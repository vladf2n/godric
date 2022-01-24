FROM adoptopenjdk/openjdk11:latest  as build

ENV SPRING_PROFILES_ACTIVE=prod

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x ./mvnw

# download the dependency if needed or if the pom file is changed
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


FROM adoptopenjdk/openjdk11:latest as production
ARG DEPENDENCY=target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-cp", "app:app/lib/*","br.com.fcube.godric.GodricApplication"]