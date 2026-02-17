package venus.filemanager.dto;

import java.util.List;

public record FilesRequestDTO(
        String fileGroup,
        List<FileAtomDTO> files
){}

