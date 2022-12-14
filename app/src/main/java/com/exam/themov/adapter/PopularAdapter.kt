package com.exam.themov.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.exam.themov.databinding.PopularMoviesBinding
import com.exam.themov.models.Result

class PopularAdapter(
    val popularList: List<Result>
): RecyclerView.Adapter<PopularAdapter.PopularHolder>() {

    inner class PopularHolder(private val binding: PopularMoviesBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(popularList : Result){
            val IMG_BASEURL = "https://image.tmdb.org/t/p/w500/"
            binding.ivMovImg.load(
                Uri.parse(IMG_BASEURL+popularList.poster_path)
            ){
                crossfade(1000)
                crossfade(true)

            }
            binding.tvTitle.text=popularList.title.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        return PopularHolder(
            PopularMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        holder.bind(popularList[position])
    }

    override fun getItemCount(): Int {
       return popularList.size
    }
}