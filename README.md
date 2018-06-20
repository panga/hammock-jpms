# Hammock JPMS

[Hammock MicroProfile](https://github.com/hammock-project/hammock) (CDI + JAX-RS + JSON) HelloWorld using Java Module System (JPMS).

The stack uses Weld 3 for CDI, RestEasy 3.5 for JAX-RS and Johnzon 1.1 for JSON

The total size of this example application is around `12mb` and the full Docker image is only `58mb`!

_Note: Tested with Java 10.0.1_

## How To

### Package and generate `target/modules` folder (weld profile)

`mvn clean package`

_Note: Need to build Weld 3.0.5-SNAPSHOT locally first because of https://issues.jboss.org/browse/WELD-2435 or choose OpenWebBeans profile._

### Package and generate `docker image` (docker + weld profiles)

`mvn clean package -Pweld,docker`

### Package using with OpenWebBeans 2 for CDI (owb profile)

`mvn clean package -Powb`

### Build Weld 3.0.5-SNAPSHOT locally

1. Clone `https://github.com/weld/core.git`

2. Execute `mvn install`

### Run using the default JRE

`java --module-path target/modules --module hammock.jpms`

### Run using a minimal JRE (35mb)

1. Run jlink to create minimal JRE

```bash
jlink \
	--add-modules java.logging,java.xml,java.naming,java.management,jdk.unsupported \
	--verbose \
	--strip-debug \
	--compress 2 \
	--no-header-files \
	--no-man-pages \
	--output target/jlink-image
```

2. Run minimal JRE with patch module for `java.beans`:

```bash
target/jlink-image/bin/java \
    --patch-module java.base=target/patch/java.beans \
    --add-exports java.base/java.beans=johnzon.mapper \
    --add-exports java.base/java.beans=org.apache.logging.log4j.core \
    --module-path target/modules \
    --module hammock.jpms
```

_Note: https://github.com/panga/lite-beans was used to patch `java.beans` implementation required by some 3rd party dependencies and remove the requirement of adding `java.desktop` module into the minimal JRE._

### Run using the Docker image

`docker run --rm -it -p 8080:8080 hammock-jpms`

### Test the application

`curl http://localhost:8080/hello`
