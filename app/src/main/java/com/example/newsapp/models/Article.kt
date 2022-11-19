package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "article",
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val author: String?= null,
    val content: String?= null,
    val description: String?= null,
    val publishedAt: String?= null,
    val source: Source?= null,
    val title: String?= null,
    val url: String?= null,
    val urlToImage: String?= null,
) : Serializable{

    override fun hashCode(): Int {
        var result = id.hashCode()
        if(url!!.isEmpty()){
            result = 31 * result + url.hashCode()
        }
        return result
    }

}