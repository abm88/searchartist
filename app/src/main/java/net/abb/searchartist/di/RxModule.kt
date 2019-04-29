package net.abb.searchartist.di

import com.twistedequations.rx2.AndroidRxSchedulers
import com.twistedequations.rx2.DefaultAndroidRxSchedulers
import dagger.Module
import dagger.Provides
import net.abb.searchartist.base.AppScope


@Module
class RxModule {

    @AppScope
    @Provides
    fun rxSchedulers(): AndroidRxSchedulers {
        return DefaultAndroidRxSchedulers()
    }
}