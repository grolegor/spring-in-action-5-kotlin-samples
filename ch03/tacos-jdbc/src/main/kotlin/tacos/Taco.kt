package tacos

import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class Taco(

    var id: Long? = null,

    var placedAt: Date? = null,

    @field: NotNull
    @field: Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String? = null,

    @field: Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: List<String>? = null
)