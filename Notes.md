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

Q: What are advanced settings and property refresh?

- configure for 'fail fast' to fail service if it cannot connect to Config Server
- can add client retry if Config Server occasionally unavailable
- refresh clients individually or in bulk
  - refresh endpoint - call it for service and refresh config without restarting
  - use Spring Cloud bus and broadcast an event and then every service listens on that and refreshes itself (better for multiple services using the Config Server)
- values are cached in the bean

### Asynchronous activities

Q: What is the role of asynchronous processing in microservices?

- reduces dependencies between services - messaging
- support low latency, high throughput (fewer blocking calls)
- facilitate event-driven computing

Q: What are some problems with the status quo?

- consuming resources even when services aren't in use
- services baked into monolithic deployments
- challenges scaling services on demand
- difficulty tracing service calls

Q: What is 'serverless' computing?

- deploy 'function' instead of 'application'
- run code without knowledge of infra
- elastic, automatic horizontal scaling
- start fast, run short

Q: What is a Spring Cloud Task?

- short-lived, asynchronous microservices (more complex than for example a batch job for which you write a script)
  - machinery to run tasks - annotations etc.
  - storage of the result of a task - audit trail
  - integrate with other runtimes

Q: How does this fit into the Spring ecosystem?

- Spring Boot apps
- Spring Batch
- Spring Cloud Stream (e.g. a listener on one of these streams can kick off a task)
- Spring Cloud Data Flow (e.g. when orchestrating data microservices you can use a task as one of the steps in that data flow)

Q: How do you create a task?

- add classpath dependencies to POM - adding references to Spring Cloud Starter
- annotate the class with @EnableTask
- add business logic to run the task - almost just a 'function'
- deploy to the Maven repository - make it more generally available

Q: How does a Task's logic work?

- Spring (Boot) app with access to beans
- task is stateless - pull from some other service, do its logic, save any outbound state and then shutdown
- bootstrap with a Runner - command line runner or application runner - can use multiple runners and the task isn't done until all the runners are complete
- can subscribe to lifecycle events - can subscribe to them

Q: What are some task result storage options?

- H2
- HSQLDB
- MySQL - just needs a tasklogs database and connection info in the project
- Oracle
- PostgreSQL
- SQL Server

Q: What's an example of repository entity relationship diagram?

```aidl
TASK_EXECUTION table
[key] TASK_EXECUTION_ID
START_TIME
END_TIME
TASK_NAME
EXIT_CODE
EXIT_MESSAGE
LAST_UPDATED

TASK_EXECUTION_PARAMS
[fkey] TASK_EXECUTION_ID
TASK_PARAM

TASK_SEQ
ID
UNIQUE_KEY
```

Q: What are some options for invoking tasks?

- run jar as Cron (scheduled) job
- include as part of data pipeline
- subscribe to event bus
- directly invoked via a custom Launcher - start from within another process


Q: How can a task be invoked via HTTP and Spring Cloud Stream?

User -> (Make http call) HTTP Listener -> (Create TaskLaunchRequest) HTTP Listener -> (Publish Message) Spring Cloud Stream -> (Task routed) Event Sink -> (Object requested) Maven Repo -> (Object returned) Event Sink -> (Task launched) Task

- (Spring Cloud Stream can sit on top of Redis, RabbitMQ, Kafka etc.)

### Securing Microservices with a Declarative Model

Q: What is the role of Security in Microservices?

- user authentication / authorization - ideally each microservice isn't doing its own authentication but authorization
    - manage credentials and accesses
- single sign-on - chaining a lot of services together without authentication on every request
- data security - in transit or at rest
  - both data going in / out but also configurations
  - interoperability - authentication / authorization schemes should work across platforms
  
Q: What are some problems with the status quo?

- credentials embedded in applications - traffic comes into a monolith through a load balancer - credentials closely coupled with the app on a high-trust level
- unnecessary permissions
- differentiating users and machines
- not optimized for diverse clients
- aim: low-trust stateless servers env

Q: What are Spring (Cloud) Security / Config and OAuth 2.0?

- service authorization powered by OAuth 2.0
- single sign-on with OAuth 2.0 and OpenID Connect

Q: What is OAuth 2.0?

- protocol for conveying authorization - by use of a token
- provides authorization flow for various clients
- obtain limited access to user accounts - access / credentials can expire - short-lived
- separates idea of user and client - as a user I allow the client to do something for me
- access token carries more than identity - able to contain more metadata
- NOT an authentication scheme
- authorization framework that lets apps attain limited access to a user's account for a service

Q: What are the actors in an OAuth 2.0 scenario?

- resource owner - entity that grants access to a resource - usually you
- resource server - server hosting the protected resource - able to process requests that take a token in and authorize it
- client - app making protected resource requests on behalf of resource owner
- authorization server - server issuing access tokens to clients - after the resource owner has been successfully authenticated

