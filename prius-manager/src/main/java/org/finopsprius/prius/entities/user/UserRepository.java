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

import java.util.Optional;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Optional<User> findByUsername(String username) {
        return find("#User.findByUsername", username).firstResultOptional();
    }

    public User findByName(String username) {
        return find("name", username).firstResult();
    }

    public User add(String username, String password, String rolename) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(rolename);
        user.persist();
        return user;
  }

    public boolean chgPassword(String username, String password) throws Exception {
        Optional<User> optionaluser = User.find("#User.findByUsername", username).firstResultOptional();
        if (optionaluser.isPresent()) {
            User user = optionaluser.get();
            user.setPassword(password);
            user.persist();
            return true;
        } else {
            throw new Exception("username not exists");
        }
    }
}
