package tacos

import java.util.Date
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class Taco(

    var id: Long? = null,

    var createdAt: Date? = null,

    @field: NotNull
    @field: Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String? = null,

    @field: Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: List<Ingredient>? = null
)