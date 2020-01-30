package com.example.androidarchitecturesample.di

import com.example.androidarchitecturesample.ui.entry.EntryViewModel
import com.example.androidarchitecturesample.ui.promoted.PromotedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PromotedViewModel(get()) }
    viewModel { EntryViewModel(get()) }
}
