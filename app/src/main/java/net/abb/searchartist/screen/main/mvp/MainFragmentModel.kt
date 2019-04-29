package net.abb.searchartist.screen.main.mvp

import io.reactivex.Observable
import net.abb.searchartist.AppConstants
import net.abb.searchartist.data.api.service.LastListService
import net.abb.searchartist.data.model.Result
import net.abb.searchartist.screen.main.MainFragment

class MainFragmentModel(private val fragment : MainFragment ,
                        private val lastListService : LastListService) {


    fun getAlbumData(albumName : String , page : Int) : Observable<Result>{
        return lastListService.getLastList("album.search" ,albumName , AppConstants.API_KEY  , "$page","json" )
    }

}