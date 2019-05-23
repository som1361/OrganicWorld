package com.example.recipeworld.model

import com.example.recipeworld.model.Entity.RecipeDetailDTO
import com.example.recipeworld.model.Entity.RecipeSearchDTO
import com.example.recipeworld.model.Service.RecipeService
import io.reactivex.Single


class RecipeSearchModel {
    val recipeApiServe by lazy {
        RecipeService.create()
    }

fun fetchRecipeList(keyword: String): Single<RecipeSearchDTO.Recipes> {
    return recipeApiServe.searchRecipe("15da9a82656696d6c1b16e258c8fdb17", keyword )
}

}

class RecipeDetailModel {
    val recipeApiServe by lazy {
        RecipeService.create()
    }

    fun getRecipeDetails(recipeId: String): Single<RecipeDetailDTO.Recipe> {
        return recipeApiServe.getRecipe("15da9a82656696d6c1b16e258c8fdb17", recipeId)
    }
}

