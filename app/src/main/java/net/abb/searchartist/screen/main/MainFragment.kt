package net.abb.searchartist.screen.main

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.abb.searchartist.base.RxMvpApp
import net.abb.searchartist.screen.main.dagger.DaggerMainFragmentComponent
import net.abb.searchartist.screen.main.dagger.MainfragmentModule
import net.abb.searchartist.screen.main.mvp.MainFragmentPresenter
import net.abb.searchartist.screen.main.mvp.MainFragnetView
import javax.inject.Inject


class MainFragment : Fragment() {

    @Inject
    lateinit var presenter : MainFragmentPresenter
    @Inject
    lateinit var view : MainFragnetView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        DaggerMainFragmentComponent.builder()
            .appComponent(RxMvpApp.get(activity as Activity)
            .component())
            .mainfragmentModule(MainfragmentModule(this)).build().inject(this)

        presenter.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return view.view
    }

    fun onLoadMore() {
        presenter.onloadMore()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
