package careers.debut

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.autoconfigure.domain.EntityScan

@SpringBootApplication
@EnableJpaRepositories("careers.debut")
@EntityScan("careers.debut")

class DebutApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(DebutApiApplication::class.java, *args)
}
