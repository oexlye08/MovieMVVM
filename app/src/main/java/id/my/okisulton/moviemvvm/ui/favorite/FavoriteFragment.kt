package id.my.okisulton.moviemvvm.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.okisulton.moviemvvm.data.local.model.FavoriteMovie
import id.my.okisulton.moviemvvm.data.remote.model.MovieMove
import id.my.okisulton.moviemvvm.databinding.FragmentFavoriteBinding

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.setMovieList(it)
        }
    }

    private fun setupRv() {
        adapter = FavoriteAdapter(
            arrayListOf(),
            object : FavoriteAdapter.OnItemClickListener {
                override fun onItemClick(movie: FavoriteMovie) {
                    val moveMovie = MovieMove(
                        backdropPath = movie.backdropPath,
                        overview = movie.overview,
                        originalTitle = movie.originalTitle,
                        id = movie.idMovie,
                        title = movie.title,
                        posterPath = movie.posterPath
                    )
                    val action =
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(movieMove = moveMovie)
                    findNavController().navigate(action)
                }

            }
        )
        binding.apply {
            rvMovie.layoutManager = LinearLayoutManager(activity)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}