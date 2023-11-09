## Introduction

These notes have been created as a reminder of the use of the different SDK's
used in the project. Unfortunately each of the technologies has a different SDK
manager which makes it complicated to configure the different programming
frameworks for different environments.

### Java and Maven

For installing Java/Maven we use the The Software Development Kit Manager:
[SDKMAN](https://sdkman.io)

```
curl -s "https://get.sdkman.io" | bash
```

```
sdk list java
sdk install java 17.0.9-tem
sdk use java java 17.0.9-tem
sdk current java
sdk list java | grep -E "installed|local"

sdk list maven
sdk list maven | grep \*
sdk install maven 3.9.5

sdk env init
sdk selfupdate
```

---
<sub>
Copyright 2023 Javi Roman
<br>
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at: 
<a href="https://www.apache.org/licenses/LICENSE-2.0">ASLv2.0</a>
 Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

