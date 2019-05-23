package com.example.recipeworld.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.recipeworld.R
import com.example.recipeworld.model.RecipeDetailModel
import com.example.recipeworld.viewmodel.RecipeDetailViewModel
import kotlinx.android.synthetic.main.activity_recipe_detail.*

class RecipeDetailActivity: AppCompatActivity() {
    private lateinit var mRecipeDetailViewModel : RecipeDetailViewModel
    var rId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rId = intent.extras.getString("recipeId")
        mRecipeDetailViewModel = RecipeDetailViewModel(RecipeDetailModel())
        loadView()
        listenToObservables()

    }

    private fun loadView() {
        setContentView(R.layout.activity_recipe_detail)
        mRecipeDetailViewModel.getRecipeDetails(rId)
    }

    private fun listenToObservables() {
        mRecipeDetailViewModel.detailResultObservable.subscribe({
            recipe_title.setText(it.recipe.title)
        })
        mRecipeDetailViewModel.detailResultErrorObservable.subscribe({
            showErrorMessage(it.message())
        })
    }

    private fun showErrorMessage(msg: String) {

    }
}