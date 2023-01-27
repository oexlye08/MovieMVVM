package id.my.okisulton.moviemvvm.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.my.okisulton.moviemvvm.R
import id.my.okisulton.moviemvvm.data.remote.model.MovieMove
import id.my.okisulton.moviemvvm.data.remote.model.MovieResponse
import id.my.okisulton.moviemvvm.databinding.FragmentMovieBinding
import id.my.okisulton.moviemvvm.util.Constants
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment(), MovieAdapter.OnItemClickListener {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var movieFactory: MovieViewModel.MovieFactory
    private val viewModel: MovieViewModel by viewModels {
        defaultViewModelProviderFactory
        Constants.movieFactory(movieFactory, requireParentFragment(), arguments)
//        MovieViewModel.provideFactory(movieFactory, requireParentFragment(), arguments)
    }

    private lateinit var movieAdapter: MovieAdapter
//    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(this)

        setupRvMovie(movieAdapter)
        viewModel.movies.observe(viewLifecycleOwner) { response ->
            Log.d(TAG, "onViewCreated: $response")
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, response)
        }

        setHasOptionsMenu(true)
    }

    private fun setupRvMovie(movieAdapter: MovieAdapter) {

        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = movieAdapter
                .withLoadStateHeaderAndFooter(
                    header = MovieLoadStateAdapter { movieAdapter.retry() },
                    footer = MovieLoadStateAdapter { movieAdapter.retry() }
                )
        }
        binding.buttonRetry.setOnClickListener { movieAdapter.retry() }

        movieAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvMovie.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                tvFailedToLoad.isVisible = loadState.source.refresh is LoadState.Error

                //not found
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    movieAdapter.itemCount < 1
                ) {
                    rvMovie.isVisible = false
                    tvNotFound.isVisible = true
                } else {
                    tvNotFound.isVisible = false
                }
            }
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.rvMovie.scrollToPosition(0)
                    viewModel.searchMovie(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }


    @Deprecated("Deprecated in Java", ReplaceWith("true"))
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movie: MovieResponse.ResultsItem) {
        Log.d(TAG, "onItemClick: $movie")
        val movieMove = MovieMove(
            backdropPath = movie.backdropPath,
            overview = movie.overview,
            originalTitle = movie.originalTitle,
            id = movie.id,
            title = movie.title,
            posterPath = movie.posterPath
        )
        val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment(movieMove)
        findNavController().navigate(action)

//        val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment(movieMove = movie)
//        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "MovieFragment"
    }

}

