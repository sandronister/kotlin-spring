package com.mercadolivro.service

import com.mercadolivro.enum.CustomerStatus
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val repository:CustomerRepository,
    val bookService:BookService ) {

    fun list(name:String?):List<CustomerModel>{
        name?.let {
            return repository.findByNameContaining(it)
        }
        return repository.findAll().toList()
    }

    fun findById(id:Int): CustomerModel {
        return  repository.findById(id).orElseThrow()
    }

    fun postCustomer( customer: CustomerModel){
        repository.save(customer)
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