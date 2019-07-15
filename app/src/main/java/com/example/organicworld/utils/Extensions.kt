package com.example.webviewscreenshot.utils

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.util.PatternsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.graphics.Canvas

fun ProgressBar.show(){
    this.visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    this.visibility = View.GONE
}

fun View.loadBitmap(): Bitmap {
    val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val c = Canvas(b)
    draw(c)
    return b
}

