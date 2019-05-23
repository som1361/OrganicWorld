package com.example.recipeworld.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.recipeworld.R
import com.example.recipeworld.model.Entity.RecipeSearchDTO
import com.example.recipeworld.model.RecipeSearchModel
import com.example.recipeworld.utils.hideKeyboard
import com.example.recipeworld.viewmodel.RecipeSearchViewModel
import kotlinx.android.synthetic.main.activity_recipe_search.*

class RecipeSearchActivity : AppCompatActivity() {

    private lateinit var mRecipeSearchViewModel: RecipeSearchViewModel
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var mRecipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRecipeSearchViewModel = RecipeSearchViewModel(RecipeSearchModel())
        loadView()
        respondToClicks()
        listenToObservables()

    }

    private fun listenToObservables() {
        mRecipeSearchViewModel.resultObservable.subscribe({
            hideProgressBar()
            updateRecipeList(it)
        })
        mRecipeSearchViewModel.resultErrorObservable.subscribe({
            hideProgressBar()
            showErrorMessage(it.message())
        })
    }

    private fun updateRecipeList(it: RecipeSearchDTO.Recipes?) {
        if (it != null) {
            mRecipeAdapter.updateList(it.recipes)
            mRecipeAdapter.notifyDataSetChanged()

        }
    }

    private fun showErrorMessage(msg: String) {

    }

    private fun loadView() {
        setContentView(R.layout.activity_recipe_search)
        mRecipeAdapter = RecipeAdapter(RecipeListener { recipeId ->
            //Toast.makeText(this, "${recipeId}", Toast.LENGTH_LONG).show()
            goToDetailActivity(recipeId)
        })
        mGridLayoutManager = GridLayoutManager(this, 2)
        recipe_RecyclerView.layoutManager = mGridLayoutManager
        recipe_RecyclerView.adapter = mRecipeAdapter
        hideProgressBar()

    }

    private fun goToDetailActivity(recipeId: String) {
       var bundle = Bundle()
        bundle.putString("recipeId", recipeId)
        var intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun respondToClicks() {
        searchBtn.setOnClickListener({
            showProgressBar()
            recipe_search_text.hideKeyboard()
            mRecipeSearchViewModel.findRecipes(recipe_search_text.text.toString())
        })
    }

    private fun hideProgressBar() {
        recipe_search_progress_bar.visibility = View.GONE;
    }

    private fun showProgressBar() {
        recipe_search_progress_bar.visibility = View.VISIBLE;
    }
}
