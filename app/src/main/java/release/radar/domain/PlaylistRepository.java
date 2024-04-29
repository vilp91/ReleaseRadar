package release.radar.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByName(String name);
    List<Playlist> findByAlbumIdsContaining(String albumIds);
    @Query("SELECT p FROM Playlist p WHERE :albumId MEMBER OF p.albumIds")
    List<Playlist> findPlaylistsByAlbumId(@Param("albumId") String albumIds);
}