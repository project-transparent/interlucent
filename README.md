![Interlucent](https://github.com/project-transparent/interlucent/blob/main/interlucent.png)

**Interlucent** is a small bridge library that combines Lucent and Eureka's features, as well as adding additional utilities. 

### Requirements
Eureka is designed to use JDK 8 but supports later versions, such versions may be unstable with this project at the moment.

## Installation (Gradle - Local)

1. Clone the repo (https://github.com/project-transparent/interlucent).
2. Run `gradlew publishToMavenLocal` in the root directory of the repo.
3. Add `mavenLocal()` to your repositories.
4. Add `implementation 'org.transparent:interlucent:<version>'` to your dependencies.
