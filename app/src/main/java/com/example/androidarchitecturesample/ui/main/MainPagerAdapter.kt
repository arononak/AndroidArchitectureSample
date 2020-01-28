package com.example.androidarchitecturesample.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidarchitecturesample.ui.entry.EntryFragment
import com.example.androidarchitecturesample.ui.promoted.PromotedFragment

const val PROMOTED_PAGE_INDEX = 0
const val ENTRY_PAGE_INDEX = 1

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PROMOTED_PAGE_INDEX to { PromotedFragment.newInstance() },
        ENTRY_PAGE_INDEX to { EntryFragment.newInstance() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}