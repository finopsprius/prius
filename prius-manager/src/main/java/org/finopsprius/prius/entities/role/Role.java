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
package org.finopsprius.prius.entities.role;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.RolesValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Table(name = "role")
@Schema(description = "Represents a platform user role")
@JsonAutoDetect(
  fieldVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC,
  getterVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC,
  setterVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
@NamedQueries({
        @NamedQuery(
                name = "Role.findByRolename",
                query = "SELECT r FROM Role r WHERE r.name = ?1"
        )
})
public class Role extends PanacheEntityBase {
  @Id
  @SequenceGenerator(name = "roleSequence", sequenceName = "role_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "roleSequence")
  @Schema(readOnly = true)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @Column(unique = true, nullable = false)
  @Schema(readOnly = true)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @RolesValue
  private String name;

  @Column(nullable = false)
  @Schema(required = true)
  @NotBlank(message = "role description")
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getDesc(){
    return this.description;
  }

  @Override
  public String toString() {
      return "Role [id=" + id + ", name=" + name + "]";
  }
}
