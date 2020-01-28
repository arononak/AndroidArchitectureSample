package com.example.androidarchitecturesample.ui.promoted

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.androidarchitecturesample.data.api.Promoted
import com.example.androidarchitecturesample.data.api.WykopApi
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PromotedDataSource(coroutineContext: CoroutineContext) : PageKeyedDataSource<Long, Promoted>() {

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Promoted>
    ) {
        scope.launch {
            try {
                val api = WykopApi.getInstance()

                val items = listOf(
                    async(Dispatchers.IO) { api.fetchPromoted(1) },
                    async(Dispatchers.IO) { api.fetchPromoted(2) },
                    async(Dispatchers.IO) { api.fetchPromoted(3) }
                ).awaitAll()
                    .filter { it.isSuccessful }
                    .mapNotNull { it.body() }
                    .flatten()

                callback.onResult(items, null, 4)

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Promoted>) {
        Log.e("PostsDataSource", "Failed to fetch data!")
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Promoted>) {
        scope.launch {
            try {
                val api = WykopApi.getInstance()

                val items = listOf(
                    async(Dispatchers.IO) { api.fetchPromoted(params.key.toInt()) }
                ).awaitAll()
                    .filter { it.isSuccessful }
                    .mapNotNull { it.body() }
                    .flatten()

                callback.onResult(items, params.key + 1)

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}
