package com.mercadolivro.extensions

import com.mercadolivro.enum.BookStatus
import com.mercadolivro.enum.CustomerStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.controllers.request.PostBookRequest
import com.mercadolivro.controllers.request.PostCustomerRequest
import com.mercadolivro.controllers.request.PutBookRequest
import com.mercadolivro.controllers.request.PutCustomerRequest
import com.mercadolivro.controllers.response.BookResponse
import com.mercadolivro.controllers.response.CustomerResponse

fun PostCustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name=this.name,email=this.email, status=CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue:CustomerModel):CustomerModel{
    return CustomerModel(
        id=previousValue.id,
        name=this.name ?: previousValue.name,
        email=this.email ?: previousValue.email,
        status = previousValue.status
    )
}

fun PostBookRequest.toBookModel(customer:CustomerModel):BookModel{
    return BookModel(
        name=this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previosValue:BookModel):BookModel{
    return BookModel(
        id = previosValue.id,
        name = this.name ?: previosValue.name,
        price = this.price ?: previosValue.price,
        status = previosValue.status,
        customer = previosValue.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id=this.id,
        name=this.name,
        email=this.email,
        status=this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id=this.id,
        name=this.name,
        price=this.price,
        customer=this.customer,
        status=this.status
    )
}