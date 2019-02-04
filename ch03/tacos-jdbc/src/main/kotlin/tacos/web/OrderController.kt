package tacos.web

import org.springframework.beans.factory.annotation.Autowired
import javax.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import tacos.Order
import tacos.data.OrderRepository

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrderController @Autowired constructor(val orderRepo: OrderRepository) {

    @GetMapping("/current")
    fun orderForm() = "orderForm"

    @PostMapping
    fun processOrder(@Valid order: Order, errors: Errors, sessionStatus: SessionStatus): String {
        if (errors.hasErrors()) return "orderForm"
        orderRepo.save(order)
        sessionStatus.setComplete()
        return "redirect:/"
    }
}