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


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jokeBtn = findViewById<Button>(R.id.jokeButton)

        jokeBtn.setOnClickListener(this);

        generateJoke()


    }

    override fun onClick(v: View?) {

        generateJoke()

    }

    fun generateJoke()
    {
        val joke = findViewById(R.id.jokeTextView) as TextView
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

            joke.text = response.body()?.setup;
            delivery.text = response.body()?.delivery

        })
    }


}
