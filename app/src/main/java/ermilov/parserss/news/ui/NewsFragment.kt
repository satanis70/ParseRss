package ermilov.parserss.news.ui

import android.content.Context
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import retrofit2.*


class NewsFragment : Fragment() {
    lateinit var navController: NavController
    var listTitle = ArrayList<String>()
    var listImage = ArrayList<String>()
    var adress = String()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        adress = sharedPref.getString("adress", "").toString()
        getnews()
    }

    fun getnews(){

        val retrofit = Retrofit.Builder()
                .baseUrl("https://")
            .addConverterFactory(RssConverterFactory.create())
            .build()
        val rssApi = retrofit.create(RssApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val responce = rssApi.getNews(adress)
            if (responce.isSuccessful){
                for (i in responce.body()?.items!!){
                    listTitle.add(i.title.toString())
                    listImage.add(i.image.toString())
                    Log.i("tagError", i.title.toString())
                }
                launch(Dispatchers.Main){
                    val adapter = Holder(listTitle, listImage)
                    recycler_news.layoutManager = LinearLayoutManager(requireContext())
                    recycler_news.adapter = adapter
                }
            }

        }




    }
}