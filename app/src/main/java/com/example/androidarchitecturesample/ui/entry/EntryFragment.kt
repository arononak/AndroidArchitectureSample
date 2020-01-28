package com.example.androidarchitecturesample.ui.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecturesample.databinding.EntryFragmentBinding

class EntryFragment : Fragment() {

    companion object {
        fun newInstance() = EntryFragment()
    }

    private val viewModel: EntryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = EntryFragmentBinding.inflate(inflater, container, false)
        val adapter = EntryAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.entriesLiveData.observe(this, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }
}