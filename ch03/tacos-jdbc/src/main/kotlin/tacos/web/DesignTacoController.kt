package tacos.web

import org.springframework.beans.factory.annotation.Autowired
import javax.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tacos.Ingredient
import tacos.Taco
import tacos.data.IngredientRepository

@Controller
@RequestMapping("/design")
class DesignTacoController @Autowired constructor(val ingredientRepo: IngredientRepository) {

    @ModelAttribute
    fun addInrgedientsToModel(model: Model) {
        val ingredients = ingredientRepo.findAll()
        Ingredient.Type.values().forEach { type ->
            model.addAttribute(type.toString().toLowerCase(),
                ingredients.filter { it.type == type })
        }
    }

    @GetMapping
    fun showDesignForm(model: Model): String {
        model.addAttribute("design", Taco())
        return "design"
    }

    @PostMapping
    fun processDesign(@Valid @ModelAttribute("design") design: Taco, errors: Errors): String {
        if (errors.hasErrors()) return "design"
        println("Processing design: $design")
        return "redirect:/orders/current"
    }
}