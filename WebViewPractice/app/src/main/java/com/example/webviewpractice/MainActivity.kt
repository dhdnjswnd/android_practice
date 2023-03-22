package com.example.webviewpractice

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.webviewpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var myWebView:WebView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflate : xml의 뷰를 객체화 해준다.
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myWebView= binding.webview
        myWebView!!.loadUrl("https://www.naver.com")

        myWebView!!.webViewClient = WebViewClient() // 웹 뷰 내에서 naver, youtube가 열리도록 설정.

        binding.btnChrome.setOnClickListener {
            println("chrome")
            myWebView!!.loadUrl("https://www.google.com")
        }
        binding.btnYoutube.setOnClickListener {
            println("youtube")
            myWebView!!.loadUrl("https://www.youtube.com")
            Toast.makeText(this,"Youtube",Toast.LENGTH_SHORT).show()
        }

        binding.htmlBtn.setOnClickListener {
            val unencodedHtml =
                "&lt;html&gt;&lt;body&gt;'%23' is the percent code for ‘#‘ &lt;/body&gt;&lt;/html&gt;"
            val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
            myWebView!!.loadData(encodedHtml, "text/html", "base64")
        }

        binding.btnJavascript.setOnClickListener {
            val intent = Intent(applicationContext, JavascriptInterfaceActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView!!.canGoBack()) {
            myWebView!!.goBack()
            return true
        }
        if (keyCode == KeyEvent.KEYCODE_FORWARD && myWebView!!.canGoForward()) {
            myWebView!!.goForward()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }
}