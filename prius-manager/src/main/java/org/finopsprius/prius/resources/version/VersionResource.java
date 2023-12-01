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
package org.finopsprius.prius.resources.version;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/version")
@Tag(name = "Version Resource", description = "Gets Server Version information")
public class VersionResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String version() {
        return "Prius Platform v0.0.1";
    }

    @RequestScoped
    @Path("/extended")
    @GET
    @RolesAllowed({ "admin" })
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad Request: Some properties fails"),
            @APIResponse(responseCode = "404", description = "Not Found: User not exists or password is not valid"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    }
    )
    public String versionSecured() {
        return "Prius Platform v0.0.1 (secured)";
    }
}