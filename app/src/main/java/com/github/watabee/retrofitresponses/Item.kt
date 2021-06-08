package com.github.watabee.retrofitresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Qiita の記事投稿データ.
// https://qiita.com/api/v2/docs#%E6%8A%95%E7%A8%BF
@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
)
