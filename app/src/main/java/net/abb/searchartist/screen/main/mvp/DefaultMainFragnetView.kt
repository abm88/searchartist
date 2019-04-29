package net.abb.searchartist.screen.main.mvp

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_main.view.*
import net.abb.searchartist.R
import net.abb.searchartist.data.model.Album
import net.abb.searchartist.data.model.Result
import net.abb.searchartist.screen.main.MainFragment
import net.abb.searchartist.screen.main.views.MainAdapter
import net.abb.searchartist.utils.EndlessRecyclerViewScrollListener
import net.abb.searchartist.utils.NavigationManager

class DefaultMainFragnetView(mainFragment : MainFragment) : MainFragnetView ,
    FrameLayout(mainFragment.context!!) , MainAdapter.LastItemListener{



    override val view: View
        get() = this



    private  var adapter : MainAdapter
    private lateinit var endlessScroll : EndlessRecyclerViewScrollListener


    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        View.inflate(context , R.layout.fragment_main , this)
        adapter = MainAdapter(this )
        rvSearch.layoutManager = LinearLayoutManager(mainFragment.context!!)
        rvSearch.adapter = adapter

        endlessScroll = object  : EndlessRecyclerViewScrollListener(rvSearch.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mainFragment.onLoadMore()
            }

        }
        rvSearch.addOnScrollListener(endlessScroll)
    }



    override fun subscribetoSearch(): PublishSubject<String> {
        val ps = PublishSubject.create<String>()
//        val ps = Observable<String>()
        evSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
//                ps.onComplete()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                 Log.d("testTag" , "onQueryTextChange : ${s.toString()}")

                setProgress(true )
                ps.onNext(s.toString())
            }

        })

//        evSearch.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(search: String?): Boolean {
//                ps.onComplete()
//                return true
//            }
//
//            override fun onQueryTextChange(search: String?): Boolean {
//                Log.d("testTag" , "onQueryTextChange : ${search}")
//                ps.onNext(search!!)
//                return true
//            }
//
//        })

        return ps
    }

    override fun subscribetoSearchChanges() : Observable<CharSequence> {
        return RxTextView.textChanges(evSearch)
            .toFlowable(BackpressureStrategy.BUFFER)
            .toObservable()

    }
    override fun noData() {

    }

    override fun clear() {

        adapter.clear()
    }
    override fun setData(results: Result) {
        adapter.setData(results)
    }

    override fun setProgress(b: Boolean) {
        if (b){
            pbSearch.visibility = View.VISIBLE
        }else{
            pbSearch.visibility = View.GONE

        }
    }

    override fun onLastItemClicked(album: Album) {
        NavigationManager.navigateToDetail(view?.context , album)
    }
    override fun resetRecycler() {
        endlessScroll.resetState()
    }




}