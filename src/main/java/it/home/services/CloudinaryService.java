package it.home.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import it.home.config.CloudinaryConfig;

@Service
public class CloudinaryService {

	private final Cloudinary cloudinary;

    public CloudinaryService(CloudinaryConfig config) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", config.getCloudName(),
            "api_key", config.getApiKey(),
            "api_secret", config.getApiSecret()
        ));
    }

    public String upload(MultipartFile file) throws IOException {
        @SuppressWarnings("unchecked")
		Map<String, Object> result = cloudinary.uploader().upload(
            file.getBytes(),
            ObjectUtils.asMap("folder", "opere")
        );
        return (String) result.get("secure_url");
    }
	
}
