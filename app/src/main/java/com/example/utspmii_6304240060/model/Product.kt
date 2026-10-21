package com.example.utspmii_6304240060.model

import com.google.gson.annotations.SerializedName

data class Product(

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("description")
    val description: String = "",

    @field:SerializedName("category")
    val category: String = "",

    @field:SerializedName("price")
    val price: Double = 0.0,

    @field:SerializedName("stock")
    val stock: Int = 0,

    @field:SerializedName("availabilityStatus")
    val availabilityStatus: String = "",

    @field:SerializedName("tags")
    val tags: List<String> = emptyList(),

    @field:SerializedName("dimensions")
    val dimensions: Dimensions = Dimensions(),

    @field:SerializedName("reviews")
    val reviews: List<Review> = emptyList(),

    @field:SerializedName("rating")
    val rating: Double = 0.0,

    @field:SerializedName("images")
    val images: List<String> = emptyList(),

    @field:SerializedName("thumbnail")
    val thumbnail: String = ""
) {
    val mainImage: String
        get() = images.firstOrNull() ?: thumbnail
}

data class Dimensions(

    @field:SerializedName("width")
    val width: Double = 0.0,

    @field:SerializedName("height")
    val height: Double = 0.0,

    @field:SerializedName("depth")
    val depth: Double = 0.0
)

data class Review(

    @field:SerializedName("rating")
    val rating: Int = 0,

    @field:SerializedName("comment")
    val comment: String = "",

    @field:SerializedName("date")
    val date: String = "",

    @field:SerializedName("reviewerName")
    val reviewerName: String = "",

    @field:SerializedName("reviewerEmail")
    val reviewerEmail: String = ""
)
