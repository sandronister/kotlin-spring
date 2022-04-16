package com.mercadolivro.controllers

import com.mercadolivro.controllers.mapper.PurchaseMapper
import com.mercadolivro.controllers.request.PostPurchaseRequest
import com.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("purchase")
class PurchaseController(
    val purchaseService: PurchaseService,
    val purchaseMapper:PurchaseMapper
) {



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchaseCreate(@RequestBody request: PostPurchaseRequest){
        purchaseService.create(purchaseMapper.toModel(request))
    }

}