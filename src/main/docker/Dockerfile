FROM openjdk:10 as builder

RUN jlink \
    --add-modules java.logging,java.xml,java.naming,java.management,jdk.unsupported \
    --verbose \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /opt/jre-minimal

FROM panga/alpine:3.7-glibc2.25

COPY --from=builder /opt/jre-minimal /opt/jre-minimal

ENV LANG=C.UTF-8 \
    PATH=${PATH}:/opt/jre-minimal/bin

ADD modules /opt/app/modules
ADD patch/java.beans /opt/app/patch/java.beans

ARG JVM_OPTS
ENV JVM_OPTS=${JVM_OPTS}

CMD java ${JVM_OPTS} \
    --patch-module java.base=/opt/app/patch/java.beans \
    --add-exports java.base/java.beans=johnzon.mapper \
    --add-exports java.base/java.beans=org.apache.logging.log4j.core \
    --module-path /opt/app/modules \
    --module hammock.jpms
