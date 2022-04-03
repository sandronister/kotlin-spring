package com.mercadolivro.controllers

import com.mercadolivro.extensions.toCustomerModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.request.PostCustomerRequest
import com.mercadolivro.request.PutCustomerRequest
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController()
@RequestMapping("customer")
class CustomerController(
    val customerService:CustomerService
) {

    @GetMapping
    fun list(@RequestParam name:String?):List<CustomerModel>{
       return customerService.list(name)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id:Int): CustomerModel {
        return customerService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCustomer(@RequestBody customer:PostCustomerRequest){
        customerService.postCustomer(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id:Int,@RequestBody customer:PutCustomerRequest) {
       val customerSaved =  customerService.findById(id)
       customerService.updateCustomer(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id:Int) {
        customerService.deleteCustomer(id)
    }
}