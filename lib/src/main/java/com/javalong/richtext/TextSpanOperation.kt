package com.javalong.richtext

import android.text.SpannableStringBuilder
import android.text.style.CharacterStyle

data class TextSpanOperation(val startIndex: Int, val endIndex: Int, val characterStyle: CharacterStyle, val flag: Int = SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
