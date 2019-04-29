package net.abb.searchartist.base;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class RxMvpAppModule {

    private final Context context;

    public RxMvpAppModule(Context context) {
        this.context = context;
    }

    @Provides
    @AppScope
    public Context context() {
        return context;
    }




}
