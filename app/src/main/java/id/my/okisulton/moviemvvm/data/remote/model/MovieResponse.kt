package id.my.okisulton.moviemvvm.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(

	@field:SerializedName("total_pages")
	val totalPages: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>
) : Parcelable {

	@Parcelize
	data class ResultsItem(

		@field:SerializedName("backdrop_path")
		val backdropPath: String? = null,

		@field:SerializedName("overview")
		val overview: String? = null,

		@field:SerializedName("original_title")
		val originalTitle: String? = null,

		@field:SerializedName("id")
		val id: String? = null,

		@field:SerializedName("title")
		val title: String? = null,

		@field:SerializedName("poster_path")
		val posterPath: String? = null
	) : Parcelable {

		val baseUrl get() = "https://image.tmdb.org/t/p/w500"
	}
}

