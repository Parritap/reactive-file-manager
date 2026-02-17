package venus.filemanager.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import venus.filemanager.dto.FilesRequestDTO;
import venus.filemanager.model.File;
import venus.filemanager.dto.FileResponseDTO;
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
    public Flux<File> saveFiles(FilesRequestDTO dto) {
        return fileRepository.saveAll(
                dto.files().stream().map(e -> File
                        .builder()
                        .fileGroup(dto.fileGroup())
                        .fileName(e.fileName())
                        .data(Base64.getDecoder().decode(e.base64Data()))
                        .build()
                ).toList()
        );
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
