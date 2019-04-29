package net.abb.searchartist.screen.main.dagger

import dagger.Component
import net.abb.searchartist.di.AppComponent
import net.abb.searchartist.screen.main.MainFragment


@MainFragmentScope
@Component(modules = [MainfragmentModule::class] ,
    dependencies = [AppComponent::class])
interface MainFragmentComponent {

    fun inject(fragment : MainFragment)

}