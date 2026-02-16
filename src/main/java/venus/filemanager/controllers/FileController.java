package venus.filemanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import venus.filemanager.model.File;
import venus.filemanager.model.FileResponseDTO;
import venus.filemanager.service.implementation.FileService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileController {


    private final FileService fileService;

    //Describe de problem

    @GetMapping("/getfiles/{fileGroup}")
    public Flux<FileResponseDTO> getFiles (@PathVariable String fileGroup) {
        return fileService.getFiles(fileGroup)
                .map(file -> FileResponseDTO.builder()
                        .id(file.getId())
                        .fileGroup(file.getFileGroup())
                        .fileName(file.getFileName())
                        .base64Data(convertBytesToBase64(file.getData()))
                        .build());
    }


    @PostMapping("/postFile/{fileGroup}")
    public Mono<FileResponseDTO> saveFile (@PathVariable String fileGroup, @RequestBody FileResponseDTO FileDTO) {
        return fileService.saveFile(FileDTO);
    }

    @GetMapping("/getBinaryFiles/{fileGroup}")
    public Flux<List<Byte>> getBinaryFiles (@PathVariable String fileGroup) {
        return fileService.getFiles(fileGroup)
                .map(File::getData);
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
