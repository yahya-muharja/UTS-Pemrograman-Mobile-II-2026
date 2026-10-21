package com.example.utspmii_6304240060.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.utspmii_6304240060.model.Product
import com.example.utspmii_6304240060.repository.ProductRepository
import com.example.utspmii_6304240060.response.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun loadProducts() {
        _isLoading.value = true
        _errorMessage.value = null

        repository.getProducts(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    val body = response.body()
                    _products.postValue(body?.products ?: emptyList())
                } else {
                    _errorMessage.postValue("Gagal memuat data. Kode status: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _isLoading.postValue(false)
                _errorMessage.postValue("Gagal terhubung ke server. Periksa koneksi internet Anda.")
            }
        })
    }
}
