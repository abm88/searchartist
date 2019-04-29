package net.abb.searchartist.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {

    @AppScope
    @Provides
    public Gson gson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
