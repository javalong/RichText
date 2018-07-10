package com.javalong.richtext

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.widget.TextView
import java.util.*

class RichText {
    class Builder(private val context: Context) {
        private var text = ""
        private var operationList = ArrayList<TextSpanOperation>()
        //文本
        fun text(text: String): Builder {
            this.text = text
            return this
        }

        //设置图片icon
        fun image(startIndex: Int, endIndex: Int, d: Drawable): Builder {
            d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
            var span = ImageSpan(d)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        fun image(startIndex: Int, endIndex: Int, d: Drawable, bounds: Rect): Builder {
            d.bounds = bounds
            var span = ImageSpan(d)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        fun image(startIndex: Int, endIndex: Int, d: Drawable, bounds: Rect, align: Int): Builder {
            d.bounds = bounds
            var span = ImageSpan(d, align)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        //设置字体大小
        fun fontSize(startIndex: Int, endIndex: Int, size: Int): Builder {
            var span = AbsoluteSizeSpan(size)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        //设置字体颜色
        fun foreColor(startIndex: Int, endIndex: Int, color: Int): Builder {
            var span = ForegroundColorSpan(color)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        //设置背景颜色
        fun backColor(startIndex: Int, endIndex: Int, color: Int): Builder {
            var span = BackgroundColorSpan(color)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        fun image(startIndex: Int, endIndex: Int, d: Drawable, bounds: Rect, align: Int, flag: Int): Builder {
            d.bounds = bounds
            var span = ImageSpan(d, align)
            operationList.add(TextSpanOperation(startIndex, endIndex, span, flag))
            return this
        }

        fun fontSize(startIndex: Int, endIndex: Int, size: Int, flag: Int): Builder {
            var span = AbsoluteSizeSpan(size)
            operationList.add(TextSpanOperation(startIndex, endIndex, span, flag))
            return this
        }

        fun foreColor(startIndex: Int, endIndex: Int, color: Int, flag: Int): Builder {
            var span = ForegroundColorSpan(color)
            operationList.add(TextSpanOperation(startIndex, endIndex, span, flag))
            return this
        }

        fun backColor(startIndex: Int, endIndex: Int, color: Int, flag: Int): Builder {
            var span = BackgroundColorSpan(color)
            operationList.add(TextSpanOperation(startIndex, endIndex, span, flag))
            return this
        }

        //设置目前没有的操作
        fun addTextSpanOperation(textSpanOperation: TextSpanOperation): Builder {
            operationList.add(textSpanOperation)
            return this
        }

        fun build(textView: TextView): TextView {
            var spanBuilder = SpannableStringBuilder(text)
            operationList.forEach({ operation ->
                spanBuilder.setSpan(operation.characterStyle, operation.startIndex, operation.endIndex, operation.flag)
            })
            textView.text = spanBuilder
            return textView
        }

    }


}