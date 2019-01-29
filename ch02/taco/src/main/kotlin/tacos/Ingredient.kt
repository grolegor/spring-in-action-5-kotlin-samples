package tacos

data class Ingredient(
        var id: String,
        var name: String,
        var type: Type
) {

    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}