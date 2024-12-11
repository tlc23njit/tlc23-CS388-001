package com.example.trivia

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Parcelize
data class Question (
    var q: String,
    var a: String,
    var point: Int,
    var cat: String
) : Parcelable {
    override fun toString(): String {
        return q
    }
}