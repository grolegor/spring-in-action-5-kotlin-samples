package tacos.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tacos.Ingredient
import tacos.data.IngredientRepository

@Component
class IngredientByIdConverter @Autowired constructor(val ingredientRepo: IngredientRepository)
    : Converter<String, Ingredient> {

    override fun convert(id: String): Ingredient? {
        return ingredientRepo.findById(id)
    }
}