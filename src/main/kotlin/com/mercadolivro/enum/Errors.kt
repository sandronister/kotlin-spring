package com.mercadolivro.enum

enum class Errors(val message:String,val code:String) {

    ML001("Invalid Request","ML-001"),
    ML101("Book [%S] not found","ML-101"),
    ML102("Cannot update book with status [%s]","ML-102"),
    ML201("Customer [%S] not found","ML-201")
}