### Microservices

'Loosely coupled service oriented architecture with bounded context'
(Adrian Cockcroft, Battery Ventures)

- desire for faster changes
- need for greater availability
- motivation for fine-grained scaling
- compatible with a DevOps mindset 

#### Core characteristics of microservices

- components exposed as services
- tied to a specific domain
- loosely coupled
- built to tolerate failure
- delivered continuously via automation
- built and run by independent teams

#### Questions about a Microservices architecture

- should every app be turned into a set of microservices?
- how do I find my service if URIs can change?
- how am I supposed to ship changes continuously?
- what if my team isn't arranged for DevOps?
- how do services maintain consistent configurations, at scale?
- how do I keep a poor-performing service from taking everything down?
- is there one single tech stack for these systems?
- what's the right way to secure services?
- how do I troubleshoot problems?
- isn't a monolith just simpler?

### Microservices scaffolding with Spring Cloud

- released March 2015
- build common distributed systems patterns
- open source software
- optimized for Spring apps
- run anywhere
- includes NetflixOSS technology

### Catalog of Spring Cloud projects

- Spring Cloud Config - Git-backed configuration server
- Spring Cloud Netflix - Suite for service discovery, routing, availability
- Spring Cloud Consul - Service discovery with Consul
- Spring Cloud Security - Simplify OAuth 2.0 flows
- Spring Cloud Sleuth - Distributed tracing
- Spring Cloud Stream - Message bus abstraction
- Spring Cloud Task - Short-lived, single-task microservices
- Spring Cloud Dataflow - Orchestration of data microservices
- Spring Cloud Zookeeper - Service discovery and configuration with Zookeeper
- Spring Cloud for AWS - Exposes core AWS service to Spring Developer
- Spring Cloud Spinnaker - Multi-cloud deployment
- Spring Cloud Contract - Stubs for service contracts