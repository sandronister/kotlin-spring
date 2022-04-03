package com.mercadolivro.controllers

import com.mercadolivro.extensions.toBookModel
import com.mercadolivro.extensions.toResponse
import com.mercadolivro.request.PostBookRequest
import com.mercadolivro.request.PutBookRequest
import com.mercadolivro.response.BookResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService:CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create((request.toBookModel(customer)))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageble:Pageable):Page<BookResponse>{
        return bookService.findAll(pageble).map { it.toResponse() }
    }

    @GetMapping("/active")
    fun findActive(@PageableDefault(page = 0, size = 10) pageble:Pageable):Page<BookResponse>{
        return bookService.findActive(pageble).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id:Int):BookResponse{
        return bookService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id:Int){
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id:Int,@RequestBody book: PutBookRequest){
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }
}