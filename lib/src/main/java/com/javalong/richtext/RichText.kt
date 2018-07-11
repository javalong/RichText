package com.javalong.richtext

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
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

        //设置点击事件
        fun click(startIndex: Int, endIndex: Int, listener: View.OnClickListener): Builder {
            var span = object : ClickableSpan() {
                override fun onClick(widget: View?) {
                    listener.onClick(widget)
                }

                override fun updateDrawState(ds: TextPaint?) {
                    //去掉一些样式，都由自己来定义

                }
            }
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        //添加下划线
        fun underline(startIndex: Int, endIndex: Int): Builder {
            var span = UnderlineSpan()
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        //添加中划线
        fun strikethrough(startIndex: Int, endIndex: Int): Builder {
            var span = StrikethroughSpan()
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

        fun click(startIndex: Int, endIndex: Int, listener: View.OnClickListener, flag: Int): Builder {
            var span = object : ClickableSpan() {
                override fun onClick(widget: View?) {
                    listener.onClick(widget)
                }

                override fun updateDrawState(ds: TextPaint?) {
                    //去掉一些样式，都由自己来定义

                }
            }
            operationList.add(TextSpanOperation(startIndex, endIndex, span, flag))
            return this
        }

        fun underline(startIndex: Int, endIndex: Int, flag: Int): Builder {
            var span = UnderlineSpan()
            operationList.add(TextSpanOperation(startIndex, endIndex, span, flag))
            return this
        }

        fun strikethrough(startIndex: Int, endIndex: Int, flag: Int): Builder {
            var span = StrikethroughSpan()
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
                if (operation.characterStyle is ClickableSpan) {
                    textView.movementMethod = MyLinkMovementMethod.getInstance()
                }
                spanBuilder.setSpan(operation.characterStyle, operation.startIndex, operation.endIndex, operation.flag)
            })
            textView.text = spanBuilder
            return textView
        }

    }


}