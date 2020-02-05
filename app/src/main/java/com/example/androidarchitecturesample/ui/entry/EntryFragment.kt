package com.example.androidarchitecturesample.ui.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecturesample.databinding.EntryFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntryFragment : Fragment() {

    companion object {
        fun newInstance() = EntryFragment()
    }

    private val viewModel by viewModel<EntryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = EntryFragmentBinding.inflate(inflater, container, false)
        val adapter = EntryAdapter()
        binding.entryRecyclerView.adapter = adapter
        binding.entryRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.entriesLiveData.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }

        return binding.root
    }
}