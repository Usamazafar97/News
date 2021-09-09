package com.example.news

import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyAdapter
    private var articles = mutableListOf<Article>()
    private var pageNum = 1
    private var totalResults = -1

//    lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val adView = AdView(this)
//        val adView: AdView = findViewById(R.id.adView)
//        adView.adSize = AdSize.BANNER
//        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

//        MobileAds.initialize(this) {}

//        mInterstitialAd = InterstitialAd()


        adapter = MyAdapter(articles,this@MainActivity)
        var rv_adapter: RecyclerView = findViewById(R.id.rv)
        rv_adapter.adapter = adapter
//        rv_adapter.layoutManager = LinearLayoutManager(this@MainActivity)

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setPagerMode(true)
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                var container: ConstraintLayout = findViewById(R.id.container)
//                var container = RelativeLayout(this@MainActivity)
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))

                if (totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.getVisibleItemCount() - 5){
                    pageNum++
                    getNews()
                }
            }

        })
        rv_adapter.layoutManager = layoutManager

        getNews()

    }

    private fun getNews() {
        val news:Call<News> = NewsService.newsInstance.getHeadline("us",pageNum)

        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null){
                    Log.d("Main Responce Part", news.toString())
                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Main Error Part",t.toString())
            }
        })
    }
}