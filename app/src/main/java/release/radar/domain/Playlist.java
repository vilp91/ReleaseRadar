package release.radar.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })

    @JoinTable(
    
        name = "playlist_albums",
    
        joinColumns = @JoinColumn(name = "playlist_id"),
    
        inverseJoinColumns = @JoinColumn(name = "album_id")
    
    )
    
    private List<Album> albums = new ArrayList<>();

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    

    // Additional fields for more explicit representations of tracks or albums
    @ElementCollection
    private List<String> trackIds;  // If you want to distinguish tracks specifically

    @ElementCollection
    private List<String> albumIds;  // If you want to distinguish albums specifically

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTrackIds() {
        return trackIds;
    }

    public void setTrackIds(List<String> trackIds) {
        this.trackIds = trackIds;
    }

    public List<String> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(List<String> albumIds) {
        this.albumIds = albumIds;
    } 

}


