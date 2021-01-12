package careers.debut.controllers

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.beans.factory.annotation.Autowired

import careers.debut.repositories.*
import careers.debut.models.Character

@RepositoryRestController
class CharacterController
(
    @Autowired val inMemory : MemoryRepository,
    val inApi : APIRepository
)
{
    @GetMapping("/characters")
    fun list(@RequestParam name: String?) : ResponseEntity<*> {    
        if (name == null)
            return ResponseEntity.ok(inApi.findAll() + inMemory.findAll())
        else
            return ResponseEntity.ok(inApi.findByName(name) + inMemory.findByName(name))
    }

    @GetMapping("/characters/{id}")
    fun find(@PathVariable id : String) : ResponseEntity<*> {    
        val obj = inApi.findById(id).orElse(inMemory.findById(id).orElse(null))

        if (obj == null)
            return ResponseEntity("Character not found", NOT_FOUND)

        return ResponseEntity.ok(obj)
    }
    
    @PostMapping("/characters")
    fun post(@RequestBody entity : Character) : ResponseEntity<*> {

        // Ensure the character ID does not exist!
        if (inMemory.findById(entity.id.trim()).isPresent() || inApi.findById(entity.id.trim()).isPresent())
            return ResponseEntity("Character with same ID already exists", CONFLICT)

        inMemory.save(entity)

        return ResponseEntity.ok(entity)
    }
}