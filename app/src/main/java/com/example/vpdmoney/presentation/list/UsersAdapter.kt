package com.example.vpdmoney.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vpdmoney.R
import com.example.vpdmoney.data.remote.dto.UsersDtoItem
import com.example.vpdmoney.databinding.UsersItemBinding

class UsersAdapter(
    private val listener :OnItemClickListener
): ListAdapter<UsersDtoItem, UsersAdapter.UsersAdapterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapterViewHolder {
        val binding =
            UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersAdapterViewHolder, position: Int) {
        val currentAlbum = getItem(position)

        if(currentAlbum != null) {
            holder.bind(currentAlbum)
        }
    }

    inner class UsersAdapterViewHolder(private val binding: UsersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.apply {
                binding.root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION) {
                        val item = getItem(position)
                        if(item != null) {
                            listener.onItemClick(item)
                        }
                    }
                }
            }
        }

        fun bind(item: UsersDtoItem) {

            binding.name.text = item.name
            binding.email.text = item.email
            binding.username.text = item.username

        }

    }

    interface OnItemClickListener {
        fun onItemClick(user: UsersDtoItem)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UsersDtoItem>() {
            override fun areItemsTheSame(oldItem: UsersDtoItem, newItem: UsersDtoItem): Boolean {
                return oldItem.name == newItem.name
            }
            override fun areContentsTheSame(oldItem: UsersDtoItem, newItem: UsersDtoItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}