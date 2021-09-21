## Java Microservices with Spring Cloud: Coordinating Services

Q: Why are Microservices Architectures popular?
- desire for faster changes
- need for greater availability
- motivation for fine-grained scaling
- compatible with a DevOps mindset

Q: What are core characteristics of Microservices?
- components exposed as services - REST, SOAP, messaging etc.
- tied to a specific domain - Domain Driven Design - e.g. Order Sevice, Customer Service, Notifcation Service etc.
- loosely coupled - limited dependency
- built to tolerate failure
- delivered continuously via automation
- built and run by independent teams mostly

Q: What are some coordination challenges that emerge with Microservices?
- how do you locate services when hosts change as services get updated or scaled?
- what can you do to prevent cascading failures when one service starts misbehaving?
- how do you reduce single points of failure in a distributed architecture?
- where should you perform load balancing of dynamic services?
- how can you dynamically adjust the routing tier?
- what's a good way to introduce loose coupling to an architecture?

Q: How does Spring Cloud provide Microservices scaffolding?
- released March 2015
- build common distributed systems patterns
- open source software
- optimized for Spring apps
- run anywhere
- includes Netflix OSS technology

Q: What are some useful Spring Cloud projects useful for distributed services?
- Spring Cloud Eureka - service discovery and registration - how to find available services and how do I advertise myself as a service for others to use
- Spring Cloud Hystrix - circuit breaker for handling errors from downstream services - monitor and close circuit once the service is back to allow traffic to go through
- Spring Cloud Ribbon - client-side load-balancing - decentralized load-balancing using a registry or cache of available nodes with client-side load-balancing
- Spring Cloud Zuul - API gateway for routing and traffic logic - good flexibility for version changes or different client types
- Spring Cloud Stream - abstract out some of the core messaging engines e.g. Kafka, RabbitMQ etc. - makes it easier for code to comm with the messaging backend
- Spring Cloud Data Flow - data processing pipeline - connecting a set of Spring Boot apps / Spring Cloud Stream apps and task - application integration