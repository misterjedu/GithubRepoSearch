package com.oladapo.githibsearch.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.oladapo.githibsearch.data.network.model.Item
import com.oladapo.githibsearch.databinding.ItemsRepoBinding

class RepoViewHolder(private val binding: ItemsRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, glideRequestManager: RequestManager) {
        binding.apply {
            binding.itemRepoDescription.text = item.description
            binding.itemRepoName.text = item.name
            binding.itemRepoUrl.text = item.owner.repositoryUrl
            glideRequestManager
                .load(item.owner.avatarImage)
                .into(binding.itemOwnerAvatar)
        }
    }
}
