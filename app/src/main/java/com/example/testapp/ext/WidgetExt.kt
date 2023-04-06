package com.example.testapp.ext

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.*
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.example.testapp.R
import com.example.testapp.model.response.StyleString
import com.example.testapp.model.response.TextFieldData

fun TextView.setTextFieldData(
    textFieldData: TextFieldData?,
) {
    textFieldData?.let { data ->
        data.size?.let { size ->
            if (size > 0) {
                textSize = size.toFloat()
            }
        }
        data.text?.let { textData ->
            if (data.substringStyles.isNullOrEmpty()) {
                text = textData
            } else {
                setSubstringStyles(data.substringStyles, textData)
            }
            isVisible = true
        }
            ?: run { isVisible = false }
        data.htmlText?.let { htmlTextData ->
            text =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(htmlTextData, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    Html.fromHtml(htmlTextData)
                }
            isVisible = true
        }
        data.textColor?.let { textColor ->
            setTextColor(Color.parseColor(textColor))
        }
        data.gradient?.let { gradient ->
            val shader =
                LinearGradient(
                    0f,
                    0f,
                    0f,
                    this.textSize,
                    Color.parseColor(gradient.startGradientColor),
                    Color.parseColor(gradient.endGradientColor),
                    Shader.TileMode.CLAMP
                )
            this.paint.shader = shader
        }
        if (data.shouldStrikeOff == true) {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }
}

fun getStartEndIndex(
    substringToFind: String?,
    startIndex: Int?,
    endIndex: Int?,
    textData: String
): Pair<Int, Int> {
    var start = -1
    var end = -1

    if (!substringToFind.isNullOrEmpty()) {
        start = textData.indexOf(substringToFind)
        end = start + substringToFind.length
    } else if (startIndex != null && endIndex != null) {
        start = startIndex!!
        end = endIndex!!
    }

    return Pair(start, end)
}

fun getImageFromIconCode(iconCode: String?): Int {
    return when (iconCode) {
        "CARTOON" -> R.drawable.cartoon
        else -> -1
    }
}

fun isValidIndexRange(textData: String, startEndIndex: Pair<Int, Int>): Boolean {
    return startEndIndex.first >= 0 &&
            startEndIndex.first < textData.length &&
            startEndIndex.second >= 0 &&
            startEndIndex.second < textData.length &&
            startEndIndex.first <= startEndIndex.second
}

fun TextView.setSubstringStyles(
    substringStylesList: List<StyleString>,
    textData: String
) {
    val spannableString = SpannableString(textData)

    substringStylesList.forEach { styleString ->
        if (!styleString.substring.isNullOrEmpty() || styleString.startIndex != null) {
            val startEndIndex =
                getStartEndIndex(
                    styleString.substring,
                    styleString.startIndex,
                    styleString.endIndex,
                    textData
                )
            if (isValidIndexRange("$textData ", startEndIndex)) {
                if(!styleString.textColor.isNullOrEmpty()) {
                    val colorSpan = ForegroundColorSpan(Color.parseColor(styleString.textColor))
                    spannableString.setSpan(
                        colorSpan,
                        startEndIndex.first,
                        startEndIndex.second,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                if (styleString.shouldStrikeOff == true) {
                    val strikeOffSpan = StrikethroughSpan()
                    spannableString.setSpan(
                        strikeOffSpan,
                        startEndIndex.first,
                        startEndIndex.second,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                if (styleString.relativeSize != null && styleString.relativeSize!! > 0.0) {
                    spannableString.setSpan(
                        RelativeSizeSpan(styleString.relativeSize.toFloat()),
                        startEndIndex.first,
                        startEndIndex.second,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                if (styleString.underline == true) {
                    spannableString.setSpan(
                        UnderlineSpan(),
                        startEndIndex.first,
                        startEndIndex.second,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                if (!styleString.iconCode.isNullOrEmpty()) {
                    val drawable: Drawable? = ResourcesCompat.getDrawable(
                        context.resources,
                        getImageFromIconCode(styleString.iconCode),
                        null
                    )
                    drawable?.let { drawable ->
                        val textviewHeight =
                            (paint.fontMetrics.bottom - paint.fontMetrics.top).toInt()
                        val drawableWidth =
                            textviewHeight * (drawable.intrinsicWidth / drawable.intrinsicHeight)
                        drawable.setBounds(0, 0, drawableWidth, textviewHeight)
                        val imgSpan: ImageSpan? = ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM)
                        spannableString.setSpan(
                            imgSpan,
                            startEndIndex.first,
                            startEndIndex.second,
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                        )
                    }

                }
            }
        }
    }
    text = spannableString
}