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
package org.finopsprius.prius;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.transaction.Transactional;
import org.finopsprius.prius.bootstrap.FirstBoot;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class AppLifecycleBean {
    private static final Logger LOGGER = Logger.getLogger("PriusLogger");

    void onStart(@Observes StartupEvent ev) {
        System.out.println("Prius Platform starting...");

        firstBootInitializeTables();

        LOGGER.info("The application is starting...");
    }

    @Transactional
    void firstBootInitializeTables(){
        FirstBoot.initializeTables();
    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("Prius Platform stopping...");
        LOGGER.info("The application is stopping...");
    }

}
