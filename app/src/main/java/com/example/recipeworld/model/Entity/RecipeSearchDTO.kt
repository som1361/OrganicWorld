package com.example.recipeworld.model.Entity

object RecipeSearchDTO {
    data class Result(val recipes: Recipes)
    data class Recipes(val recipes: List<Recipe>)
    data class Recipe(val image_url: String, val title: String, val social_rank: Double, val f2f_url: String)
}