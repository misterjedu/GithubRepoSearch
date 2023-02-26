package com.oladapo.githibsearch.presentation.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.oladapo.githibsearch.databinding.LoadStateItemBinding

class LoadStateViewHolder(
    private val binding: LoadStateItemBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }

        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.errorMsg.isVisible = loadState is LoadState.Error
        binding.retryButton
            .also {
                it.setOnClickListener { retry() }
                it.isVisible = loadState is LoadState.Error
            }
    }
}
