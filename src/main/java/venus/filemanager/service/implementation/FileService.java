package venus.filemanager.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import venus.filemanager.dto.FileResponseDTO;
import venus.filemanager.dto.FilesRequestDTO;
import venus.filemanager.exceptions.FileSizeExceeded;
import venus.filemanager.model.File;
import venus.filemanager.repository.FileRepository;
import venus.filemanager.service.specificaction.IFileService;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService implements IFileService {

    private final FileRepository fileRepository;

    @Override
    public void saveFiles(String fileGroup, List<String> base64Files) {

    }

    @Override
    public Mono<File> saveFile(FileResponseDTO fileDTO) {
        return fileRepository.save(
                File.builder().fileName(fileDTO.fileName())
                        .fileGroup(fileDTO.fileGroup())
                        .data(Base64.getDecoder().decode(fileDTO.base64Data()))
                        .build()
        );
    }


    @Override
    public Flux<File> saveFiles(FilesRequestDTO dto) throws FileSizeExceeded {
        return Flux.fromIterable(dto.files())
                .flatMap(fileDTO -> {
                    byte[] decodedData = Base64.getDecoder().decode(fileDTO.base64Data());

                    // Validate file size before saving
                    if (decodedData.length > 102400) { // 100KB limit
                        return Mono.error(new FileSizeExceeded(
                                "File '" + fileDTO.fileName() + "' exceeds 100KB limit. Size: " + decodedData.length + " bytes"
                        ));
                    }

                    File file = File.builder()
                            .fileGroup(dto.fileGroup())
                            .fileName(fileDTO.fileName())
                            .data(decodedData)
                            .build();

                    return fileRepository.findByFileName(fileDTO.fileName())
                            .flatMap(existing -> {
                                file.setId(existing.getId());
                                return fileRepository.save(file);
                            })
                            .switchIfEmpty(fileRepository.save(file));
                });
    }

    @Override
    public Flux<File> getFiles(String fileGroup) throws ResponseStatusException {
        return fileRepository.getAllByFileGroup(fileGroup)
                .switchIfEmpty(Flux.error(
                        new ResponseStatusException(
                                HttpStatusCode.valueOf(404),
                                "No files found for file group: " + fileGroup
                        )
                ));
    }

}
