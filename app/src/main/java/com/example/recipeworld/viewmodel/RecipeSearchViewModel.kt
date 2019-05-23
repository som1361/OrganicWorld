package com.example.recipeworld.viewmodel

import com.example.recipeworld.model.Entity.RecipeSearchDTO
import com.example.recipeworld.model.RecipeSearchModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException

class RecipeSearchViewModel (){
    lateinit var resultObservable: PublishSubject<RecipeSearchDTO.Recipes>
    lateinit var resultErrorObservable: PublishSubject<HttpException>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var recipeModel:RecipeSearchModel

    constructor(mRecipeModel:RecipeSearchModel):this(){
        recipeModel = mRecipeModel
        resultObservable = PublishSubject.create()
        resultErrorObservable = PublishSubject.create()
    }

    fun findRecipes(keyword: String)  {
        val disposable: Disposable = recipeModel.fetchRecipeList(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<RecipeSearchDTO.Recipes>(){
                override fun onSuccess(t: RecipeSearchDTO.Recipes) {
                    resultObservable.onNext(t)
                }

                override fun onError(e: Throwable) {
                    resultErrorObservable.onNext(e as HttpException)
                }

            })
        compositeDisposable.add(disposable)
    }
}