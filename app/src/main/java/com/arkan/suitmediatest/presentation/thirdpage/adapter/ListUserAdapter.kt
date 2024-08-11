package com.arkan.suitmediatest.presentation.thirdpage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.arkan.suitmediatest.data.model.User
import com.arkan.suitmediatest.databinding.ItemUserBinding

class ListUserAdapter(
    private val itemClick: (User) -> Unit,
) : PagingDataAdapter<User, ListUserViewHolder>(UserDiffCallBack()) {
    override fun onBindViewHolder(
        holder: ListUserViewHolder,
        position: Int,
    ) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListUserViewHolder {
        return ListUserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            itemClick,
        )
    }
}

class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(
        oldItem: User,
        newItem: User,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: User,
        newItem: User,
    ): Boolean {
        return oldItem == newItem
    }
}

class ListUserViewHolder(
    private val binding: ItemUserBinding,
    val itemClick: (User) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: User) {
        with(item) {
            binding.ivImgUser.load(item.avatar) {
                crossfade(true)
            }
            binding.tvItemName.text =
                buildString {
                    append(item.firstName)
                    append(" ")
                    append(item.lastName)
                }
            binding.tvItemEmail.text = item.email
            itemView.setOnClickListener { itemClick(this) }
        }
    }
}
