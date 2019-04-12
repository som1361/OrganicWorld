package com.example.recipeworld.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.recipeworld.R
import com.example.recipeworld.model.RecipeSearchModel
import com.example.recipeworld.viewmodel.RecipeSearchViewModel
import kotlinx.android.synthetic.main.activity_recipe_search.*

class RecipeSearchActivity : AppCompatActivity() {

    private lateinit var mRecipeSearchViewModel: RecipeSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRecipeSearchViewModel = RecipeSearchViewModel(RecipeSearchModel())
        loadView()
    }

    private fun loadView() {
        setContentView(R.layout.activity_recipe_search)
        hideProgressBar()
        respondToClicks()

    }

    private fun respondToClicks() {
        searchBtn.setOnClickListener({
            showProgressBar()
            mRecipeSearchViewModel.findRecipe(keyword.text.toString())
        })
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE;
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE;
    }
}
