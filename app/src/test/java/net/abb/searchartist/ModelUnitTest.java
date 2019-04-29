package net.abb.searchartist;


import io.reactivex.Observable;
import net.abb.searchartist.data.api.service.LastListService;
import net.abb.searchartist.data.model.Album;
import net.abb.searchartist.data.model.Albummatches;
import net.abb.searchartist.data.model.Result;
import net.abb.searchartist.data.model.Results;
import net.abb.searchartist.screen.main.MainFragment;
import net.abb.searchartist.screen.main.mvp.MainFragmentModel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.*;
import java.util.*;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ModelUnitTest {

    @InjectMocks
    MainFragmentModel model ;

    @Mock
    MainFragment fragment ;

    @Mock
    LastListService service ;

    private static Result result ;

    @BeforeClass
    public static void setup(){
        result = new Result() ;
        Results results = new Results() ;
        Albummatches albummatches = new Albummatches() ;
        List<Album> albums = new ArrayList<>() ;
        albums.add(new Album()) ;
        albums.add(new Album()) ;
        albummatches.setAlbum(albums);
        results.setAlbummatches(albummatches);
        result.setResults(results);

    }

    @Test
    public void whenRquestDataThenAtListOne(){

        when(model.getAlbumData("" , 1)).thenReturn(
                Observable.just(result)
        );

        Result r = model.getAlbumData("" , 1).blockingFirst() ;


        assertEquals(2 , result.getResults().getAlbummatches().getAlbum().size());
    }
}