Q: What are some OAuth 2.0 terms?

- access token - credentials used to access a protected resource - typically a random string injected in the headers of a web service request - contains scope a validity duration
- refresh token - renew the access token when it's expired
- scope - permissions - what are you able to do
- client ID and secret - when you register you app with an OAuth 2.0 provider you get these back - client credentials
- OpenID Connect - authentication protocol based on OAuth 2.0 - lets devs authenticate their users across website, apps etc. without having to actually manage the password
  - take care of some of the authentication - uses standard json web tokens - JWT

Q: How does Spring support OAuth 2.0?

- code annotations - point the user to an authorization server
  - saying this service is playing in an OAuth scenarion - who doesn't send a token should be redirected
- token storage options - not having a single point of failure for validating session tokens
- OAuth 2.0 endpoints - endpoints taken care of by Spring Boot and Spring Cloud Security
  - authorize endpoint 
  - token endpoint
  - confirming access endpoint
  - check token endpoint for the resource server to decode the access tokens
  - error endpoints
  - token key endpoints for exposing the public key in a JWT scenario
- numerous extensibility points

Q: What is the abstract OAuth flow?

Client -> (Authorization Request) Resource Owner -> (Authorization Grant) Client -> (Authorization Grant) Authorization Server -> (Access Token) Client -> (Access Token) Resource Server -> (Protected Resource) Client

- The client never sees the user's credentials, it gets a grant, and a token back from the Authorization Server

- Types of grants:
  - authorization code
  - implicit resource owner 
  - password credential
  - client credential

Q: What is the flow for OAuth 2.0 grant type Authorization Code?

- Actors:
  - Resource Owner (user)
  - User-Agent (Web Browser)
  - Client (Application)
  - AuthZ Server

- optimized to get access tokens or refresh tokens for confidential clients
- most common for server side apps
- redirection based flow - the client redirects the person the asks for feedback
- client needs to comm with the user - through a web-browser typically - wouldn't work as a 'headless' operation

Client -> (User Authorization Request) AuthZ Server -> (User Authorizez App) User-Agent / Resource owner -> (Authorization Code Grant) AuthZ Server / Client -> (Access Token Request) AuthZ Server -> (Access Token Grant) Client

Q: How do you create a Resource Server and Routing Tokens to Downstream Services?

- use @EnableResourceServer annotation
- can combine AuthZ and Resource Servers
- user-info-uri and token-info-uri properties - if the AuthZ and Resource server are not together to know how to decode the tokens, you need to set these properties so the Resource Server will know how to look up the Token - "here's a token, go validate this thing for me"
- OAuth2RestTemplate bean - web service / http call out of a Spring Boot app - to fetch user details for the auth and inject into headers

Q: How does OAuth 2.0 Grant Type: Resource Owner Password Credentials work?

- with the Password Grant the user does provide their service credentials user/pass directly to the application
- the application then uses those credentials to go get an access token from the service
- this is not an ideal flow
- Resource Owner / User -> (Password credentials) Client / Application -> (Password credentials) AuthZ Server -> (Access token) Client / Application to inject in headers
  - less redirection - where you have only a service calling a service and no way to redirect the service caller to an UI to approve the request for their credentials (headless operation)

Q: How do you create a custom Authorization Server?

- Lit up with @EnableAuthorizationServer
  - offers a bunch of endoints
    - /authorize - actual auth endpoint
    - /token - get token
    - /confirmaccess - user posts their approval for the grant
    - /error - renders errors
    - /checktoken - used by the resource server to decode the access token
    - /tokenkey - exposes the public key for token verification
- property settings drive behavior - client id, client secret etc.
- secured with Basic Authentication - credentials for basic auth are the actual client id and secret which you can define - unlike the usual Spring Security credentials with 'user' and a random password

Q: How does OAuth2 Grant Type: Client Credentials work?

- Client is itself the resource owner / there is no authorization to obtain from the end user
- Providing the client id and client secret - enough to get back a simple token

Client -> (Client authentication) Authorization Server -> (Access token) Client

Q: How do you add Access Rules?

- can method be called?
- check OAuth2 scope
- check user role

```aidl
@PreAuthorize("#oauth2.hasScope('READ') and hasAuthority('ROLE_Admin')")
@RequestMapping("/")
public String secureCall() {
    return "success!";
}
```

Q: What are advanced token options?

- JdbcTokenStore for scaling AuthZ servers - tokens stored in a persistent database (vs. in-memory)
- token extensibility via TokenEnhancers
- OpenID Connect for authentication and JWTs for encrypted tokens
  - OpenID connect is an overlay that uses the flows of OAuth2
  - bearer token is the heart of OAuth2
  - token in a JWT has a body, a header, a footer and can be encrypted and have digital signatures attached
  - OpenID uses JWT for token and user identity

### Chasing down performance issues using distributed tracing

Q: What is the role of Tracing in Microservices?

