package net.abb.searchartist.di;

import android.content.Context;
import com.twistedequations.rx2.AndroidRxSchedulers;
import dagger.Component;
import net.abb.searchartist.base.AppScope;
import net.abb.searchartist.base.GsonModule;
import net.abb.searchartist.base.RxMvpAppModule;
import net.abb.searchartist.data.api.service.LastListService;
import net.puertorico.shoolportal.di.NetworkModule;


@AppScope
@Component(modules = {RxMvpAppModule.class,
        NetworkModule.class,
        GsonModule.class, RxModule.class})
public interface AppComponent {

    Context context();

    AndroidRxSchedulers rxSchedulers();

    LastListService lastService();


}
