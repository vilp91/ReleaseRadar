package release.radar.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "albums") // Optional if table name matches class name
public class Album {
    @Id
    private String id;
    private String name;
    private String artist;

    @ManyToMany(mappedBy = "albums", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Playlist> albums = new ArrayList<>();

    public List<Playlist> getAlbums() {
        return albums;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Playlist> playlists = new ArrayList<>();

    // ... other methods

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}