- locate misbehaving components
- observe end-to-end latency
- understand actual, not specified, behavior

Q: What are the problems with the Status Quo?

- instrumenting all comm paths
  - traffic isn't going over only HTTP - how do you capture the performance info for traffic through the message bus, or as it goes through proxies?
- collecting logs across components, threads
  - distributed logging is complex, doing async, multi-threaded, multi-server, multi-datacenter services and instrumenting all that in a that's actually thread safe and performant is a nightmare
  - or how about extending that so a developer can attach their own events to that automatic instrumentation
- correlating and querying logs
- seeing the big pic / graph

Q: What is Spring Cloud Sleuth?

- automatic instrumentation of comm channels
- terms:
  - span - individual operation that happened, a unit of work - it has timestamped events attached to it (client sent, server received, tags, hey-value info etc.) - it can start and stop i.e. it has a beginning and an end
  - trace - is an e2e latency graph - made up of spans - puts spans into context
  - annotation - timestamped events
    - client sent
    - server received (subtract 'client sent' and you get the network latency)
    - server sent (subtract the 'server received' and you get the processing time of the operation)
    - client received - end of the span - subtract 'client sent' and you get the whole time it took to get the response
  - tracer - runs inside the production apps and creates these spans - it's a must to have a high performance tracer that doesn't slow down the app

Q: What is the anatomy of a trace?

1. Request to Service 1 - no trace ID , no span ID
   1.1 Span ID and Trace ID get assigned - Trace ID = X ; Span ID = A
2. Request from Service 1 to Service 2
   2.1 Trace ID = X ; Span ID = B ; Client Sent annotation
   2.2 Trace ID = X ; Span ID = B ; Server Received annotation
   2.3 Trace ID = X ; Span ID = C - for the operation (inbound)
3. Request from Service 2 to Service 3
   3.1 Trace ID = X ; Span ID = D ; Client Sent annotation
   3.2 Trace ID = X ; Span ID = D ; Server Received annotation
   3.3 Trace ID = X ; Span ID = E - for the operation (inbound)
4. Response from Service 3 to Service 2
   4.1 Trace ID = X ; Span ID = E - for the operation (outbound)
   4.2 Trace ID = X ; Span ID = D ; Server Sent annotation
   4.3 Trace ID = X ; Span ID = D ; Client Received annotation
5. Request from Service 2 to Service 4
   5.1 Trace ID = X ; Span ID = F ; Client Sent annotation
   5.2 Trace ID = X ; Span ID = F ; Server Received annotation
   5.3 Trace ID = X ; Span ID = G - for the operation (inbound)
6. Response from Service 4 to Service 2
   6.1 Trace ID = X ; Span ID = G - for the operation (outbound)
   6.2 Trace ID = X ; Span ID = F ; Server Sent annotation 
   6.3 Trace ID = X ; Span ID = F ; Client Received annotation
7. Response from Service 2 to Service 1
   7.1 Trace ID = X ; Span ID = C - for the operation (outbound)
   7.2 Trace ID = X ; Span ID = B ; Server Sent annotation
   7.3 Trace ID = X ; Span ID = B ; Client Received annotation
8. Response from Service 1
   8.1 Trace ID = X and Span ID = A - now finished

Q: What is Automatically Instrumented and how to add Sleuth to a project?

- one of the most powerful things about Sleuth is how easy it is to have all the core interaction patterns instrumented automatically
- runnable / callable operations
- Spring Cloud Hystrix or Zuul - some of those components for circuit breaker Hystrix, some of the components for the gateway Zuul - are taken care of automatically
- RxJava
- Sync / Async RestTemplate
- Spring Integration - pub / sub events
- @Async or @Scheduled operations - trace info passed between threads
- adding Sleuth to a project:

```aidl
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
```

Q: How do you visualize latency with Zipkin?

- created by Twitter, OpenZipkin is the public fork
- collects timing data
  - collect, process and present data from tracers coming from Sleuth
  - putting the tracers into a tree for retrieval
- shows service dependencies
- visualize latency for spans in a trace
- many integrations, besides Spring
- add Sleuth with Zipkin over HTTP
  - provides a Rest API for clients to talk to

```aidl
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

- or add Sleuth with Zipkin over Spring Cloud Stream - publish messages to the Zipkin Server - using RabbitMQ or Apache Kafka

```aidl
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-stream</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<!- an example for RabbitMQ -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
</dependency>
```

Q: How do you visualize and query traces in Zipkin?

- view dependencies - which service call which service
- find a trace and view details
- perform annotations query - search for specific requests or annotations
- look for durations

Q: How do you work with sampling rates?

- Sleuth exports 10% of spans by default - can be changed with e.g. spring.sleuth.sampler.percentra=1.0
- custom samplers give fine-grained control

Q: What are the options when working with spans?

- create new spans
- continue existing spans
- associate with explicit parent
- add tags, events to span