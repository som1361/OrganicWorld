package com.example.recipeworld.model.Service

import com.example.recipeworld.model.Entity.RecipeDetailDTO
import com.example.recipeworld.model.Entity.RecipeSearchDTO
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("search")
    fun searchRecipe(@Query("key") key: String, @Query("q") q: String ) : Single<RecipeSearchDTO.Recipes>

    @GET ("get")
    fun getRecipe(@Query("key") key: String, @Query("rId") rId: String) : Single<RecipeDetailDTO.Result>

    companion object {
        fun create(): RecipeService {
            val retrofit = Retrofit.Builder().addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://www.food2fork.com/api/")
                .build()
            return retrofit.create(RecipeService::class.java)
        }
    }
}