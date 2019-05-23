package com.example.recipeworld.model.Entity

object RecipeDetailDTO {
    data class Result(val recipe: Recipe)
    data class Recipe(val image_url: String, val title: String, val social_rank: Double, val publisher: String, val ingredients: String)
}