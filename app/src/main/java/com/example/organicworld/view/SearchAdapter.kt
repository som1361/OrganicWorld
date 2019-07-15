package com.example.organicworld.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.organicworld.R
import com.example.organicworld.model.Entity.OrganicSearchDTO.Organic
import kotlinx.android.synthetic.main.product_item.view.*

//Make the class extend RecyclerView.ViewHolder, allowing the adapter to use it as as a ViewHolder
class SearchAdapter(val clickListener: OrganicListener) : RecyclerView.Adapter<SearchAdapter.OrganicHolder>() {
    var organics: List<Organic> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.OrganicHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.product_item, parent, false)
        return OrganicHolder(view!!)
    }

    override fun getItemCount() = organics.size

    override fun onBindViewHolder(holder: SearchAdapter.OrganicHolder, position: Int) {
        holder.bindOrganic(clickListener, organics[position])
    }

    fun updateList(organicList: List<Organic>) {
        organics = organicList

    }

    class OrganicHolder(v: View) : RecyclerView.ViewHolder(v) {
        //Add a reference to the view you’ve inflated to allow the ViewHolder to access the views as an extension property
        private var view: View = v
        private var organic: Organic? = null

        companion object {
            //Add a key for easy reference to the item launching the RecyclerView
            private val RECIPE_KEY = "RECIPE"
        }

        fun bindOrganic(clickListener: OrganicListener, organic: Organic) {
            this.organic = organic
            Glide.with(view.context).load(organic.image_url).into(view.itemImage)
            view.itemTitle.text = organic.title
            view.setOnClickListener { clickListener.onClick(organic) }
        }
    }
}
class OrganicListener(val clickListener: (organic: String) -> Unit) {
    fun onClick(organic: Organic) = clickListener(organic.f2f_url.substringAfterLast('/'))
}
