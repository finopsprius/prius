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


import jakarta.validation.constraints.NotBlank;

public class ChangePassword {
  @NotBlank
  private String oldPassword;
  @NotBlank
  private String newPassword;

  
  /**
   * @param oldPassword
   * @param newPassword
   */
  public ChangePassword(String oldPassword, String newPassword) {
    setOldPassword(oldPassword);
    setNewPassword(newPassword);
  }

  /**
   * @return the oldPassword
   */
  public String getOldPassword() {
    return oldPassword;
  }
  /**
   * @param oldPassword the oldPassword to set
   */
  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }
  /**
   * @return the newPassword
   */
  public String getNewPassword() {
    return newPassword;
  }
  /**
   * @param newPassword the newPassword to set
   */
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }  
}
