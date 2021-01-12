package careers.debut.repositories

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

import careers.debut.models.Character

// This repo is created automatically by JPA at runtime against the in memory DB
// Thanks to the @EnableJpaRepositories annotation on the application
interface MemoryRepository : JpaRepository<Character, String> { 

    fun findByName(name: String): Iterable<Character>

}
