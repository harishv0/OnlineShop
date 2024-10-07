package com.example.backend.Storage;
import com.cloudinary.Cloudinary;
import java.io.IOException;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
@Service
public class CloudinaryServices {
    
    private Cloudinary cloudinary;

    @Autowired
    public void Cloudinary(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    public String uploadPost(MultipartFile file, String folderName, String postName)throws IOException{
        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
            "folder",folderName,
            "public_id", postName,
            "resource_type", "auto"
        )).get("url").toString();
    }
    
    public String profileUpload(MultipartFile image, String folderName, String userName) throws IOException{
        return cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap(
            "folder",folderName,
            "public_id",userName,
            "resource_type", "auto"
        )).get("url").toString();
    }
}
