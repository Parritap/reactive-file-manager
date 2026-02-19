package venus.filemanager.exceptions;

public class FileSizeExceeded extends RuntimeException {
    public FileSizeExceeded(String message) {
        super(message);
    }
}
