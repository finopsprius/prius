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
package org.finopsprius.prius.entities.access;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class LoginInfoResponse {
  @Schema(example = "admin@admin.com")
  private String username;

  @Schema(example = "[\"admin\"]")
  private String role;

  @Schema(minimum = "1", example = "86400")
  private Long expirationTime;

  /**
   * @param username
   * @param role
   * @param expirationTime
   */
  public LoginInfoResponse(String username, String role, Long expirationTime) {
    setUsername(username);
    setRole(role);
    setExpirationTime(expirationTime);
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the roles
   */
  public String getRole() {
    return role;
  }

  /**
   * @param role the roles to set
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * @return the expirationTime
   */
  public Long getExpirationTime() {
    return expirationTime;
  }

  /**
   * @param expirationTime the expirationTime to set
   */
  public void setExpirationTime(Long expirationTime) {
    this.expirationTime = expirationTime;
  }

  /**
   * To String
   */
  @Override
  public String toString() {
    return "LoginInfoResponse [username=" + username + ", roles=" + role + ", expirationTime=" + expirationTime + "]";
  }

}