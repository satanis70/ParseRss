package ermilov.parserss

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ermilov.parserss.news.model.RssApi
import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://lenta.ru/")
                .addConverterFactory(RssConverterFactory.create())
                .build()



        val rssApi = retrofit.create(RssApi::class.java)
        rssApi.getNews("rss/news").enqueue(object : Callback<RssFeed> {
            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                for (i in response.body()?.items!!){
                    Log.i("tag", i.toString())
                }
            }

            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                Log.i("tagError", t.message.toString())
            }

        })
    }
}