### Microservices

Q: What are microservices?
'Loosely coupled service oriented architecture with bounded context'
(Adrian Cockcroft, Battery Ventures)

Q: What problems do they solve?
- desire for faster changes
- need for greater availability
- motivation for fine-grained scaling
- compatible with a DevOps mindset 

#### Core characteristics of microservices

Q: What are their core characteristics?
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

#### The role of configuration in Microservices

Q: What is the role of configuration in microservices?
- removing 'settings' from compiled code
- change runtime behaviors
- enforce consistency across elastic services
- cache values to reduce load on databases

#### Problems with the status quo

Q: What are the problems with the status quo?
- local configuration files fall out of sync
- no history of changes with env variables
- configuration changes require restart
- challenges with sensitive info
- inconsistent usage across teams

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

### Spring Boot

Q: What are the main features of Spring Boot?
- offers opinionated runtime for Spring
    - convention, not configuration (annotations, not xml config files)
- 'opinions' can be overridden
- handles boilerplate setup
- simple dependency management
- embeds app server in executable JAR
- built in endpoints for health metrics

### Spring Cloud Config

Q: What is the Spring Cloud config?
- HTTP access to git or file based configurations
- you end up with a configuration server that uses a git or file based backend and it servers out configuration values to your Java Spring app or anything that can consume it over HTTP

#### Creating a Config Server

Q: How do you create a Config Server?
- choose a config source
  - local files
      - points to classpath or file system
      - multiple search locations possible
      - no audit trail
      - supports labelling
      - support for placeholders in URI
      - relies on 'native' profile - spring profile that indicates looking at local files
      - dev / test only, unless set up in reliable, shared fashion
  - git-based repository
      - points to git repo
      - multiple search locations possible
      - full change history
      - support labelling
      - support for placeholders in URI
      - multiple profiles possible
      - local git for dev / test highly available file system or service for production
- add config files
  - native support for YML, properties file
  - can serve out any text file
  - file name contains app, optionally profile
  - nested folders supported
  - all matching files returned
- build the spring project (the config server itself is a spring boot app)
  - generate spring scaffolding
  - see POM dependency on spring-cloud-config-server ad spring-boot-started-actuator
  - add $EnableConfigServer annotation to class
  - create app properties (or YAML) with server port, app name, and profile
- secure the configurations

#### YAML file example

```aidl
spring:
    cloud:
        config:
            server:
                git:
                    uri: https:\/\/github.com/watolls/rates // point to git location
                    searchPaths: 'station*' // pattern to search sub directories
                    repos: // point to alternate repos
                        prod:
                            patter: '*/prod' // pattern to go to alternate repo
                            uri: https:\/\/github.com/watolls/rates-prod
```

E.g.

```aidl
https:\/\/github.com/wa-tolls/rates/{application}/{profile}[/{label}]
        <branch: master>           - required   - required - optional 
        
- application.properties
- station1
    - s1rates-dev.properties
    - s1rates-qa.properties
    - s1rates.properties
- station2
    - s2rates-dev.properties
    - s2rates.properties   
    
/s1rates/default - application.properties & s1rates.properties
/s1rates/dev - application.properties & s1rates-dev.properties & s1rates.properties
/s2rates/qa - application.properties & s2rates.properties
/s3rates/default - application.properties
```
---

Q: How are configurations consumed?

- Spring apps use Config Servers as a property source
- Loads values based on app name, Spring profile, label
- Annotate code with @Value attribute
- Can consume from non-Spring apps via URL

Q: How can you apply access security to configurations?

- integrated security via Spring Security
- default HTTP Basic, but other options like OAuth2
- configured in properties, YAML files
- could be unique per profile
- look to also secure with network security, API gateways

Q: How to encrypt / decrypt configurations?

- property values not stored in plain text
- symmetric or asymmetric options
- server offers encrypt and decrypt endpoints
- can decrypt on server or in the client


