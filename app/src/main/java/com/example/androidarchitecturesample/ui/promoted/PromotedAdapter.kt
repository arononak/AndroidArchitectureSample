package com.example.androidarchitecturesample.ui.promoted

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecturesample.R
import com.example.androidarchitecturesample.data.model.Promoted
import com.example.androidarchitecturesample.ui.main.MainViewPagerFragmentDirections
import kotlinx.android.synthetic.main.promoted_item.view.*

class PromotedAdapter : PagedListAdapter<Promoted, PromotedAdapter.ViewHolder>(PromotedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.promoted_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val previewImage= itemView.preview
        private val avatarImage= itemView.avatar
        private val authorText = itemView.author
        private val dateText = itemView.date

        fun bind(promoted: Promoted) {
            with(promoted) {
                Glide.with(itemView.context).load(authorAvatarBig).into(avatarImage)
                Glide.with(itemView.context).load(preview).into(previewImage)
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

private class PromotedDiffCallback : DiffUtil.ItemCallback<Promoted>() {

    override fun areItemsTheSame(oldItem: Promoted, newItem: Promoted) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Promoted, newItem: Promoted) =
        oldItem.description == newItem.description
}