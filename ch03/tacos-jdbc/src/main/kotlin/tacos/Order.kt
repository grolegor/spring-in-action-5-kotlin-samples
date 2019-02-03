package tacos

import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*

data class Order(

    var id: Long? = null,

    var placedAt: Date? = null,

    @field: NotBlank(message = "Name is required")
    var name: String? = null,

    @field: NotBlank(message = "Street is required")
    var street: String? = null,

    @field: NotBlank(message = "City is required")
    var city: String? = null,

    @field: NotBlank(message = "State is required")
    var state: String? = null,

    @field: NotBlank(message = "Zip code is required")
    var zip: String? = null,

    @field: CreditCardNumber(message = "Not a valid credit card number")
    var ccNumber: String? = null,

    @field: Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    var ccExpiration: String? = null,

    @field: Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    var ccCVV: String? = null
)