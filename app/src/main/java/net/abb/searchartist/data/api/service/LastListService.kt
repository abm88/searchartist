package net.abb.searchartist.data.api.service

import io.reactivex.Observable
import net.abb.searchartist.data.model.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface LastListService {

    @GET("2.0")
    fun getLastList(@Query("method") method : String = "album.search" ,
                    @Query("album") alnum : String ,
                    @Query("api_key") apy_key : String,
                    @Query("page") page : String ,
                    @Query("format") format : String) : Observable<Result>
}