package net.puertorico.shoolportal.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.abb.searchartist.base.AppScope
import net.abb.searchartist.screen.main.MainFragment


@Suppress("unused")
@Module
abstract class MainActivityFragmentModule{

    @AppScope
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment() : MainFragment


}