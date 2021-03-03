package ermilov.parserss.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import ermilov.parserss.news.data.Holder
import ermilov.parserss.R
import ermilov.parserss.news.model.RssApi
import kotlinx.android.synthetic.main.fragment_news.*
import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class NewsFragment : Fragment() {
    lateinit var navController: NavController
    var listTitle = ArrayList<String>()
    var listImage = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        getnews()
        listTitle.add("sdagfs faasgfg  ")
        val adapter = Holder(listTitle, listImage)
        recycler_news.layoutManager = LinearLayoutManager(requireContext())
        recycler_news.adapter = adapter

    }

    fun getnews(){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://lenta.ru/")
            .addConverterFactory(RssConverterFactory.create())
            .build()


        val rssApi = retrofit.create(RssApi::class.java)
        rssApi.getNews("rss/news").enqueue(object : Callback<RssFeed> {
            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                for (i in response.body()?.items!!){
                    listTitle.add(i.title.toString())
                    listImage.add(i.image.toString())
                    Log.i("tagError", i.title.toString())
                }

            }
            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                Log.i("tagError", t.message.toString())
            }

        })

    }
}