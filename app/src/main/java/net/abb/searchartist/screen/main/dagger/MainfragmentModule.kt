package net.abb.searchartist.screen.main.dagger

import com.twistedequations.rx2.AndroidRxSchedulers
import dagger.Module
import dagger.Provides
import net.abb.searchartist.data.api.service.LastListService
import net.abb.searchartist.screen.main.MainFragment
import net.abb.searchartist.screen.main.mvp.DefaultMainFragnetView
import net.abb.searchartist.screen.main.mvp.MainFragmentModel
import net.abb.searchartist.screen.main.mvp.MainFragmentPresenter
import net.abb.searchartist.screen.main.mvp.MainFragnetView


@Module
class MainfragmentModule(private val mainFragment : MainFragment) {


    @Provides
    @MainFragmentScope
    fun providesView() : MainFragnetView {
        return DefaultMainFragnetView(mainFragment)
    }

    @Provides
    @MainFragmentScope
    fun providesModel(lastListService: LastListService) : MainFragmentModel {
        return MainFragmentModel(mainFragment , lastListService)
    }


    @Provides
    @MainFragmentScope
    fun providesPresenter( view : MainFragnetView,
                          model : MainFragmentModel ,
                          rxSchedulers: AndroidRxSchedulers
    ) : MainFragmentPresenter {
         return MainFragmentPresenter(mainFragment ,view , model , rxSchedulers)
    }

}