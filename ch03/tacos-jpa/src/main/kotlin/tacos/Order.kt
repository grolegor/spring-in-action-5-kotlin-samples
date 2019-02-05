package tacos

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.PrePersist
import javax.persistence.Table
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import org.hibernate.validator.constraints.CreditCardNumber

@Entity
@Table(name = "Taco_Order")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @ManyToMany(targetEntity = Taco::class)
    var tacos: MutableList<Taco> = mutableListOf(),

    var placedAt: Date? = null,

    @field: NotBlank(message = "Name is required")
    var deliveryName: String? = null,

    @field: NotBlank(message = "Street is required")
    var deliveryStreet: String? = null,

    @field: NotBlank(message = "City is required")
    var deliveryCity: String? = null,

    @field: NotBlank(message = "State is required")
    var deliveryState: String? = null,

    @field: NotBlank(message = "Zip code is required")
    var deliveryZip: String? = null,

    @field: CreditCardNumber(message = "Not a valid credit card number")
    var ccNumber: String? = null,

    @field: Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    var ccExpiration: String? = null,

    @field: Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    var ccCVV: String? = null
) {
    @PrePersist
    fun placedAt() {
        placedAt = Date()
    }
}