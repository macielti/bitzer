FROM clojure as buildStage

LABEL stage="builder"

COPY . /usr/src/app

WORKDIR /usr/src/app

RUN apt-get -y update

RUN lein deps

RUN lein uberjar

FROM amazoncorretto:22-alpine

WORKDIR /app

COPY --from=buildStage /usr/src/app/target/bitzer-0.1.0-SNAPSHOT-standalone.jar  /app/bitzer.jar

RUN apk add lmdb

CMD ["java", "-jar", "bitzer.jar"]