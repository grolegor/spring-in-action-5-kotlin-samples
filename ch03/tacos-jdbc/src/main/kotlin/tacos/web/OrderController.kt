package tacos.web

import javax.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import tacos.Order

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrderController {

    @GetMapping("/current")
    fun orderForm() = "orderForm"

    @PostMapping
    fun processOrder(@Valid order: Order, errors: Errors): String {
        if (errors.hasErrors()) return "orderForm"
        println("Oder submitted: $order")
        return "redirect:/"
    }
}