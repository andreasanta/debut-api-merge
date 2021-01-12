package careers.debut.models

import kotlinx.serialization.*
import kotlinx.serialization.json.*

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

enum class Gender { 
    Male,
    Female,
    Genderless,
    unknown
}

/* 
    Simple character model for Rick & Morty and more :)

    Use the new Kotlin Serialization APIs for JSON decoding/encoding.
*/
@Serializable
@Entity
data class Character(
    
    @Id
    val id: String = "",
    val name: String = "",
    val gender: Gender = Gender.unknown,
    val image: String = ""
)