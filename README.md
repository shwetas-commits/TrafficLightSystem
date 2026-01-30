## Traffic Light Controller API

### Design Principles
- Invalid states are prevented by design
- Business rules live in the domain layer
- Concurrency will be handled per intersection

### Assumptions
- Fixed directions (N, S, E, W)
- One active phase at a time


## Swagger doc: http://localhost:8080/v3/api-docs
## Swagger Doc UI: http://localhost:8080/swagger-ui/index.html#/

### How to Run
 Run TrafficLightSystemApplication.java
OR
 mvn clean test
 mvn spring-boot:run
 http://localhost:8080/swagger-ui.html
