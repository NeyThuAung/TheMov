package com.exam.themov.seemore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.exam.themov.MainActivity
import com.exam.themov.adapter.AnimeAdapter
import com.exam.themov.api.Request
import com.exam.themov.api.RetrofitHelper
import com.exam.themov.databinding.ActivityPopularSeemoreBinding
import com.exam.themov.repository.PopularRepository
import com.exam.themov.viewmodels.MainViewModel
import com.exam.themov.viewmodels.ViewModelFactory

class popularSeeMoreActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPopularSeemoreBinding
    private lateinit var animeAdapter: AnimeAdapter
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_popular_seemore)
        binding= ActivityPopularSeemoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request = RetrofitHelper.getInstance().create(Request::class.java)


        val popularRepository = PopularRepository(request)



        mainViewModel = ViewModelProvider(this, ViewModelFactory(popularRepository)
        ).get(MainViewModel::class.java)

        mainViewModel.anime.observe(this) {
            Log.d("POPULAR", it.results.toString())
            Log.d("Search", "onCreate: ${it.results}")
            animeAdapter = AnimeAdapter(it.results)
            binding.rvPopularSM.also {
                it.setHasFixedSize(true)

                it.layoutManager = GridLayoutManager(this@popularSeeMoreActivity, 2)
                it.adapter = animeAdapter
            }
        }
    }
    private fun onClick(){
        binding.ibBack.setOnClickListener {
            var intent = Intent(this@popularSeeMoreActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}