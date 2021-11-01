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

### Locating Services at Runtime using Service Discovery

Q: What is the role of Service Discovery in Microservices?
- recognize the dynamic environment - service have to advertise their appearance and disappearance - not necessarily the same hosts everytime
  - find instances of another service based on a sort of advertised metadata - maybe some sort of geo location criteria
  - it's also important that Service Discovery doesn't become a new single point of failure
- have a live view of healthy services
- avoid hard-coded references to service location
- centralized list of available services

Q: What are some problems with the Status Quo?
- outdated configuration management DBs
- simplistic HTTP 200 health checks
- limited load balancing for middle-tier
- DNS is insufficient for microservices
- registries can be single points of failure

Q: What is Spring Cloud Eureka?
- registry that acts as a phone-book for services
- no database just a live look at the health of individual systems
- AP system - availability & partition tolerance (vs consistency of full CAP theorem)
  - sacrifices consistency, it prioritizes availability and partition tolerance
- first released by Netflix OSS team in 2012
- used for middle-tier load balancing
- integrated into many other Netflix projects

Q: What are the components of a Eureka environment?
- Eureka server / Dashboard
  - registry - everybody talks to it to register their availability
  - standalone app - manages all the service instance info, provides an API to register an un-register and query data about instances
  - has the capability to propagate its registry info to other servers and clients
  - no database, just live info
  - zone aware - zone info, regional info come into play
  - can have replication with other Eureka servers for HA purposes
- Service A Instance / Eureka Client
  - for registering the availability of that service and even consuming that to get a copy of the registry locally
  - it keeps alive a connection with a server and caches discovering info
  - sends a heartbeat
  - these services register themselves, they get a copy of the registry and they're also responsible for sending the heartbeat
- Service B Instance / Eureka Client
  - same as above
  - can make service calls with Service A once the registry is available (using client-side load balancing)

Q: How can you build an Eureka Server?
- add spring-cloud-starter-eureka-server as a dependency
- standalone or clustered configuration
- @EnableEureka server annotation
- numerous config options - e.g. can detect a network issue and won't automatically remove all instances due to a lack of heartbeats

Q: How do you work with the Eureka Dashboard?
- enabled by default
- shows env info
- lists registered services and instances
- view service health

Q: How do you register a service with Eureka?
- Eureka in the classpath leads to registration - springcloud.starter.eureka in classpath it will want to register itself using the Spring app name as the service instance ID
- service name, host info sent during bootstrap
- @EnableDiscoveryClient and @EnableEurekaClient
- once a client is up it sends a heartbeat every 30 secs (configurable)
- heartbeat can include health status
- http or https support ; basic auth etc.

Q: How do you discover a service with Eureka?
- @EnableDiscoveryClient and @EnableEurekaClient
- client works with local cache - gets the Eureka register at startup and only gets the delta from then on
- cache refreshed, reconciled regularly
- manually load balance (if you want to pull from the Discovery client or Eureka client), or use Ribbon (decorate rest template bean with a load balanced annotation - it will autowire and add some interceptors to replace the service ID with the URL)
- can prefer talking to registry in the closest Zone
- may take multiple heartbeats to discover new services - eventually consistent model - a new service is not instantly pulled by all clients and could take a few heartbeats for the metadata to get from the server to the client cache and then used by a local load balanced rest template
  - customizable - e.g. shorter renewal interval

Q: How do you configure service health information?
- heartbeat doesn't convey health - just availability
- possible to include health information with a config chance that will pull from the actuator
  - can extend and create own health check - maybe to also remove from registry temporarily

Q: What is high availability architecture for Eureka?
- built-in 'self preservation' model - the serve might stop expiring instances if it detects a network problem - e.g. services fail in mass
- native support for peer to peer registry application
  - Eureka doesn't use a DB, it has in-memory cache, it's not necessarily consistent between clusters but it's available and partition tolerant
  - with a cluster of Eureka servers we can just point their service URLs at eachother
  - if there's a network fail the server can figure that out and goes into a self preservation mode and will eventually correct and re-sync
- use DNS in front of Eureka cluster
- recommended to have one Eureka cluster in each Zone

Q: What are some advanced configuration options for Eureka?
- dozens and dozens of configuration flags
- set cache refresh intervals
- set timeouts
- set connection limits
- set service metadataMap
- override default service, health endpoints
- define replication limits, timeout, retries