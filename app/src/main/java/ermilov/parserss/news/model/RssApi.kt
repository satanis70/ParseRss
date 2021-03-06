package ermilov.parserss.news.model

import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RssApi {
    @GET
    suspend fun getNews(@Url url: String) : Response<RssFeed>
}