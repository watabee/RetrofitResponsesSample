package com.github.watabee.retrofitresponses

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

// Qiita API のエラーレスポンス.
// https://qiita.com/api/v2/docs#%E3%82%A8%E3%83%A9%E3%83%BC%E3%83%AC%E3%82%B9%E3%83%9D%E3%83%B3%E3%82%B9
@JsonClass(generateAdapter = true)
data class QiitaApiError(
    @Json(name = "message") val message: String,
    @Json(name = "type") val type: String
) {
    class Mapper(private val moshi: Moshi) : ApiErrorModelMapper<QiitaApiError?> {
        override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): QiitaApiError? {
            return apiErrorResponse.errorBody?.source()?.let(moshi.adapter(QiitaApiError::class.java)::fromJson)
        }
    }
}
