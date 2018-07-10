package com.javalong.richtext.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import com.javalong.richtext.R
import com.javalong.richtext.RichText
import com.javalong.richtext.TextSpanOperation
import kotlinx.android.synthetic.main.activity_textview.*

class TextViewActivity : AppCompatActivity() {

    companion object {
        const val TEXT = "学习是一件枯燥的事情，但是分享会让我感到快乐。"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textview)
        initContentTop()
        initContentBottom()
    }

    //链式结构
    private fun initContentTop() {
        RichText.Builder(this)
                .backColor(1, 4, Color.RED)
                .foreColor(5, 9, Color.BLUE)
                .fontSize(10, 15, 20)
                .image(2, 3, resources.getDrawable(R.mipmap.ic_launcher))
                .addTextSpanOperation(TextSpanOperation(3, 4, AbsoluteSizeSpan(20)))
                .text(TEXT)
                .build(tvContentTop)
    }

    //常规写法
    private fun initContentBottom() {
        var backColor = BackgroundColorSpan(Color.RED)
        var foreColor = ForegroundColorSpan(Color.BLUE)
        var fontSize = AbsoluteSizeSpan(20)
        var d = resources.getDrawable(R.mipmap.ic_launcher)
        d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
        var image = ImageSpan(d)
        var fontSize2 = AbsoluteSizeSpan(20)
        var spanBuilder = SpannableStringBuilder(TEXT)
        spanBuilder.setSpan(backColor, 1, 4, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(foreColor, 5, 9, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(fontSize, 10, 15, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(image, 2, 3, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(fontSize2, 3, 4, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvContentBottom.text = spanBuilder
    }
}
