package com.example.recipeworld.viewmodel

import com.example.recipeworld.model.Entity.RecipeDetailDTO
import com.example.recipeworld.model.RecipeDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException

class RecipeDetailViewModel (){
    lateinit var detailResultObservable: PublishSubject<RecipeDetailDTO.Result>
    lateinit var detailResultErrorObservable: PublishSubject<HttpException>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var recipeDetailModel:RecipeDetailModel

    constructor(mRecipeModel:RecipeDetailModel):this(){
        recipeDetailModel = mRecipeModel
        detailResultObservable = PublishSubject.create()
        detailResultErrorObservable = PublishSubject.create()
    }

    fun getRecipeDetails(recipeId: String)  {
        val disposable: Disposable = recipeDetailModel.getRecipeDetails(recipeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<RecipeDetailDTO.Result>(){
                override fun onSuccess(t: RecipeDetailDTO.Result) {
                    detailResultObservable.onNext(t)
                }

                override fun onError(e: Throwable) {
                    detailResultErrorObservable.onNext(e as HttpException)
                }

            })
        compositeDisposable.add(disposable)
    }

}