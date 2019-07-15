package com.example.organicworld.viewmodel

import android.widget.EditText
import com.example.organicworld.model.Entity.OrganicSearchDTO
import com.example.organicworld.model.OrganicSearchModel
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException
import io.reactivex.Observable.combineLatest as combineLatest1

class OrganicSearchViewModel (){
    lateinit var resultObservable: PublishSubject<OrganicSearchDTO.Organics>
    lateinit var resultErrorObservable: PublishSubject<HttpException>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var organicModel:OrganicSearchModel

    constructor(mOrganicModel:OrganicSearchModel):this(){
        organicModel = mOrganicModel
        resultObservable = PublishSubject.create()
        resultErrorObservable = PublishSubject.create()
    }

    fun findOrganics(keyword: String)  {
        val disposable: Disposable = organicModel.fetchOrganicList(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<OrganicSearchDTO.Organics>(){
                override fun onSuccess(t: OrganicSearchDTO.Organics) {
                    resultObservable.onNext(t)
                }

                override fun onError(e: Throwable) {
                    resultErrorObservable.onNext(e as HttpException)
                }

            })
        compositeDisposable.add(disposable)
    }

    fun configSearchDebounce(organicText: EditText?) {

        val observable: InitialValueObservable<CharSequence> = organicText!!.textChanges()

    }
}

