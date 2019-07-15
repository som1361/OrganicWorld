package com.example.organicworld.model.Entity

object OrganicSearchDTO {
    data class Result(val organics: Organics)
    data class Organics(val organics: List<Organic>)
    data class Organic(val image_url: String, val title: String, val social_rank: Double, val f2f_url: String)
}