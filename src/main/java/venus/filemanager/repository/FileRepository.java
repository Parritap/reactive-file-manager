package venus.filemanager.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import venus.filemanager.model.File;

@Repository
public interface FileRepository extends R2dbcRepository<File, String> {
    Flux<File> getAllByFileGroup(String fileGroup);

    long countFileByFileGroup(String fileGroup);
}
