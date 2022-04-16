package com.mercadolivro.model

import net.bytebuddy.dynamic.loading.InjectionClassLoader.Strategy
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity(name = "purchase")
data class PurchaseModel(

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id:Int?=null,

    @ManyToOne
    @JoinColumn(name="customer_id")
    val customer:CustomerModel,

    @ManyToMany
    @JoinTable(name="purchase_book",
        joinColumns = [JoinColumn(name="purchase_id")],
        inverseJoinColumns = [JoinColumn(name="book_id")]
    )
    val books:MutableList<BookModel>,


    @Column
    val nfe:String?=null,

    @Column
    val price:BigDecimal,

    @Column(name="created_at")
    val created_at:LocalDateTime = LocalDateTime.now()


);