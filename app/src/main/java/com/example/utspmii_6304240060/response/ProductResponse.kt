package com.example.utspmii_6304240060.response

import com.example.utspmii_6304240060.model.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(

    @field:SerializedName("products")
    val products: List<Product> = emptyList(),

    @field:SerializedName("total")
    val total: Int = 0,

    @field:SerializedName("skip")
    val skip: Int = 0,

    @field:SerializedName("limit")
    val limit: Int = 0
)

data class Category(

    @field:SerializedName("slug")
    val slug: String = "",

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("url")
    val url: String = ""
)
