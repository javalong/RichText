package com.javalong.richtext.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.javalong.richtext.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TEXT = "学习是一件枯燥的事情，但是分享会让我感到快乐。"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btEditText.setOnClickListener({
            startActivity(Intent(this@MainActivity, EditTextActivity::class.java))
        })
        btTextView.setOnClickListener({
            startActivity(Intent(this@MainActivity, TextViewActivity::class.java))
        })
    }

}
