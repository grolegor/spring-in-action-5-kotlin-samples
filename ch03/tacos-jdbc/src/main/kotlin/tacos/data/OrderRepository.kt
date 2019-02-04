package tacos.data

import tacos.Order

interface OrderRepository {

    fun save(order: Order): Order
}