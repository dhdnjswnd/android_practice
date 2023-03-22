package com.example.webviewpractice

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader
import com.example.webviewpractice.databinding.ActivityJavascriptBinding

class JavascriptInterfaceActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflate : xml의 뷰를 객체화 해준다.
        val binding = ActivityJavascriptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myWebView = binding.webview
        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .addPathHandler("/res/", WebViewAssetLoader.ResourcesPathHandler(this))
            .build()
        myWebView.webViewClient =LocalContentWebViewClient(assetLoader)
        myWebView.settings.javaScriptEnabled=true
        myWebView.addJavascriptInterface(WebAppInterface(this), "Android")

        myWebView.loadUrl("https://appassets.androidplatform.net/assets/javascriptInterfaceTest.html")
    }
}