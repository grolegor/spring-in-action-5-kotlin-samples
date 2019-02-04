package tacos.data

import tacos.Taco

interface TacoRepository {

    fun save(taco: Taco): Taco
}