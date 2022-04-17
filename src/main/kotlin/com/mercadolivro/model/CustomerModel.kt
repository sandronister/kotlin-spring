package com.mercadolivro.model

import com.mercadolivro.enum.CustomerStatus
import com.mercadolivro.enum.RoleEnum
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int?=null,

    @Column
    var name:String,

    @Column
    var email:String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column
    var password:String,

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="customer_role", joinColumns = [JoinColumn(name="customer_id")])
    @ElementCollection(targetClass = RoleEnum::class, fetch = FetchType.EAGER)
    var roles: Set<RoleEnum> = setOf()
)
