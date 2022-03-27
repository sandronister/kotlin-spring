package com.mercadolivro.controllers

import com.mercadolivro.extensions.toCustomerModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.request.PostCustomerRequest
import com.mercadolivro.request.PutCustomerRequest
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController()
@RequestMapping("customer")
class CustomerController(
    val service:CustomerService
) {

    @GetMapping
    fun list(@RequestParam name:String?):List<CustomerModel>{
       return service.list((name))
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id:String): CustomerModel {
        return service.getCustomer(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCustomer(@RequestBody customer:PostCustomerRequest){
        service.postCustomer(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id:String,@RequestBody customer:PutCustomerRequest) {
       service.updateCustomer(customer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id:String) {
        service.deleteCustomer(id)
    }
}