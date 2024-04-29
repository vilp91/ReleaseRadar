package release.radar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import release.radar.domain.AlbumRepository;
import release.radar.domain.Playlist;
import release.radar.domain.PlaylistRepository;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Playlist createPlaylist(String name, String albumId) {
        Playlist playlist = new Playlist();
        playlist.setName(name);

        release.radar.domain.Album album = albumRepository.findById(albumId).orElse(null);
        if (album != null) {
            playlist.getAlbums().add(album);
            album.getPlaylists().add(playlist); // Add the Playlist entity to the albums list of the Album entity
            albumRepository.save(album); // Save the Album entity to update the relationship
        }

        return playlistRepository.save(playlist);
    }




    public List<Playlist> findAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist findPlaylistById(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

}