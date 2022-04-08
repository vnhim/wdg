build:
	mvn clean install
test:
	mvn clean test
run:
	make build
	make services_run
	mvn -f application/pom.xml spring-boot:run
debug:
	make build
	mvn -f application/pom.xml spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"


services_run:
	docker-compose up -d