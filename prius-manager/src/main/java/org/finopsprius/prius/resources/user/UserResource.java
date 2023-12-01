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
package org.finopsprius.prius.resources.user;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.finopsprius.prius.entities.user.User;
import org.finopsprius.prius.entities.user.UserListResponse;

@ApplicationScoped
@Path("/v1/user")
@Tag(name = "User Resource", description = "User management")
public class UserResource {
  
  @Inject
  JsonWebToken jwt; 

  @GET
  @RolesAllowed("adminrole")
  @Operation(summary = "List users")
  @Produces(MediaType.APPLICATION_JSON)
  @APIResponses(value = {
    @APIResponse(responseCode = "200"),
    @APIResponse(responseCode = "400", description = "Bad Request: Some properties fails"),
    @APIResponse(responseCode = "403", description = "Forbidden"),
    @APIResponse(responseCode = "405", description = "Method Not Allowed"),
    @APIResponse(responseCode = "500", description = "Internal Server Error")
  })
  public List<UserListResponse> list(@QueryParam("sort") List<String> sortQuery,
                                     @QueryParam("page") @DefaultValue("0") int pageIndex,
                                     @QueryParam("size") @DefaultValue("25") int pageSize) {
    Page page = Page.of(pageIndex, pageSize);
    List<User> userList;
    if (sortQuery.size() > 0) {
      userList = User
      .findAll(Sort.by(String.join(",", sortQuery)))
      .page(page)
      .list();
    } else {
      userList = User
      .findAll()
      .page(page)
      .list();
    }

    List<UserListResponse> userListResponses = new ArrayList<>();
    for (User user : userList) {
      userListResponses.add(new UserListResponse(user.getId(), user.getUsername()));
    }

    return userListResponses;
  }

}
