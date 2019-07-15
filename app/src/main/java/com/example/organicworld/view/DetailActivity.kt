package com.example.organicworld.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.organicworld.R
import com.example.organicworld.model.OrganicDetailModel
import com.example.organicworld.viewmodel.OrganicDetailViewModel

class DetailActivity: AppCompatActivity() {
    private lateinit var mOrganicDetailViewModel : OrganicDetailViewModel
    var rId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rId = intent.extras.getString("organicId")
        mOrganicDetailViewModel = OrganicDetailViewModel(OrganicDetailModel())
        loadView()
        listenToObservables()

    }

    private fun loadView() {
        setContentView(R.layout.activity_detail)
        mOrganicDetailViewModel.getOrganicDetails(rId)
    }

    private fun listenToObservables() {
        mOrganicDetailViewModel.detailResultObservable.subscribe({
            //.setText(it.organic.title)
        })
        mOrganicDetailViewModel.detailResultErrorObservable.subscribe({
            showErrorMessage(it.message())
        })
    }

    private fun showErrorMessage(msg: String) {

    }
}