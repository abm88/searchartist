package net.abb.searchartist.utils

import android.content.Context
import net.abb.searchartist.data.model.Album
import net.abb.searchartist.screen.detail.DetailActivity

class NavigationManager{

    companion object {
        fun navigateToDetail(context: Context , album : Album){
            DetailActivity.startActivity(context , album)
        }
    }
}