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

### NodeJS and NPM

For installing NodeJS (and NPM) we use the Node Version Manager:
[NVM](https://github.com/nvm-sh/nvm#install--update-script)

```
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash
```
```
nvm --version
nvm ls
nvm ls-remote
nvm install 16.2.0
nvm use node 14.15.5

node -v
v14.15.5

npm -v
6.14.11
```

As we can see the node installation manager itself installs a version of NPM, 
if we want to upgrade to the latest version we will use this command:

```
nvm install-latest-npm

npm -v
7.14.0

nvm install 18.12.1
Downloading and installing node v18.12.1...
Downloading https://nodejs.org/dist/v18.12.1/node-v18.12.1-linux-x64.tar.xz...
#################################################
100.0%
Computing checksum with sha256sum
Checksums matched!
Now using node v18.12.1 (npm v8.19.2)
```
Creating aliases:

```
nvm alias prius v18.12.1
prius -> v18.12.1

nvm alias otherproject v14.16.0
otherproject -> v14.16.0

nvm use prius
Now using node v18.12.1 (npm v8.19.2)
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

