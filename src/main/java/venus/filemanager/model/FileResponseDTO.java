package venus.filemanager.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileResponseDTO {
    String id;
    String fileGroup;
    String fileName;
    String base64Data;
}
