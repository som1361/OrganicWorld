package com.example.organicworld.viewmodel

import com.example.organicworld.model.Entity.OrganicDetailDTO
import com.example.organicworld.model.OrganicDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException

class OrganicDetailViewModel (){
    lateinit var detailResultObservable: PublishSubject<OrganicDetailDTO.Result>
    lateinit var detailResultErrorObservable: PublishSubject<HttpException>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var organicDetailModel:OrganicDetailModel

    constructor(mOrganicModel:OrganicDetailModel):this(){
        organicDetailModel = mOrganicModel
        detailResultObservable = PublishSubject.create()
        detailResultErrorObservable = PublishSubject.create()
    }

    fun getOrganicDetails(organicId: String)  {
        val disposable: Disposable = organicDetailModel.getOrganicDetails(organicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<OrganicDetailDTO.Result>(){
                override fun onSuccess(t: OrganicDetailDTO.Result) {
                    detailResultObservable.onNext(t)
                }

                override fun onError(e: Throwable) {
                    detailResultErrorObservable.onNext(e as HttpException)
                }

            })
        compositeDisposable.add(disposable)
    }

}