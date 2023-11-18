#!/bin/bash
#
# Copyright 2023 Javi Roman
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# The default java implementation to use.
JAVA_HOME=${JAVA_HOME:=/usr/lib/jvm/java-17}
PRIUS_HOME=${PRIUS_HOME:=$PWD/..}
# The directory for the PRIUS pid file
PRIUS_PID_DIR=${PRIUS_PID_DIR:=$PRIUS_HOME/run}
# The directory for PRIUS log files
PRIUS_LOG_DIR=${PRIUS_LOG_DIR:=$PRIUS_HOME/logs}
# The backend API URI
PRIUS_API_URI=${PRIUS_API_URI:=http://localhost:8080}

export JAVA_HOME PRIUS_HOME PRIUS_PID_DIR PRIUS_LOG_DIR PRIUS_API_URI
