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
       return service.list(name)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id:Int): CustomerModel {
        return service.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCustomer(@RequestBody customer:PostCustomerRequest){
        service.create(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id:Int,@RequestBody customer:PutCustomerRequest) {
       service.updateCustomer(customer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id:Int) {
        service.deleteCustomer(id)
    }
}