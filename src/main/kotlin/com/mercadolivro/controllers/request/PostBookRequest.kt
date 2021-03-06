package com.mercadolivro.controllers.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

data class PostBookRequest(

    val name: String,

    val price:BigDecimal,

    @JsonAlias("customer_id")
    val customerId:Int
)
