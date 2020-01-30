package com.example.androidarchitecturesample.ui.promoted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecturesample.databinding.PromotedFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PromotedFragment : Fragment() {

    companion object {
        fun newInstance() = PromotedFragment()
    }

    private val viewModel by viewModel<PromotedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PromotedFragmentBinding.inflate(inflater, container, false)
        val adapter = PromotedAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.promotedLiveData.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }

        return binding.root
    }
}