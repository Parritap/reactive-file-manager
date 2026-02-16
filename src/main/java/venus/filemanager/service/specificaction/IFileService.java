package venus.filemanager.service.specificaction;

import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import venus.filemanager.model.File;
import venus.filemanager.model.FileResponseDTO;

import java.util.List;

public interface IFileService{

    //POST
    // Store username and files as in plural, so I need to put several files in into this function.
    void saveFiles (String fileGroup, List<String> base64Files);

    Mono<FileResponseDTO> saveFile (FileResponseDTO FileDTO);
    Flux<File> getFiles (String fileGroup) throws ResponseStatusException;
}
