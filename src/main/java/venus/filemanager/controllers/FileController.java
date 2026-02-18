package venus.filemanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import venus.filemanager.dto.FilesRequestDTO;
import venus.filemanager.model.File;
import venus.filemanager.dto.FileResponseDTO;
import venus.filemanager.service.implementation.FileService;


@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileController {


    private final FileService fileService;

    @PostMapping("/saveFile")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<File> saveFile(@RequestBody FileResponseDTO FileDTO) {
        return fileService.saveFile(FileDTO);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/saveFiles")
    public Flux<File> saveFiles(@RequestBody FilesRequestDTO dto) {
        return fileService.saveFiles(dto).log();
    }

}
