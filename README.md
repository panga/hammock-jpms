# hammock-jpms

Hammock MicroProfile HelloWorld using Java Module System (JPMS)

Tested with Java 10.0.1

1. `mvn clean package`

2. `java --module-path target/modules --module hammock.jpms`

Note: Depends on https://issues.jboss.org/browse/WELD-2435 fix.
