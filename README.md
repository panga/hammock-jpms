# Hammock JPMS

[Hammock MicroProfile](https://github.com/hammock-project/hammock) (CDI + JAXRS + JSON) HelloWorld using Java Module System (JPMS).

The stack uses OpenWebBeans 2 for CDI, RestEasy 3.5 for JAXRS and Johnzon 1.1 for JSON.

The total size of this example application is around `11mb`!

_Note: Tested with Java 10.0.1_

## How To

### Package and generate `target/modules` folder

`mvn clean package`

### Package using with Weld 3 for CDI

`mvn clean package -Pweld`

_Note: Depends on https://issues.jboss.org/browse/WELD-2435 fix._

### Run using the default JRE

`java --module-path target/modules --module hammock.jpms`

### Run using a minimal JRE (35mb)

1. Run jlink to create minimal JRE

```java
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

```java
target/jlink-image/bin/java \
    --patch-module java.base=target/patch/java.beans \
    --add-exports java.base/java.beans=johnzon.mapper \
    --add-exports java.base/java.beans=org.apache.logging.log4j.core \
    --module-path target/modules \
    --module hammock.jpms
```

_Note: https://github.com/panga/java-beans-lite was used to patch `java.beans` implementation._