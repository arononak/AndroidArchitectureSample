package com.example.androidarchitecturesample.ui.entry

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.androidarchitecturesample.data.api.WykopApi
import com.example.androidarchitecturesample.data.model.Entry
import kotlinx.coroutines.*

class EntryDataSource(private val api: WykopApi) : PageKeyedDataSource<Long, Entry>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Entry>
    ) {
        GlobalScope.launch {
            try {
                listOf(
                    async(Dispatchers.IO) { api.fetchEntries(1) },
                    async(Dispatchers.IO) { api.fetchEntries(2) },
                    async(Dispatchers.IO) { api.fetchEntries(3) }
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

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Entry>) {
        Log.e("PostsDataSource", "Failed to fetch data!")
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Entry>) {
        GlobalScope.launch {
            try {
                listOf(
                    async(Dispatchers.IO) { api.fetchEntries(params.key.toInt()) }
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
