package com.mercadolivro.service

import com.mercadolivro.enum.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val repository:BookRepository
) {

    fun create(book:BookModel){
        repository.save(book)
    }

    fun findAll(pageble:Pageable): Page<BookModel> {
        return repository.findAll(pageble)
    }

    fun findActive(pageble: Pageable): Page<BookModel> {
        return repository.findByStatus(BookStatus.ATIVO,pageble)
    }

    fun findById(id: Int): BookModel {
        return  repository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        var book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun update(book: BookModel) {
        repository.save(book)
    }

    fun deleteByCustomer(customer:CustomerModel) {
        var books = repository.findByCustomer(customer)
        books.map { it -> it.status=BookStatus.DELETADO }
        repository.saveAll(books)
    }

}
