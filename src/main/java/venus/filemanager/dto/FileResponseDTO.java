package venus.filemanager.dto;

import lombok.Builder;

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
