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

//    @GetMapping("/getfiles/{fileGroup}")
//    public Flux<FileResponseDTO> getFiles (@PathVariable String fileGroup) {
//        return fileService.getFiles(fileGroup)
//                .map(file -> FileResponseDTO.builder()
//                        .id(file.getId())
//                        .fileGroup(file.getFileGroup())
//                        .fileName(file.getFileName())
//                        .base64Data(convertBytesToBase64(file.getData()))
//                        .build());
//    }


    @PostMapping("/saveFile")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<File> saveFile(@RequestBody FileResponseDTO FileDTO) {
        return fileService.saveFile(FileDTO);
    }

//    @GetMapping("/getBinaryFiles/{fileGroup}")
//    public Flux<List<Byte>> getBinaryFiles (@PathVariable String fileGroup) {
//        return fileService.getFiles(fileGroup)
//                .map(File::getData);
//    }

    @PostMapping("/saveFiles")
    public Flux<File> saveFiles(@RequestBody FilesRequestDTO dto) {
        return fileService.saveFiles(dto);
    }


    private String convertBytesToBase64(java.util.List<Byte> bytes) {
        if (bytes == null || bytes.isEmpty()) {
            return "";
        }
        byte[] primitiveBytes = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            primitiveBytes[i] = bytes.get(i);
        }
        return java.util.Base64.getEncoder().encodeToString(primitiveBytes);
    }
}
