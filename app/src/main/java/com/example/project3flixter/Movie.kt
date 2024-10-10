package com.example.project3flixter
import com.google.gson.annotations.SerializedName
class Movie {
    @SerializedName("poster_path")
    var poster : String? = null
    @SerializedName("title")
    var title : String?  = null
    @SerializedName("overview")
    var description : String? = null
}