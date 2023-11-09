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