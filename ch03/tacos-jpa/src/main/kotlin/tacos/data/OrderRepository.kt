package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.Order

interface OrderRepository : CrudRepository<Order, Long>