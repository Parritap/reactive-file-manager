package venus.filemanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
/**
 * DTO meant to be sent by the frontend to the back and not otherwise.
 */
public record FileResponseDTO(
        String fileGroup,
        String fileName,
        String base64Data
) {
}
