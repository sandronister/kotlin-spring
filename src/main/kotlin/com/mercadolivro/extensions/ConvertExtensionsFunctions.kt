package com.mercadolivro.extensions

import com.mercadolivro.enum.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.request.PostBookRequest
import com.mercadolivro.request.PostCustomerRequest
import com.mercadolivro.request.PutCustomerRequest

fun PostCustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name=this.name,email=this.email)
}

fun PutCustomerRequest.toCustomerModel(id:Int):CustomerModel{
    return CustomerModel(id=id,name=this.name,email=this.email)
}

fun PostBookRequest.toBookModel(customer:CustomerModel):BookModel{
    return BookModel(
        name=this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}