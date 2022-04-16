package com.mercadolivro.controllers.request

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PostPurchaseRequest(

    @JsonAlias("customer_id")
    @field:NotNull
    @field:Positive
    val customerId:Int,

    @field:NotNull
    @JsonAlias("books_id")
    val bookIds:Set<Int>
)
