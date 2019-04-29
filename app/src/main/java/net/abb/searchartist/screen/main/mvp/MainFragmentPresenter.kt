package net.abb.searchartist.screen.main.mvp

import android.util.Log
import android.widget.Toast
import com.twistedequations.rx2.AndroidRxSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import net.abb.searchartist.data.model.Result
import net.abb.searchartist.screen.main.MainFragment
import java.util.concurrent.TimeUnit

class MainFragmentPresenter(private val fragment : MainFragment,
                            private val view : MainFragnetView,
                            private val model : MainFragmentModel,
                            private val rxSchedulers: AndroidRxSchedulers){

    companion object {
        const val TAG = "mMainFragmentPresenter"
    }

    private val composaiteDisposble = CompositeDisposable()
    private var page = 1
    private var lastSearch = ""

    fun onCreate(){

        composaiteDisposble.add(subscribeToSearch())

    }

    fun onDestroy(){
        composaiteDisposble.clear()
    }

    private fun subscribeToSearch(): Disposable {
        Log.d(TAG , "string to search : subscribed to search ")
        return view.subscribetoSearchChanges()
            .debounce(650 , TimeUnit.MILLISECONDS)
            .filter {
                Log.d(TAG , "string to search : ${it}")
                !it.isNullOrBlank()
            }
            .subscribe {
            processSearch(it.toString())
        }
    }

    private fun processSearch(search: String) {
        page = 1
        view.resetRecycler()
        lastSearch = search
        fragment.activity?.runOnUiThread{
            view.setProgress(true )
        }
        composaiteDisposble.add(Observable.just(search)
                .distinctUntilChanged()
                .switchMap { model.getAlbumData(it?.toString() , page ) }
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.mainThread())
                .subscribe ( {
                    processResult(it!!)
                    view.setProgress(false)
                } , {
                    view.noData()
                    it?.let{
                        view.setProgress(false)
                        it?.toString()?.let {
                            Log.d(TAG , it)
                            processError(it)
                        }
                    }
                })

        )
    }


    private fun loadMore(search: String) {
        lastSearch = search
        fragment.activity?.runOnUiThread{
            view.setProgress(true )
        }
        composaiteDisposble.add(Observable.just(search)
            .distinctUntilChanged()
            .switchMap { model.getAlbumData(it?.toString() , page ) }
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.mainThread())
            .subscribe ( {
                processResult(it!!)
                view.setData(it!!)
                view.setProgress(false)
            } , {
                view.noData()
                it?.let{
                    view.setProgress(false)
                    it?.toString()?.let {
                        Log.d(TAG , it)
                        processError(it)
                    }
                }
            })

        )
    }

    private fun processError(error: String) {

        if (error.contains("404")){
            Toast.makeText(view.view.context , "404 NOT FOUND " , Toast.LENGTH_LONG).show()
        }
    }

    private fun processResult(results: Result) {
        Log.d(TAG , "processResult called")
        view.clear()
        view.setData(results)
    }

    fun onloadMore() {
        page+=1
        loadMore(lastSearch)
    }


}