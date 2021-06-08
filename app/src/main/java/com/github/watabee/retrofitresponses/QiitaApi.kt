package com.github.watabee.retrofitresponses

import com.skydoves.sandwich.ApiResponse
import com.slack.eithernet.ApiResult
import com.slack.eithernet.DecodeErrorBody
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApiForEitherNet {
    @DecodeErrorBody
    @GET("/api/v2/items")
    suspend fun fetchItems(@Query("page") page: Int, @Query("per_page") perPage: Int): ApiResult<List<Item>, QiitaApiError>
}

interface QiitaApiForSandwich {
    @GET("/api/v2/items")
    suspend fun fetchItems(@Query("page") page: Int, @Query("per_page") perPage: Int): ApiResponse<List<Item>>
}
