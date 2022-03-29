package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(val repository:CustomerRepository) {

    fun list(name:String?):List<CustomerModel>{
        name?.let {
            return repository.findByNameContaining(it)
        }
        return repository.findAll().toList()
    }

    fun getCustomer(id:Int): CustomerModel {
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
        if(!repository.existsById(id)){
            throw Exception()
        }

        repository.deleteById(id)
    }
}