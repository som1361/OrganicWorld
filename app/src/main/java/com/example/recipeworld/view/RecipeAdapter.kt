package com.example.recipeworld.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.recipeworld.R
import com.example.recipeworld.model.Entity.RecipeSearchDTO.Recipe
import com.example.recipeworld.utils.inflate
import kotlinx.android.synthetic.main.recipe_item.view.*

//Make the class extend RecyclerView.ViewHolder, allowing the adapter to use it as as a ViewHolder
class RecipeAdapter(val clickListener: RecipeListener) : RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {
    var recipes: List<Recipe> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeHolder(view!!)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeHolder, position: Int) {
        holder.bindRecipe(clickListener, recipes[position])
    }

    fun updateList(recipeList: List<Recipe>) {
        recipes = recipeList

    }

    class RecipeHolder(v: View) : RecyclerView.ViewHolder(v) {
        //Add a reference to the view you’ve inflated to allow the ViewHolder to access the views as an extension property
        private var view: View = v
        private var recipe: Recipe? = null

        companion object {
            //Add a key for easy reference to the item launching the RecyclerView
            private val RECIPE_KEY = "RECIPE"
        }

        fun bindRecipe(clickListener: RecipeListener, recipe: Recipe) {
            this.recipe = recipe
            Glide.with(view.context).load(recipe.image_url).into(view.itemImage)
            view.itemTitle.text = recipe.title
            view.setOnClickListener { clickListener.onClick(recipe) }
        }
    }
}
class RecipeListener(val clickListener: (recipe: String) -> Unit) {
    fun onClick(recipe: Recipe) = clickListener(recipe.f2f_url.substringAfterLast('/'))
}
