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

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class UserListResponse {
  @Schema(example = "1")
  private Long id;

  @Schema(example = "admin@admin.com")
  private String username;

  /**
   * @param id
   * @param username
   */
  public UserListResponse(Long id, String username) {
    setId(id);
    setUsername(username);
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
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
   * To String
   */
  @Override
  public String toString() {
    return "UserResponse [id=" + id + ", username=" + username + "]";
  }

}
