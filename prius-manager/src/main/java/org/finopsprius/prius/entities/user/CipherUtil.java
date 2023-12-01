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

import org.eclipse.microprofile.config.ConfigProvider;

import io.quarkus.elytron.security.common.BcryptUtil;

public class CipherUtil {
  
  public static String encrypt(String input){
    byte[] salt = ConfigProvider.getConfig().getValue("prius.pass-salt", String.class).getBytes();
    return BcryptUtil.bcryptHash(input, 10, salt);
  }

}
