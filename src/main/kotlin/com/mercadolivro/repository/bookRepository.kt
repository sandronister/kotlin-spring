package com.mercadolivro.repository

import com.mercadolivro.enum.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel,Int> {
    abstract fun findByStatus(satus: BookStatus): List<BookModel>
    abstract fun findByCustomer(customer: CustomerModel): List<BookModel>
}