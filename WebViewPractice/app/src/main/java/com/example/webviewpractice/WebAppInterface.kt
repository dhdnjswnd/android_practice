package com.example.webviewpractice

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class WebAppInterface(private val mContext : Context) {
    // 웹에서 안드로이드 toastmessage 띄우기!!?
    @JavascriptInterface
    fun showToast(toast:String){
        Toast.makeText(mContext, "Toast!", Toast.LENGTH_SHORT).show()
        println("FRomJavascript!")
    }
}