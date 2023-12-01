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
package org.finopsprius.prius.entities.user;

import jakarta.inject.Inject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import jakarta.enterprise.context.ApplicationScoped;

import io.smallrye.jwt.build.Jwt;

import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class TokenUtil {

  public String get(String username, String role) {
    String jwt_issuer = ConfigProvider.
            getConfig().
            getValue("mp.jwt.verify.issuer", String.class);

    String token = Jwt.issuer(jwt_issuer)
        .upn(username)
        .groups(new HashSet<>(Arrays.asList(role)))
        .innerSign().encrypt();
    return token;
  }
}
