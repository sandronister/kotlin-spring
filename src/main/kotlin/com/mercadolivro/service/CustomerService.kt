package com.mercadolivro.service

import com.mercadolivro.enum.CustomerStatus
import com.mercadolivro.enum.Errors
import com.mercadolivro.enum.RoleEnum
import com.mercadolivro.exception.BadRequestException
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val repository:CustomerRepository,
    private val bookService:BookService,
    private val bcrypt: BCryptPasswordEncoder
) {

    fun list(name: String?): List<CustomerModel> {
        name?.let {
            return repository.findByNameContaining(it)
        }
        return repository.findAll().toList()
    }

    fun findById(id: Int): CustomerModel {
        return repository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) }
    }

    fun isExixits(customer: CustomerModel) {
        val customerMail = repository.existsByEmail(customer.email)
        if(customerMail){
            throw BadRequestException(Errors.ML401.message,Errors.ML401.code);
        }
    }

    fun postCustomer( customer: CustomerModel){
        isExixits(customer)
        val customerSave = customer.copy(
            roles = setOf(RoleEnum.CUSTOMER),
            password = bcrypt.encode(customer.password)
        )
        repository.save(customerSave)
    }


    fun updateCustomer(customer: CustomerModel) {
       if(!repository.existsById(customer.id!!)){
            throw Exception()
       }

        repository.save((customer))
    }


    fun deleteCustomer(id:Int) {
        var customer = findById(id)
        customer.status = CustomerStatus.INATIVO
        bookService.deleteByCustomer(customer)
        repository.save(customer)
    }
}