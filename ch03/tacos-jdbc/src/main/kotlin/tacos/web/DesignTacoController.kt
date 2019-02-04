package tacos.web

import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import tacos.Ingredient
import tacos.Order
import tacos.Taco
import tacos.data.IngredientRepository
import tacos.data.TacoRepository

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
class DesignTacoController @Autowired constructor(
    val ingredientRepo: IngredientRepository,
    val tacoRepo: TacoRepository
) {

    @ModelAttribute(name = "taco")
    fun taco() = Taco()

    @ModelAttribute(name = "order")
    fun order() = Order()

    @GetMapping
    fun showDesignForm(model: Model): String {
        val ingredients = ingredientRepo.findAll()
        Ingredient.Type.values().forEach { type ->
            model.addAttribute(type.toString().toLowerCase(),
                ingredients.filter { it.type == type })
        }
        return "design"
    }

    @PostMapping
    fun processDesign(@Valid design: Taco, errors: Errors, @ModelAttribute order: Order): String {
        if (errors.hasErrors()) return "design"
        order.tacos.add(tacoRepo.save(design))
        return "redirect:/orders/current"
    }
}