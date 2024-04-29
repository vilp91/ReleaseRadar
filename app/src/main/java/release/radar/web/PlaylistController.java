package release.radar.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import release.radar.domain.Album;
import release.radar.domain.AlbumRepository;
import release.radar.domain.Playlist;
import release.radar.domain.PlaylistRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {
    
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private AlbumRepository albumRepository; // Add this line

    @GetMapping("/create")
    public String createPlaylistForm(Model model) {
        return "studentlist"; // The name of the Thymeleaf template for creating a playlist
    }
    
    @PostMapping("/create")
    public String createPlaylist(
            @RequestParam("name") String name,
            @RequestParam(value = "albumIds", required = false) List<String> albumIds,
            Model model
    ) {
        Playlist playlist = new Playlist();
        playlist.setName(name);

        // Fetch and add Albums
        if (albumIds != null && !albumIds.isEmpty()) {
            List<Album> albums = new ArrayList<>();
            for (String albumId : albumIds) {
                Album album = albumRepository.findById(albumId).orElse(null); // Use albumRepository instead of playlistRepository
                if (album != null) {
                    albums.add(album);
                }
            }
            playlist.setAlbums(albums);
        }

    playlistRepository.save(playlist);

    return "redirect:/playlists/list"; // Redirect to playlist listing
}

    @GetMapping("/list")
    public String listPlaylists(Model model) {
        List<Playlist> playlists = playlistRepository.findAll();
        model.addAttribute("playlists", playlists);
        return "playlists"; // Name of the Thymeleaf template
    }

    @GetMapping("/view/{id}")
    public String viewPlaylist(@PathVariable Long id, Model model) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);
        if (playlist == null) {
            return "redirect:/playlists/list"; // Redirect or display an error message
        }
        model.addAttribute("playlist", playlist);
        return "playlistView"; // Name of the Thymeleaf template to view a specific playlist
    }
    

    @GetMapping("/delete/{id}")
    public String deletePlaylist(@PathVariable Long id) {
        playlistRepository.deleteById(id);
        return "redirect:/playlists/list"; // Redirect after deletion
    }
 

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex, Model model) {
        model.addAttribute("error", "Required parameter is missing: " + ex.getParameterName());
        return "error/parameter-missing";  // Path to an error view
    }
}







}



