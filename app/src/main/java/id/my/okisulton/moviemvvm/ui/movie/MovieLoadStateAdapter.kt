package id.my.okisulton.moviemvvm.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import id.my.okisulton.moviemvvm.databinding.LoadStateFooterBinding

/**
 * Created by Oki Sulton on 14/09/2022.
 */
class MovieLoadStateAdapter(private val retry: ()-> Unit): LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: LoadStateFooterBinding)
        : RecyclerView.ViewHolder(binding.root) {
            init {
                binding.btnRetry.setOnClickListener {
                    retry.invoke()
                }
            }
            fun bind(loadState: LoadState) {
                with(binding) {
                    progressBar.isVisible = loadState is LoadState.Loading
                    btnRetry.isVisible = loadState !is LoadState.Loading
                    tvFailed.isVisible = loadState !is LoadState.Loading
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = LoadStateViewHolder (
        LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}