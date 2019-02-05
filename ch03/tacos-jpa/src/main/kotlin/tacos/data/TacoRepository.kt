package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.Taco

interface TacoRepository : CrudRepository<Taco, Long>