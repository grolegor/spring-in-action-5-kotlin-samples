package tacos.web

import javax.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tacos.Order

@Controller
@RequestMapping("/orders")
class OrderController {

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        model.addAttribute("order", Order())
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid order: Order, errors: Errors): String {
        if (errors.hasErrors()) return "orderForm"
        println("Oder submitted: $order")
        return "redirect:/"
    }
}