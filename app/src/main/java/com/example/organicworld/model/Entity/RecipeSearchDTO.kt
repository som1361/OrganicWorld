package com.example.organicworld.model.Entity

object OrganicSearchDTO {
    data class Organics(val recipes: List<Product>)
    data class Product(val image_url: String, val title: String, val social_rank: Double, val f2f_url: String, val publisher: String)
}