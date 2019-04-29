package net.puertorico.shoolportal.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.abb.searchartist.base.AppScope
import net.abb.searchartist.screen.main.MainActivity


@Suppress("unused")
@Module
abstract class ActivityModule{

    @AppScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity() : MainActivity

}