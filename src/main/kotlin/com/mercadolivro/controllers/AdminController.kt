package com.mercadolivro.controllers

import com.mercadolivro.extensions.toCustomerModel
import com.mercadolivro.extensions.toResponse
import com.mercadolivro.controllers.request.PostCustomerRequest
import com.mercadolivro.controllers.request.PutCustomerRequest
import com.mercadolivro.controllers.response.CustomerResponse
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController()
@RequestMapping("admin")
class AdminController() {

    @GetMapping("/report")
    fun list(@RequestParam name:String?): String {
       return "This is is only report,only Admin can see it"
    }
}