package tacos.data

import tacos.Taco

interface TacoRepository {

    fun save(design: Taco): Taco
}