package id.my.okisulton.moviemvvm.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.my.okisulton.moviemvvm.R
import id.my.okisulton.moviemvvm.data.local.model.FavoriteMovie
import id.my.okisulton.moviemvvm.databinding.ItemMovieBinding
import id.my.okisulton.moviemvvm.util.Constants.IMAGE_URL

/**
 * Created by Oki Sulton on 27/01/2023.
 */
class FavoriteAdapter(
    private var list: List<FavoriteMovie>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(list: List<FavoriteMovie>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val data = list[position]
        holder.binding.apply {
            Glide.with(root)
                .load(IMAGE_URL + data.backdropPath)
                .error(R.drawable.ic_error)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(moviePoster)

            tvTitle.text = data.originalTitle

            root.setOnClickListener {
                listener.onItemClick(data)
            }
        }

    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onItemClick(movie: FavoriteMovie)
    }

    companion object {
        private const val TAG = "FavoriteAdapter"
    }
}
