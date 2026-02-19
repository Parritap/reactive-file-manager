package venus.filemanager.model;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import venus.filemanager.exceptions.FileSizeExceeded;

@Builder
@Data
@Table(name = "file")
public class File {

    private static final long MAX_FILE_SIZE = 102400;


    private static Log log = LogFactory.getLog(File.class);

    @Id
    String id;
    @Column("file_group")
    String fileGroup;
    @Column("file_name")
    String fileName;
    @Column("data")
    byte[] data;


    public static void checkMaxSize(byte[] data) throws FileSizeExceeded {
        if (data.length > MAX_FILE_SIZE) throw new FileSizeExceeded(
                "Max Size is " + MAX_FILE_SIZE + " and file data passed has lenght: " + data
        );
    }
}

