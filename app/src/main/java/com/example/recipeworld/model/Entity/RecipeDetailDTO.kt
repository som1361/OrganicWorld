package com.example.recipeworld.model.Entity

object RecipeDetailDTO {
    data class Result(val recipe: Recipe)
    data class Recipe(val title: String, val social_rank: Double, val publisher: String, val ingredients: List<String>)
}