package com.example.newsapp.repositroy

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(counterCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(counterCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert (article: Article) = db.getArticleDao().upsert(article)

    suspend fun delete (article: Article) = db.getArticleDao().deleteArticle(article)

    fun getOurSavedNews() = db.getArticleDao().getAllArticles()


}