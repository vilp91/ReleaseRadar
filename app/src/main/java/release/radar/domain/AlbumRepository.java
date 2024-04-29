package release.radar.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlbumRepository extends JpaRepository<Album, String> {
    
    @Query("SELECT a FROM Album a JOIN a.playlists p WHERE p.id = :playlistId")
    List<Album> findAlbumsByPlaylistId(@Param("playlistId") Long playlistId);

}