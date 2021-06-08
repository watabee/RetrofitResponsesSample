package com.github.watabee.retrofitresponses

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

val moshi: Moshi = Moshi.Builder().build()

val qiitaApiForEitherNet: QiitaApiForEitherNet by lazy {
    Retrofit.Builder()
        .baseUrl("https://qiita.com")
        .client(okHttpClient)
        .addConverterFactory(ApiResultConverterFactory)
        .addCallAdapterFactory(ApiResultCallAdapterFactory)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create()
}

val qiitaApiForSandwich: QiitaApiForSandwich by lazy {
    Retrofit.Builder()
        .baseUrl("https://qiita.com")
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create()
}
