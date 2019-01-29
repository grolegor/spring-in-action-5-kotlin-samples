package tacos

import org.hibernate.validator.constraints.CreditCardNumber
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class Order(

        @NotBlank(message = "Name is required")
        var name: String,

        @NotBlank(message = "Street is required")
        var street: String,

        @NotBlank(message = "City is required")
        var city: String,

        @NotBlank(message = "State is required")
        var state: String,

        @NotBlank(message = "Zip code is required")
        var zip: String,

        @CreditCardNumber(message = "Not a valid credit card number")
        var ccNumber: String,

        @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
        var ccExpiration: String,

        @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
        var ccCVV: String
)