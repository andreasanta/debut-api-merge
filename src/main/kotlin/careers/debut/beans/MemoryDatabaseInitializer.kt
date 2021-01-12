package careers.debut.beans

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean

import careers.debut.repositories.MemoryRepository
import careers.debut.models.*
import org.springframework.beans.factory.annotation.Autowired

import mu.KLogging

@Configuration
open class MemoryDatabaseInitializer (@Autowired val inMemory : MemoryRepository) {

    @Bean
    fun inMemory() = ApplicationRunner {
        val charList = listOf(
            Character("L1", "Local Char 1", Gender.Male, "https://i.pinimg.com/originals/5b/b4/8b/5bb48b07fa6e3840bb3afa2bc821b882.jpg"),
            Character("L2", "Local Char 2", Gender.Female, "https://www.computerhope.com/jargon/r/random-dice.jpg"),
            Character("L3", "Local Char 3", Gender.unknown, "https://www.hackingwithswift.com/uploads/matrix.jpg"),
            Character("L4", "Local Char 4", Gender.Genderless, "https://dzone.com/storage/temp/12790270-phil-hearing-ybt8wrbpmug-unsplash.jpg"),
            Character("L5", "Local Char 5", Gender.Female, "https://phillipbrande.files.wordpress.com/2013/10/random-pic-14.jpg")
        )

        logger.info { "==> Fake Character Database initialized" }

        inMemory.saveAll(charList)
    }
        
    companion object : KLogging()
}