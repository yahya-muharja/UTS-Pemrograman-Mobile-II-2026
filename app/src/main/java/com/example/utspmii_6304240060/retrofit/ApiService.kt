package com.example.utspmii_6304240060.retrofit

import com.example.utspmii_6304240060.model.Product
import com.example.utspmii_6304240060.response.Category
import com.example.utspmii_6304240060.response.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    fun getProducts(
        @Query("limit") limit: Int,
        @Query("select") select: String
    ): Call<ProductResponse>

    @GET("products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

    @GET("products/categories")
    fun getCategories(): Call<List<Category>>
}
