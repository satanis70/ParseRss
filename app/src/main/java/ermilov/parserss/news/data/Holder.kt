package ermilov.parserss.news.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ermilov.parserss.R

class Holder(var listTitle: List<String>, var listImage: List<String>) : RecyclerView.Adapter<Holder.MainHolder>() {
    var listNews = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder.MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: Holder.MainHolder, position: Int) {
        holder.bind(listTitle[position], listImage[position])
        listNews.add(listTitle[position])
    }

    override fun getItemCount(): Int {
        return listTitle.size
    }

    inner class MainHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.textViewTitle)
        private val imageNews = itemView.findViewById<ImageView>(R.id.imageViewNews)

        fun bind(title: String, image: String){
            titleTextView.text = title
            Glide.with(itemView.context)
                    .load(image)
                    .override(300, 300)
                    .centerCrop()
                    .into(imageNews)
        }

    }
}