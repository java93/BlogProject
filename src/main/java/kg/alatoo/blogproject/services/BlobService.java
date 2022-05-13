package kg.alatoo.blogproject.services;

import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class BlobService {
    public Blob getBlob(InputStream inputStream, int fileSize) {
        try {
            byte[] bytes = new byte[fileSize];
            inputStream.read(bytes);
            return new SerialBlob(bytes);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
