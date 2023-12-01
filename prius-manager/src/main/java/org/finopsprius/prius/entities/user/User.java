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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Table(name = "user_account")
@Schema(description = "Represents an user into the platform")
@JsonAutoDetect(
  fieldVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC,
  getterVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC,
  setterVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
@NamedQueries({
        @NamedQuery(
                name = "User.findByUsername",
                query = "SELECT u FROM User u WHERE u.username = ?1"
        )
})
@UserDefinition
public class User extends PanacheEntityBase {
  @Id
  @SequenceGenerator(name = "userSequence", sequenceName = "user_account_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "userSequence")
  @Schema(readOnly = true)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @Column(unique = true, nullable = false)
  @Schema(required = true)
  @NotBlank(message = "username/email may not be blank")
  //@Size(min = 5, max = 45, message = "username/email should have size [{min},{max}]")
  @Username
  @Email
  private String username;

  @Column(nullable = false)
  @Schema(required = true)
  @NotBlank(message = "password may not be blank")
  //@Size(min = 5, max = 72, message = "password should have size [{min},{max}]")
  @Password
  private String password;

  @Column
  @Schema
  @Roles
  private String rolename;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = CipherUtil.encrypt(password);
  }

  public String getRole() {
    return rolename;
  }

  public void setRole(String rolename) {
    this.rolename = rolename;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", role=" + rolename + "]";
  }
}
