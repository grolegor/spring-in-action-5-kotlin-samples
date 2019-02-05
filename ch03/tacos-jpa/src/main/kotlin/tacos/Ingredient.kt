package tacos

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Ingredient(
    @Id
    var id: String,
    var name: String,
    var type: Type
) {

    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}