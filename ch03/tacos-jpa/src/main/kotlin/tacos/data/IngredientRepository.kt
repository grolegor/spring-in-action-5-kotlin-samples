package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.Ingredient

interface IngredientRepository : CrudRepository<Ingredient, String>