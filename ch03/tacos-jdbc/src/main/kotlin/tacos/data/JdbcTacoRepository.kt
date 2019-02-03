package tacos.data

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import tacos.Ingredient
import tacos.Taco
import java.util.*

@Repository
class JdbcTacoRepository @Autowired constructor(private val jdbc: JdbcTemplate) : TacoRepository {

    private val tacoInserter: SimpleJdbcInsert = SimpleJdbcInsert(jdbc)
        .withTableName("Taco")
        .usingGeneratedKeyColumns("id")

    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun save(taco: Taco): Taco {
        taco.placedAt = Date()
        val tacoId: Long = saveTacoDetails(taco)
        taco.ingredients?.forEach {ingredient -> saveIngredientToTaco(ingredient, tacoId) }
        return taco
    }

    private fun saveTacoDetails(taco: Taco): Long {
        @Suppress("UNCHECKED_CAST")
        val values = objectMapper.convertValue(taco, Map::class.java) as MutableMap<String, Any?>
        values["placedAt"] = taco.placedAt
        return tacoInserter.executeAndReturnKey(values).toLong()
    }

    private fun saveIngredientToTaco(ingredient: Ingredient, tacoId: Long) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) values values (?, ?)", tacoId, ingredient.id)
    }
}