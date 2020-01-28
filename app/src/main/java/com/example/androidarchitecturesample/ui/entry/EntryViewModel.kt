package com.example.androidarchitecturesample.ui.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.androidarchitecturesample.data.api.Entry

class EntryViewModel : ViewModel() {

    var entriesLiveData: LiveData<PagedList<Entry>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(50)
            .setEnablePlaceholders(false)
            .build()

        val dataSourceFactory = object : DataSource.Factory<Long, Entry>() {
            override fun create(): DataSource<Long, Entry> {
                return EntryDataSource(viewModelScope.coroutineContext)
            }
        }

        entriesLiveData = LivePagedListBuilder<Long, Entry>(dataSourceFactory, config).build()
    }
}
