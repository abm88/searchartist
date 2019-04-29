package net.abb.searchartist.screen.main.views

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.last_item_adapter_view.view.*
import net.abb.searchartist.R
import net.abb.searchartist.data.model.Album
import net.abb.searchartist.data.model.Result

class MainAdapter( val listener : LastItemListener) : RecyclerView.Adapter<MainAdapter.LastItemViewHolder>() {

    private val albums : MutableList<Album> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): MainAdapter.LastItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.last_item_adapter_view , parent , false  )
        return LastItemViewHolder(view , listener)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(viewHolder: MainAdapter.LastItemViewHolder, position: Int) {
        viewHolder.bind(albums[position])
    }

    fun setData(results: Result) {
        Log.d("testTag" , "set data ")
        albums.addAll( results.results.albummatches?.album!!)
        notifyDataSetChanged()
    }

    fun clear() {
        albums.clear()
        notifyDataSetChanged()
    }


    class LastItemViewHolder(itemView : View , private val listener : LastItemListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(album : Album){
            itemView.tvSearchResultTitle.text = album.name
            itemView.searchTitlerSub.text = album.artist!!
            itemView.setOnClickListener {
                listener.onLastItemClicked(album)
            }

        }

    }


    interface LastItemListener{
        fun onLastItemClicked(album : Album)
    }

}