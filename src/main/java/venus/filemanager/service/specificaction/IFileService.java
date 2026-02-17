package venus.filemanager.service.specificaction;

import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import venus.filemanager.dto.FilesRequestDTO;
import venus.filemanager.model.File;
import venus.filemanager.dto.FileResponseDTO;

import java.util.List;

public interface IFileService{

    //POST
    // Store username and files as in plural, so I need to put several files in into this function.
    void saveFiles (String fileGroup, List<String> base64Files);

    Mono<File> saveFile (FileResponseDTO FileDTO);

    /**
     * Saves a list of files under the same FileGroup and so that is why FileRequestDTO is used.
     * @return
     */
    Flux<File> saveFiles (FilesRequestDTO files);
    Flux<File> getFiles (String fileGroup) throws ResponseStatusException;
}
