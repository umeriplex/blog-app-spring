package tecqasr.blog.app.blogguist.services.implimentations;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tecqasr.blog.app.blogguist.services.FileService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String updateImage(String path, MultipartFile file) throws IOException {
        // File name
        String name = file.getOriginalFilename(); // image.jpeg

        // Random name generate for the file
        String fileName = UUID.randomUUID().toString().concat(name != null ? name.substring(name.lastIndexOf(".")) : "null"); // 1234567890.jpeg

        // Full Path of the file
        String filePath = path + File.separator + fileName; // /home/tecqasr/blogguist/images/1234567890.jpeg

        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }

        // File Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fillPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fillPath);
        return is;
    }
}
