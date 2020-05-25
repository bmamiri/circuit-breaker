# circuit-breaker
Learn to leverage one of the Spring Cloud Netflix stack component called Hystrix to implement circuit breaker while invoking underlying microservice. It is generally required to enable fault tolerance in the application where some underlying service is down/throwing error permanently, we need to fall back to a different path of program execution automatically. This is related to the distributed computing style of the Ecosystem using lots of underlying Microservices. This is where the circuit breaker pattern helps and Hystrix is a tool to build this circuit breaker.

**Hystrix configuration is be done in four major steps.**

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

**Test Hystrix Circuit Breaker – Demo** <br>
The opening browser and type `http://localhost:9098/getSchoolDetails/abcschool.` <br>

It should show the below output in the browser <br>
`NORMAL FLOW !!! - School Name -  abcschool :::  Student Details [{"name":"John","className":"Class IV"},{"name":"Mike","className":"Class V"}]` <br>

Now we already know that School service is calling student service internally, and it is getting student details from that service. So if both the services are running, school service is displaying the data returned by student service as we have seen in the school service browser output above. This is **CIRCUIT CLOSED State**. <br>

Now let us stop the student service and test the school service again from the browser. This time it will return the fallback method response. Here Hystrix comes into the picture, it monitors Student service infrequent interval and as it is down, the Hystrix component has opened the Circuit and fallback path enabled. <br>

Here is the fallback output in the browser. <br>
`CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. Service will be back shortly` <br>

Again start the Student service, wait for few moments and go back to school service, and it will again start responding in normal flow. <br>

**Hystrix Dashboard**<br>
http://localhost:9098/hystrix