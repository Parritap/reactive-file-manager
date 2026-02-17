package venus.filemanager.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Builder
@Data
@Table(name = "file")
public class File {
    @Id
    String id;
    @Column("file_group")
    String fileGroup;
    @Column("file_name")
    String fileName;
    @Column("data")
    byte[] data;
}