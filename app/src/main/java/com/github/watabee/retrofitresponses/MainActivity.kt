package com.github.watabee.retrofitresponses

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.slack.eithernet.ApiResult
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.eithernet_button).setOnClickListener {
            lifecycleScope.launch {
                when (val result = qiitaApiForEitherNet.fetchItems(1, 3)) {
                    // 成功.
                    is ApiResult.Success -> Log.e(TAG, "Success: ${result.response}")
                    is ApiResult.Failure -> when (result) {
                        // HTTP ステータスコードが 4xx or 5xx の時に呼ばれる.
                        is ApiResult.Failure.HttpFailure -> Log.e(TAG, "HttpFailure: ${result.code}, ${result.error}")
                        // 通信エラーの際に呼ばれる.
                        is ApiResult.Failure.NetworkFailure -> Log.e(TAG, "NetworkFailure: ${result.error}")
                        // 予期しないエラーの時に呼ばれる（レスポンスの JSON のパースエラーなど）.
                        is ApiResult.Failure.UnknownFailure -> Log.e(TAG, "UnknownFailure: ${result.error}")
                        // 自前で ApiException を throw した際に呼ばれる.
                        is ApiResult.Failure.ApiFailure -> Log.e(TAG, "ApiFailure: ${result.error}")
                    }
                }
            }
        }

        findViewById<Button>(R.id.sandwich_button).setOnClickListener {
            lifecycleScope.launch {
                when (val result = qiitaApiForSandwich.fetchItems(1, 3)) {
                    // 成功.
                    is ApiResponse.Success -> Log.e(TAG, "Success: ${result.response.body()}")
                    // HTTP ステータスコードが200番台以外の時に呼ばれる.
                    is ApiResponse.Failure.Error -> result.onError(QiitaApiError.Mapper(moshi)) { Log.e(TAG, "Error: ${this?.message}") }
                    // 上記以外のエラーの時に呼ばれる.
                    is ApiResponse.Failure.Exception -> Log.e(TAG, "Exception: ${result.exception}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
