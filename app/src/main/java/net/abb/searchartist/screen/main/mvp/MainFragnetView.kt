package net.abb.searchartist.screen.main.mvp

import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import net.abb.searchartist.data.model.Result

interface MainFragnetView {
    val view : View


    fun subscribetoSearch() : PublishSubject<String>
    fun subscribetoSearchChanges() : Observable<CharSequence>
    fun noData()
    fun setData(results: Result)
    fun setProgress(b: Boolean)
    fun clear()
    fun resetRecycler()


}