package tacos.web

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

@Controller
@RequestMapping("/design")
class DesignTacoController {

    @ModelAttribute
    fun addInrgedientsToModel(model: Model) {
        val ingredients = listOf(
            Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
            Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
            Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        )
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