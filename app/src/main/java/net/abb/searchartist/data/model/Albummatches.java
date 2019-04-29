
package net.abb.searchartist.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Albummatches {

    @SerializedName("album")
    @Expose
    private List<Album> album = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Albummatches() {
    }

    /**
     * 
     * @param album
     */
    public Albummatches(List<Album> album) {
        super();
        this.album = album;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

}
