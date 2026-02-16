package venus.filemanager.service.implementation;

import io.netty.handler.codec.base64.Base64Decoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import venus.filemanager.model.File;
import venus.filemanager.model.FileResponseDTO;
import venus.filemanager.repository.FileRepository;
import venus.filemanager.service.specificaction.IFileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService implements IFileService {

    private final FileRepository fileRepository;

    @Override
    public void saveFiles(String fileGroup, List<String> base64Files) {

    }

    @Override
    public Mono<FileResponseDTO> saveFile(FileResponseDTO fileDTO) {
        return fileRepository.save(
                File.builder().fileName(fileDTO.getFileName())
                        .fileGroup(fileDTO.getFileGroup())
                        .data(decodeData(fileDTO.getBase64Data()))
                        .build()
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

    private List<Byte> decodeData(String base) {
        return new Base64Decoder()
    }
}
