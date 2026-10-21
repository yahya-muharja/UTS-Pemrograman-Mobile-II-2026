package com.example.utspmii_6304240060.repository

import com.example.utspmii_6304240060.helpers.Config
import com.example.utspmii_6304240060.response.ProductResponse
import com.example.utspmii_6304240060.retrofit.ApiConfig
import com.example.utspmii_6304240060.retrofit.ApiService
import retrofit2.Callback

class ProductRepository {
    private val apiService: ApiService = ApiConfig.getApiService()

    fun getProducts(callback: Callback<ProductResponse>) {
        apiService.getProducts(Config.PRODUCT_LIMIT, Config.PRODUCT_SELECT)
            .enqueue(callback)
    }
}
