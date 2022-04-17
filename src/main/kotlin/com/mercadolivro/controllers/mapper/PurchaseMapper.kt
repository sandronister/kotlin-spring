package com.mercadolivro.controllers.mapper

import com.mercadolivro.controllers.request.PostPurchaseRequest
import com.mercadolivro.enum.BookStatus
import com.mercadolivro.enum.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    val bookService:BookService,
    val customerService:CustomerService
) {

    fun toModel(request: PostPurchaseRequest):PurchaseModel{
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        val failStatus = books.filter{it.status!=BookStatus.ATIVO}

        if(failStatus.size!=0){
            throw NotFoundException(Errors.ML301.message,Errors.ML301.code)
        }

        return PurchaseModel(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )
    }
}