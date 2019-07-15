package com.example.organicworld.model.Entity

object OrganicDetailDTO {
    data class Result(val organic: Organic)
    data class Organic(val title: String, val social_rank: Double, val publisher: String, val ingredients: List<String>)
}