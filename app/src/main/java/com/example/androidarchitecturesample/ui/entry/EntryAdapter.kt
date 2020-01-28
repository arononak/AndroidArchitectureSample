package com.example.androidarchitecturesample.ui.entry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecturesample.R
import com.example.androidarchitecturesample.data.api.Entry
import com.example.androidarchitecturesample.ui.main.MainViewPagerFragmentDirections
import kotlinx.android.synthetic.main.entry_item.view.*

class EntryAdapter : PagedListAdapter<Entry, EntryAdapter.ViewHolder>(EntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.entry_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImage= itemView.avatar
        val authorText = itemView.author
        val dateText = itemView.date

        fun bind(entry: Entry) {
            with(entry) {
                Glide.with(itemView.context).load(authorAvatarBig).into(avatarImage)
                authorText.text = author
                dateText.text = date
                itemView.setOnClickListener {
                    itemView.findNavController().navigate(
                        MainViewPagerFragmentDirections.actionMainFragmentToDetailFragment(url)
                    )
                }
            }
        }
    }
}

private class EntryDiffCallback : DiffUtil.ItemCallback<Entry>() {

    override fun areItemsTheSame(oldItem: Entry, newItem: Entry) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Entry, newItem: Entry) =
        oldItem.body == newItem.body
}