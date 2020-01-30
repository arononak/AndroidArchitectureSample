package com.example.androidarchitecturesample.ui.promoted

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.androidarchitecturesample.data.model.Promoted

class PromotedViewModel(promotedDataSource: PromotedDataSource) : ViewModel() {

    var promotedLiveData: LiveData<PagedList<Promoted>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(50)
            .setEnablePlaceholders(false)
            .build()

        val dataSourceFactory = object : DataSource.Factory<Long, Promoted>() {
            override fun create(): DataSource<Long, Promoted> = promotedDataSource
        }

        promotedLiveData = LivePagedListBuilder<Long, Promoted>(dataSourceFactory, config).build()
    }
}
