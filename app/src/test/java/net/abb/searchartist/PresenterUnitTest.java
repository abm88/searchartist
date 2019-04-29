package net.abb.searchartist;


import android.util.Log;
import com.twistedequations.rx2.AndroidRxSchedulers;
import io.reactivex.Observable;
import net.abb.searchartist.screen.main.MainFragment;
import net.abb.searchartist.screen.main.mvp.DefaultMainFragnetView;
import net.abb.searchartist.screen.main.mvp.MainFragmentModel;
import net.abb.searchartist.screen.main.mvp.MainFragmentPresenter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;






@RunWith(MockitoJUnitRunner.class)
public class PresenterUnitTest {

    @Mock
    MainFragmentModel model ;

    @Mock
    DefaultMainFragnetView view ;

    @Mock
    MainFragment fragment ;

    @Mock
    AndroidRxSchedulers rxSchedulers ;

    @Mock
    Log log ;

    @InjectMocks
    MainFragmentPresenter presenter ;




    @BeforeClass
    public static void setup(){

    }


    @Test
    public void testPresenter(){
        presenter.onCreate();
        Mockito.verify(presenter , Mockito.times(1)).onCreate() ;

    }


    @Test
    public void testSearch(){
        when(view.subscribetoSearchChanges()).thenAnswer(new Answer<Observable<String>>() {
            @Override
            public Observable<String> answer(InvocationOnMock invocation) throws Throwable {
                return Observable.just("song");
            }
        }) ;


        CharSequence search = view.subscribetoSearchChanges().blockingFirst() ;

        assertEquals("song" , search.toString());

    }

}
