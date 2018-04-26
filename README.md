# App Register Backend

Application Registration system.


## Test values

```json
{
  "resourceIdentifier": {
    "type": "MAVEN",
    "url": "maven://com.github.joostvdg.keep-watching:keep-watching:0.1.0"
  },
  "author": "Joost van der Griendt",
  "created": 2016,
  "components": [
    {
      "identifier": {
        "type": "MAVEN",
        "url": "maven://com.github.joostvdg.keep-watching:keep-watching-backend:0.1.0"
      },
      "author": "Joost van der Griendt",
      "created": 2016
    }
  ],
  "attributes": [
    {
      "key": "Description",
      "value": "Small app for keeping tabs on what you're watching"
    }
  ]
}
```

## The PLAN

### Components

* app register backend :: Spring Boot 2 :: Kotlin :: Gradle
* app register client :: Go CLI

### Build Process Backend

* build with Gradle into a Docker image -> Docker Compose
* start Docker container
* Put sources into a Gradle Build Container -> execute integration tests (shared network/dns name)
* build in swarm-ci:
    * sonarqube
    * gattlin
    * release into Artifactory 

### Runtime

* Docker Swarm Service for MongoDB
* Docker Swarm Service for App-Register backend
* DFP, DFM, ELK

### Basic functionality

* Teams
* Person
* Owner
    * person?
    * team?
* Products
    * uri
    * initial authors (listOf person)
    * current owners
    * Components: 0..n
        * dependencies: 0..n
        * url
* Blueprint for DUI
    * DUI = technical challenge of distributed, parallel, concurrent computing
    * app-register = problem domain

### Other elements

* Consul:
    * https://cloud.spring.io/spring-cloud-consul/
    * http://www.baeldung.com/spring-cloud-consul

## TODO

Event stuff:

```yaml
  creationTimestamp: 2017-05-31T07:21:55Z
  name: my-config
  namespace: default
  resourceVersion: "241345"
  selfLink: /api/v1/namespaces/default/configmaps/my-config
  uid: d35f0a3d-45d1-11e7-9e62-080027a46057
```
