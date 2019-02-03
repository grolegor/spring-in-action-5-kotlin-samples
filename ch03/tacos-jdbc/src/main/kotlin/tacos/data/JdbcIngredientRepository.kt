package tacos.data

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import tacos.Ingredient
import java.sql.ResultSet

@Repository
class JdbcIngredientRepository @Autowired constructor(private val jdbc: JdbcTemplate) : IngredientRepository {

    override fun findAll(): Iterable<Ingredient> {
        return jdbc.query("select id, name, type from Ingredient", ::mapRowToIngredient)
    }

    override fun findById(id: String): Ingredient? {
        return jdbc.queryForObject(
            "select id, name, type from Ingredient where id=?",
            ::mapRowToIngredient,
            arrayOf(id))
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
            ingredient.id,
            ingredient.name,
            ingredient.type)
        return ingredient
    }

    fun mapRowToIngredient(rs: ResultSet, rowNum: Int) = Ingredient(
        rs.getString("id"),
        rs.getString("name"),
        Ingredient.Type.valueOf(rs.getString("type")))
}