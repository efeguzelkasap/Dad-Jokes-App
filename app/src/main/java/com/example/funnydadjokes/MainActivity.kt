package com.example.funnydadjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.funnydadjokes.repository.Repository
import com.google.android.gms.ads.*


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var viewModel: MainViewModel
    private val TAG = "MainActivity"
    private lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jokeBtn = findViewById<Button>(R.id.jokeButton)

        MobileAds.initialize(this)

        bannerAd()

        jokeBtn.setOnClickListener(this)

        generateJoke()


    }

    private fun bannerAd()
    {
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object  : AdListener()
        {
            override fun onAdLoaded() {
                Log.d(TAG, "Ad loaded")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                Log.d(TAG, "Ad failed to load")
            }

            override fun onAdOpened() {
                Log.d(TAG, "Ad opened")
            }

            override fun onAdClicked() {
                Log.d(TAG, "Ad loaded")
            }

            override fun onAdClosed() {
                Log.d(TAG, "Ad Closed")
            }

        }
    }

    override fun onClick(v: View?) {

        generateJoke()

    }

    private fun generateJoke()
    {
        val joke = findViewById<TextView>(R.id.jokeTextView)
        val delivery = findViewById<TextView>(R.id.deliveryTextView)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.w("Response", response.body()?.id.toString())
                joke.text = response.body()?.setup.toString()
            } else {
                joke.text = response.errorBody().toString()
            }

            joke.text = response.body()?.setup
            delivery.text = response.body()?.delivery

        })
    }


}
