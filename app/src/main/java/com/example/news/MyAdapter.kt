package com.example.news

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.zip.Inflater

class MyAdapter(val articles: List<Article>, val context: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.description.text = articles[position].description
        holder.title.text = articles[position].title
        Glide.with(context).load(articles[position].urlToImage).into(holder.image)

        holder.itemView.setOnClickListener {
            Toast.makeText(context,articles[position].title,Toast.LENGTH_LONG).show()
            var intent = Intent(context, DetailNews::class.java)
            intent.putExtra("URL",articles[position].url)
            context.startActivity(intent)
        }

//        if (position%2 == 0)
//            holder.container.setBackgroundColor(Color.DKGRAY)
//        else
//            holder.container.setBackgroundColor(Color.GRAY)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.newsImage)
        var title: TextView = itemView.findViewById(R.id.title)
        var description: TextView = itemView.findViewById(R.id.description)
//

    }
}

