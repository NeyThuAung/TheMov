package com.exam.themov.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.exam.themov.DetailActivity
import com.exam.themov.databinding.PopularMoviesBinding
import com.exam.themov.databinding.TestActivityBinding
import com.exam.themov.models.Anime.AnimeData
import com.exam.themov.models.Anime.AnimeResult
import com.exam.themov.models.Result
import kotlinx.coroutines.CoroutineScope

class AnimeAdapter(
    private var animeList: List<AnimeResult>
): RecyclerView.Adapter<AnimeAdapter.AnimeHolder>() {

//    interface ItemClickInterface{
//        fun onItemClicked(id:Int)
//    }

    inner class AnimeHolder(private val binding: TestActivityBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(animeList : AnimeResult){
            val IMG_BASEURL = "https://image.tmdb.org/t/p/w500/"
            binding.ivMovImg.load(
                Uri.parse(IMG_BASEURL+animeList.poster_path)
            ){
                crossfade(1000)
                crossfade(true)
            }
            binding.tvTitle.text=animeList.name

//            binding.root.setOnClickListener {
//                listener.onItemClicked(animeList.id)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeAdapter.AnimeHolder {
        return AnimeHolder(
            TestActivityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: AnimeAdapter.AnimeHolder, position: Int) {
        holder.bind(animeList[position])
        holder.itemView.setOnClickListener{
            val intent =Intent(it.context,DetailActivity::class.java)
            intent.putExtra("movieName",animeList[position].name)
            intent.putExtra("movieBackDrop",animeList[position].backdrop_path)
            intent.putExtra("moviePoster",animeList[position].poster_path)
            intent.putExtra("Rating",animeList[position].vote_average)
            intent.putExtra("Popularity",animeList[position].popularity)
            intent.putExtra("Overview",animeList[position].overview)
            it.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }


}