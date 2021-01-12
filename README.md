# Rick & Morty MIX

This is an implementation of the exercise proposed by Debut.

The objective is to mix one or more datasets, and provide an aggregated results.

The development must be done in Kotlin + Java Spring Boot.

## Getting Started

I had to upgrade gradle in order to work with Java 15 - latest version,
so please ensure you have the same version or a compatible one with
Gradle 6.7-rc1.

In order to execute the app, simply run:

`./gradlew bootRun`

From the command line. This will spin up a local webserver on localhost:8080.

You can ensure everything is working fine by navigating to:

GET http://localhost:8080/characters/

It should present a mix of remote and local characters. This is the
main endpoint that returns all characters.

## Other endpoints

Following the best practices of REST APIs, I tried to always return errors with
HTTP status codes and use URL parameters wherever possible.

### GET /characters/{id}

Returns the character with the specified id.

### GET /characters/?name=Rick

Returns a list of characters with the specified names.

**NOTE**: on the local JPA instance a LIKE query is not issued so the name must match exactly.

### POST /characters

A post to this endpoint with the proper paylod will create an entity
in the local in memory storage. This is done using the CrudRepository
class and inheriting from there.

Try a post with the following payload:

```json
    {
        "id":"AMI",
        "name":"Amigo Mio",
        "gender":"Genderless",
        "image":"https://www.sophya.es/wp-content/uploads/2011/06/shutterstock_283656011_easy-resize-com-1024x682.jpg"
    }
```

NOTES:
- It's not possible to create an entity with the same ID, the check is performed also on the remote APIS.
- The parameters are not validated except the Gender which is an enum. This is a point of improval in my todo notes.

## Approach

The high level approach to the exercise has been to consider since the beginning that we can have multiple data sources in the future, thus each one of them will inherit from the JPA compliant CrudRepository.

You will find in the `repositories` namespace two classes, one mapping to the remote APIs and the other to the local in memory database. Both are instanced as Beans/Singletons and the local database is seeded by the "beans.MemoryDatabaseInitializer" class which runs on every startup.

All these sources are unified in the "CharacterController" which queries both of them before returning results. In the future, it will be sufficient to add a list of datasources and query them parallely to expand the number of datasources.


## TODO
- Unit and integration testing, apologies I need more time for this. We can discuss it in person and I can explain the strategy.
- Implement API and DB pagination/sorting by inheriting from `PagingAndSortingRepository`
- Linting and code style
- More flexibility to add dynamic repositories
- Deduplicate possible duplicate IDs
- Check remote APIs before creating characters locally
- Improve error handling and form validation on creation
- Perform queries on all datasources asynchronously
- Fix a bug to perform a LIKE query against local DB for name search
- Cache API resources from remote endpoint