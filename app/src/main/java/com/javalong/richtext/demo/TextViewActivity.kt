package com.javalong.richtext.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.Toast
import com.javalong.richtext.MyLinkMovementMethod
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
                .click(6, 16, View.OnClickListener {
                    Toast.makeText(this@TextViewActivity, TEXT, Toast.LENGTH_LONG).show()
                })
                .underline(15, 20)
                .strikethrough(21, 23)
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
        var clickSpan = object : ClickableSpan() {
            override fun onClick(widget: View?) {
                Toast.makeText(this@TextViewActivity, TEXT, Toast.LENGTH_LONG).show()
            }

            override fun updateDrawState(ds: TextPaint?) {
                //去掉一些样式，都由自己来定义
            }
        }
        var underlineSpan = UnderlineSpan()
        var strikethroughSpan = StrikethroughSpan()
        spanBuilder.setSpan(backColor, 1, 4, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(foreColor, 5, 9, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(fontSize, 10, 15, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(image, 2, 3, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(fontSize2, 3, 4, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(clickSpan, 6, 16, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(underlineSpan, 15, 20, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(strikethroughSpan, 21, 23, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvContentBottom.movementMethod = MyLinkMovementMethod.getInstance()
        tvContentBottom.text = spanBuilder
    }
}
