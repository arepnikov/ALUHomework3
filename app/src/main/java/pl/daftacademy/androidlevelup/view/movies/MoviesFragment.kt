package pl.daftacademy.androidlevelup.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.entity.Page
import pl.daftacademy.androidlevelup.view.viewmodel.MoviesViewModel

const val FILTER_BY_GENRE = "filterByGenre"

class MoviesFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[MoviesViewModel::class.java] }
    private val adapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val filterByGenre = arguments?.getString(FILTER_BY_GENRE)?.toLowerCase()
        recyclerView.adapter = adapter

        adapter.items = viewModel.getMovies().run {
            if (filterByGenre == null)
                this
            else
                filter { it.genres.any { it.toLowerCase() == filterByGenre } }
        }
    }

    companion object {
        fun create(page: Page): MoviesFragment =
            MoviesFragment().apply { arguments = Bundle().apply { putString(FILTER_BY_GENRE, page.filterBy) } }
    }
}
