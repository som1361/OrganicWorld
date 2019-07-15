package com.example.organicworld.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MotionEvent
import android.view.View
import com.example.organicworld.R
import com.example.organicworld.model.Entity.OrganicSearchDTO
import com.example.organicworld.model.OrganicSearchModel
import com.example.organicworld.viewmodel.OrganicSearchViewModel
import com.example.webviewscreenshot.utils.hide
import com.example.webviewscreenshot.utils.hideSoftKeyboard
import com.example.webviewscreenshot.utils.show
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var mOrganicSearchViewModel: OrganicSearchViewModel
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var mOrganicAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mOrganicSearchViewModel = OrganicSearchViewModel(OrganicSearchModel())
        loadView()
        respondToClicks()
        listenToObservables()

    }

    private fun listenToObservables() {
        mOrganicSearchViewModel.resultObservable.subscribe({
search_progress_bar.hide()
            updateOrganicList(it)
        })
        mOrganicSearchViewModel.resultErrorObservable.subscribe({
            search_progress_bar.hide()
            showErrorMessage(it.message())
        })
    }

    private fun updateOrganicList(it: OrganicSearchDTO.Organics?) {
        if (it != null) {
            mOrganicAdapter.updateList(it.recipes)
            mOrganicAdapter.notifyDataSetChanged()

        }
    }

    private fun showErrorMessage(msg: String) {

    }

    private fun loadView() {
        setContentView(R.layout.activity_search)
        mOrganicAdapter = SearchAdapter(OrganicListener { organicId ->
            goToDetailActivity(organicId)
        })
        mGridLayoutManager = GridLayoutManager(this, 2)
        search_recyclerview.layoutManager = mGridLayoutManager
        search_recyclerview.adapter = mOrganicAdapter
        search_progress_bar.hide()
    }

    private fun goToDetailActivity(organicId: String) {
        var bundle = Bundle()
        bundle.putString("organicId", organicId)
        var intent = Intent(this, DetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun respondToClicks() {

        search_imageView.setOnClickListener({
            search_progress_bar.show()
            mOrganicSearchViewModel.findOrganics(search_editText.text.toString())
        })

        search_layout.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                this@SearchActivity.hideSoftKeyboard()
                return true
            }
        })
    }
}
