package com.example.project3flixter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.Headers
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import com.codepath.project3flixter.OnListFragmentInteractionListener
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

private const val API_KEY = "a7eb9cafc425fda32900870a6ccf15a6"
class MovieFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movielists, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        val client = AsyncHttpClient()
        val params = RequestParams().apply{
            put("api-key", BuildConfig.api_key)
        }
        client[
            "https://api.themoviedb.org/3/movie/now_playing?&api_key=a7eb9cafc425fda32900870a6ccf15a6",
            params,
            object : JsonHttpResponseHandler()
            {
                // Using the client, perform the HTTP request
                /*
                 * The onSuccess function gets called when
                 * HTTP response status is "200 OK"
                 */
                override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, json: JsonHttpResponseHandler.JSON) {
                    // The wait for a response is over
                    progressBar.hide()
                    //TODO - Parse JSON into Models
                    val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray
                    val moviesRawJSON : String = resultsJSON.toString()
                    val gson = Gson()
                    val arrayMovieType = object : com.google.gson.reflect.TypeToken<List<Movie>>() {}.type
                    val models : List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType)
                    recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)
                    // Look for this in Logcat:
                    //Log.d("MovieFragment", "response successful")
                    //Log.d("MovieFragment", "${models[0]}")
                    //Log.d("MovieFragment","${models[0].title}")
                }
                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: okhttp3.Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    progressBar.hide()
                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("MovieFragment", errorResponse)
                    }
                }
            }]
    }
    override fun onItemClick(item: Movie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }
}