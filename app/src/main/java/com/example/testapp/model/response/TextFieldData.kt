package com.example.testapp.model.response

import com.google.gson.annotations.SerializedName

data class TextFieldData(
    @SerializedName("text") var text: String? = null,
    @SerializedName("htmlText") val htmlText: String? = null,
    @SerializedName("size") val size: Int? = null,
    @SerializedName("font") val font: String? = null,
    @SerializedName("fontWeight") val fontWeight: Int? = null,
    @SerializedName("textColor") val textColor: String? = null,
    @SerializedName("gradient") val gradient: Gradient? = null,
    @SerializedName("substringStyles") val substringStyles: List<StyleString>? = null,
    @SerializedName("shouldStrikeOff") val shouldStrikeOff: Boolean? = false,
    @SerializedName("masked") val masked: Boolean? = null,
    @SerializedName("gravity") val gravity: String? = null,
    @SerializedName("alignment") val alignment: String? = null,
    @SerializedName("lineSpacing") val lineSpacing: Int? = null
)

data class StyleString(
    @SerializedName("font") val font: String? = null,
    @SerializedName("textColor") val textColor: String? = null,
    @SerializedName("substring") val substring: String? = null,
    @SerializedName("startIndex") val startIndex: Int? = null,
    @SerializedName("iconCode") val iconCode: String? = null,
    @SerializedName("endIndex") val endIndex: Int? = null,
    @SerializedName("relativeSize") val relativeSize: Double? = null,
    @SerializedName("underline") val underline: Boolean? = null,
    @SerializedName("shouldStrikeOff") val shouldStrikeOff: Boolean? = false,
)

data class Gradient(
    @SerializedName("startGradientColor") val startGradientColor: String? = null,
    @SerializedName("middleGradientColor") val middleGradientColor: String? = null,
    @SerializedName("endGradientColor") val endGradientColor: String? = null,
    @SerializedName("angle") val angle: Int? = null,
    @SerializedName("orientation") val orientation: String? = null
)

data class NaviDrawableData(
    @SerializedName("cornerRadius") val cornerRadius: Int? = null,
    @SerializedName("backgroundColor") val backgroundColor: String? = null,
    @SerializedName("strokeWidth") val strokeWidth: Int? = null,
    @SerializedName("strokeColor") val strokeColor: String? = null,
    @SerializedName("elevation") val elevation: Float? = null
)

enum class TextAlignment(val value: String?) {
    CENTER("CENTER"),
    START("START"),
    END("END")
}
