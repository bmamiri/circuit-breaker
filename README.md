# circuit-breaker
Learn to leverage one of the Spring Cloud Netflix stack component called Hystrix to implement circuit breaker while invoking underlying microservice. It is generally required to enable fault tolerance in the application where some underlying service is down/throwing error permanently, we need to fall back to a different path of program execution automatically. This is related to the distributed computing style of the Ecosystem using lots of underlying Microservices. This is where the circuit breaker pattern helps and Hystrix is a tool to build this circuit breaker.

**Hystrix configuration is done in four major steps.**

1. Add Hystrix starter and dashboard dependencies.<br>
    * spring-cloud-starter-netflix-hystrix<br>
    * spring-cloud-starter-netflix-hystrix-dashboard<br>
2. Add `@EnableCircuitBreaker` annotation<br>
3. Add `@EnableHystrixDashboard` annotation<br>
4. Add annotation `@HystrixCommand(fallbackMethod = "myFallbackMethod")`<br>

To demo circuit breaker, we will create the following two microservices where first is dependent on another.<br>

**Student Microservice** – This will give some basic functionality on the Student entity. It will be a REST-based service. We will call this service from School Service to understand the Circuit Breaker. It will run on port 8098 in the localhost.<br>
http://localhost:8098/getStudentDetailsForSchool/abcschool <br>
**School Microservice** – Again a simple REST-based microservice where we will implement circuit breaker using Hystrix. Student Service will be invoked from here and we will test the fallback path once student service will be unavailable. It will run on port 9098 in the localhost.<br>
http://localhost:9098/getSchoolDetails/abcschool <br>

**Hystrix Dashboard**<br>
http://localhost:9098/hystrix