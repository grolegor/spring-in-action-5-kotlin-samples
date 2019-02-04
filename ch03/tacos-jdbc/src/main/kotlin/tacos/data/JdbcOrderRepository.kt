package tacos.data

import com.fasterxml.jackson.databind.ObjectMapper

import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import tacos.Order
import tacos.Taco

@Configuration
class JdbcOrderRepository @Autowired constructor(val jdbc: JdbcTemplate) : OrderRepository {

    val orderInserter = SimpleJdbcInsert(jdbc)
        .withTableName("Taco_Order")
        .usingGeneratedKeyColumns("id")
    val objectMapper = ObjectMapper()

    override fun save(order: Order): Order {
        order.placedAt = Date()
        val orderId: Long = saveOrderDetails(order)
        order.id = orderId
        order.tacos.forEach { taco -> saveTacoToOrder(taco, orderId) }
        return order
    }

    private fun saveOrderDetails(order: Order): Long {
        @Suppress("UNCHECKED_CAST")
        val values = objectMapper.convertValue(order, Map::class.java) as MutableMap<String, Any?>
        values["placedAt"] = order.placedAt
        return orderInserter.executeAndReturnKey(values).toLong()
    }

    private fun saveTacoToOrder(taco: Taco, orderId: Long) {
        jdbc.update("insert into Taco_Order_Tacos(tacoOrder, taco) values(?, ?)", orderId, taco.id)
    }
}