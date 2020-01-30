package com.example.androidarchitecturesample.ui.promoted

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.androidarchitecturesample.data.api.WykopApi
import com.example.androidarchitecturesample.data.model.Promoted
import kotlinx.coroutines.*

class PromotedDataSource(private val api: WykopApi) : PageKeyedDataSource<Long, Promoted>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Promoted>
    ) {
        GlobalScope.launch {
            try {
                listOf(
                    async(Dispatchers.IO) { api.fetchPromoted(1) },
                    async(Dispatchers.IO) { api.fetchPromoted(2) },
                    async(Dispatchers.IO) { api.fetchPromoted(3) }
                ).awaitAll()
                    .filter { it.isSuccessful }
                    .mapNotNull { it.body() }
                    .flatten()
                    .let { callback.onResult(it, null, 4) }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Promoted>) {
        Log.e("PostsDataSource", "Failed to fetch data!")
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Promoted>) {
        GlobalScope.launch {
            try {
                listOf(
                    async(Dispatchers.IO) { api.fetchPromoted(params.key.toInt()) }
                ).awaitAll()
                    .filter { it.isSuccessful }
                    .mapNotNull { it.body() }
                    .flatten()
                    .let { callback.onResult(it, params.key + 1) }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }
}
