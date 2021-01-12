# Debut API

The idea of the exercise is to see how you structure the project, apply good practices and solve the problem. There is no one way to do it but you should be able to defend your work.

## Setup
Clone this repository and only work on your local environment.
- No need to fork the repository
- Do not create Pull Request (they'll be deleted)

It's ready to run using `gradle` or from the main class.

## Exercise Definition

The topic is characters from Rick and Morty.

The idea is to create a simple REST API that's going to combine data from different sources and treat them as one. A 
client should be able to consume this API without knowing the data is coming from more than one source.

### Sources
- Local database. 
    - It doesn't need to be a real database, just storing it on memory would be enough.
    - Model definition defined on [characters-entity-definition.json](https://github.com/debutcareers/debut-be-exercise-api/blob/master/src/main/resources/characters-entity-definition.json)
- First page of Characters endpoint from [Rick and Morty API](https://rickandmortyapi.com/documentation/#rest)

### Endpoints
- Get a list of characters
- Create a new character (only for the local database)
- Search character by name
- Get character by id

## Notes
- The idea of the exercise is to be done in around 3 hours depending on how familiar you are with Kotlin and Spring
- What we are going to check from the project is:
    - Project design, clean and scalable architecture
    - Model design and model mapping
    - REST API working, ideally no bugs
    - Understanding of functional programming
    - Testing
- Try to think a scenario where you could have more than two sources in the future  
- You can use any external library that you see necessary 
- Everything extra you add is going to be well appreciated, but it’s not going to be judge for not adding it
- The result of this exercise is going to be used for your final tech interview

## Delivering
We accept any of these ways for delivery
- Zip project and send it by email
- Create a Github repo and share access to @maxidelo and @valentinholgado


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