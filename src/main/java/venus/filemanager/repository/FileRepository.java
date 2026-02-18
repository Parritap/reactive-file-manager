package venus.filemanager.repository;

import lombok.NonNull;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import venus.filemanager.model.File;

@Repository
public interface FileRepository extends R2dbcRepository<File, String> {
    Flux<File> getAllByFileGroup(String fileGroup);

    long countFileByFileGroup(String fileGroup);

    @Query("SELECT * FROM file WHERE file_name = :fileName")
    Mono<@NonNull File> findByFileName(String s);

    Mono<File> findFileByFileName(String fileName);
}
