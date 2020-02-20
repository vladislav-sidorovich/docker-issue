# docker-issue
This project try to reproduce the issue connection from docker image to localhost

# steps

Make sure you have maven toolchain for Amazon Java 11 and Docker. 

Let's validate without docker
1. Run `local-rest-service/com.example.localrestservice.LocalRestServiceApplication`
2. go to http://localhost:8080 and http://localhost:8080/abc
3. Run `initiator/com.example.initiator.InitiatorApplication`
4. go to http://localhost:8082 and http://localhost:8082/qwe

Now you should see result from both services. Let's run **initiator** in Docker
1. strop initiator/com.example.initiator.InitiatorApplication
2. `cd initiator` && `mvn clean package`
3. `docker build -t initiator .`
4. `docler images` and just check that you have _initiator_
5. `docker-compose up`
6. go to http://localhost:8082 and http://localhost:8082/qwe