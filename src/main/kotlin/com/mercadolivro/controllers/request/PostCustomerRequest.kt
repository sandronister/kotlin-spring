package com.mercadolivro.controllers.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message="É necessário informar o nome")
    val name:String,

    @field:Email(message="Informe um e-mail válido")
    val email:String,

    @field:NotEmpty(message="É obrigatório informar o password")
    val password:String
    )
