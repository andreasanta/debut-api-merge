package careers.debut.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.stereotype.Component

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.json.*

import kotlinx.serialization.*
import kotlinx.serialization.json.*

import org.json.*
import java.util.*

import mu.KLogging

import careers.debut.models.Character

@Component
class APIRepository : CrudRepository<Character, String> {

    val BASE_URL : String = "https://rickandmortyapi.com/api/character" 
    val jsonSerializer = Json { 
        ignoreUnknownKeys = true
        isLenient = true
    }

    override fun count() : Long {
        val (request, response, result) = BASE_URL.httpGet().responseJson()

        when (result) {
            
            is Result.Failure<*> -> {
                val ex = result.getException()
                logger.error(ex) { "Exception while retrieving all chars from API in count"}
                return 0
            }

            is Result.Success -> {
                val data = result.get() as FuelJson
                logger.info { data }

                return data.obj().getJSONObject("info").getLong("count")
            }
        }
    }
    
    override fun delete(entity : Character) {
        throw HttpRequestMethodNotSupportedException("Method Not Allowed")
    }

    override fun deleteAll() {
        throw HttpRequestMethodNotSupportedException("Method Not Allowed")
    }

    override fun deleteAll(entities : Iterable<Character>)
    {
        throw HttpRequestMethodNotSupportedException("Method Not Allowed")
    }

    override fun deleteById(id : String) {
        throw HttpRequestMethodNotSupportedException("Method Not Allowed")
    }


    // Returns whether an entity with the given id exists.
    override fun existsById(id : String) : Boolean {
        val (request, response, result) = (BASE_URL + "/$id")
            .httpGet()
            .responseString()

        when (result) {
            is Result.Failure<*> -> {
                val ex = result.getException()
                logger.error(ex) { "Exception while retrieving all chars from API"}
                return false
            }
            is Result.Success -> {
                return true
            }
        }
    }

    // Returns all instances of the type.
    override fun findAll() : Iterable<Character>
    {
        val (request, response, result) = BASE_URL
            .httpGet()
            .responseJson()

        when (result) {
            is Result.Failure<*> -> {
                val ex = result.getException()
                logger.error(ex) { "Exception while retrieving all chars from API"}
                return listOf()
            }
            is Result.Success -> {
                val data = result.get()
                logger.info { data }
         
                

                // Serialize characters into Model
                return jsonSerializer.decodeFromString<List<Character>>(data.obj().getJSONArray("results").toString())
            }
        }
    }
    
    // Returns all instances of the type T with the given IDs.
    override fun findAllById(ids : Iterable<String>) : Iterable<Character>
    {
        val (request, response, result) = (BASE_URL + ids.joinToString(prefix="/", separator=","))
            .httpGet()
            .responseJson()

        when (result) {
            is Result.Failure<*> -> {
                val ex = result.getException()
                logger.error(ex, { "Exception while retrieving all chars from API" })
                return listOf()
            }
            is Result.Success -> {
                val data = result.get()
                logger.info { data }

                // Serialize characters into Model
                return jsonSerializer.decodeFromString<List<Character>>(data.obj().getJSONArray("results").toString())
            }
        }
    }

    // Retrieves an entity by its id.
 	override fun findById(id : String) : Optional<Character>
    {
        val (request, response, result) = (BASE_URL + "/$id")
            .httpGet()
            .responseJson()

        when (result) {
            is Result.Failure<*> -> {
                val ex = result.getException()
                logger.error(ex) { "Exception while retrieving all chars from API"}
                return Optional.ofNullable(null)
            }
            is Result.Success -> {
                val data = result.get()
                logger.info { data }

                // Serialize characters into Model
                return Optional.of(jsonSerializer.decodeFromString<Character>(data.obj().toString()))
            }
        }
    }

    fun findByName(name: String): Iterable<Character>
    {
        val (request, response, result) = (BASE_URL + "/?name=$name")
            .httpGet()
            .responseJson()

        when (result) {
            is Result.Failure<*> -> {
                val ex = result.getException()
                logger.error(ex) { "Exception while retrieving char by name from API"}
                return listOf()
            }
            is Result.Success -> {
                val data = result.get()
                logger.info { data }

                // Serialize characters into Model
                return jsonSerializer.decodeFromString<List<Character>>(data.obj().getJSONArray("results").toString())
            }
        }
    }
    
    // Saves a given entity.
    override fun <S : Character> save(entity : S) : S
    {
        throw HttpRequestMethodNotSupportedException("Method Not Allowed")
    }

    // Saves all given entities.
    override fun <S : Character> saveAll(entities : Iterable<S>) : Iterable<S>
    {
        throw HttpRequestMethodNotSupportedException("Method Not Allowed")
    }

    companion object : KLogging()
}