/**
 *  Copyright 2023 Javi Roman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.finopsprius.prius.resources.access;

import java.util.Optional;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.security.UnauthorizedException;
import org.finopsprius.prius.entities.access.Login;
import org.finopsprius.prius.entities.access.LoginInfoResponse;
import org.finopsprius.prius.entities.access.LoginTokenResponse;
import org.finopsprius.prius.entities.user.*;

@Path("/v1/access")
@Tag(name = "Access Resource", description = "User authentication and JWT token")
public class AccessResource {
    @Inject
    JsonWebToken jwt;
    @Inject
    TokenUtil tokenUtil;
    @Inject
    UserRepository userRepository;

    @POST
    @Path("/login")
    @Operation(summary = "User login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad Request: Some properties fails"),
            @APIResponse(responseCode = "401", description = "Unauthorized: User not exists or password is not valid"),
            @APIResponse(responseCode = "405", description = "Method Not Allowed"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public LoginTokenResponse login(@Valid Login logindata) {
        // check if exist user
        Optional<User> optionalUser = userRepository.findByUsername(logindata.getUsername());
        if (!optionalUser.isPresent()) {
            // if not exist returns 401
            throw new WebApplicationException(401);
        }
        // check if password is correct
        User user = optionalUser.get();
        String passwordCrypted = CipherUtil.encrypt(logindata.getPassword());
        if (!passwordCrypted.equals(user.getPassword())) {
            // if not match returns 401
            throw new WebApplicationException(401);
        }

        // Create token with username and role
        String token = tokenUtil.get(user.getUsername(), user.getRole());

        return new LoginTokenResponse(token);
    }

    @GET
    @Path("/logout")
    @Operation(summary = "User logout")
    @RolesAllowed("**")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "401", description = "Not Authorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "405", description = "Method Not Allowed"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response logout() {
        if (jwt.getName() == null) {
            throw new UnauthorizedException();
        }
        Optional<User> userOptional = userRepository.findByUsername(jwt.getName());
        if (userOptional.isEmpty()) {
            throw new UnauthorizedException();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/info")
    @Operation(summary = "User information")
    @RolesAllowed("**")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "401", description = "Not Authorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "405", description = "Method Not Allowed"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public LoginInfoResponse info() {
        if (jwt.getName() == null) {
            throw new UnauthorizedException();
        }
        Optional<User> userOptional = userRepository.findByUsername(jwt.getName());
        if (userOptional.isEmpty()) {
            throw new UnauthorizedException();
        }
        User user = userOptional.get();
        return new LoginInfoResponse(user.getUsername(), user.getRole(), jwt.getExpirationTime());
    }

    @Transactional
    @POST
    @Path("/chgpassword")
    @Operation(summary = "Update password")
    @RolesAllowed("**")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Password updated"),
            @APIResponse(responseCode = "400", description = "Bad Request: Some properties fails"),
            @APIResponse(responseCode = "401", description = "Not Authorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "405", description = "Method Not Allowed"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response chgPassword(@Valid ChangePassword changePassword) {
        // get username
        String username = jwt.getName();

        // get user
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            // if not exist returns 404
            throw new WebApplicationException(404);
        }
        User user = optionalUser.get();

        // Check if oldpassword exists
        String oldPasswordCrypted = CipherUtil.encrypt(changePassword.getOldPassword());
        if (!oldPasswordCrypted.equals(user.getPassword())) {
            return Response.ok("{\"err\": \"oldpassword not match\"}")
                    .status(Response.Status.BAD_REQUEST)
                    .build(); //TODO: change this
        }

        try {
            if (userRepository.chgPassword(user.getUsername(), changePassword.getNewPassword())) {
                return Response.noContent().status(200).build();
            } else {
                return Response.notModified().build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

}
