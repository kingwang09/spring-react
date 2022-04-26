package io.labs.springreact.upload.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Service
public class UploadService {

    private final ServletContext context;

    private final String BASE_LOCATION = "data/";

    public boolean save(MultipartFile uploadFile) {
        try {
            Path baseLocation = Paths.get(context.getRealPath(BASE_LOCATION));
            log.debug("base location: {}", baseLocation);

            if(Files.notExists(baseLocation)) {
                Files.createDirectories(baseLocation);
                log.debug("base location directory created.");
            }

            try (InputStream inputStream = uploadFile.getInputStream()) {
                Path uploadFilePath = baseLocation.resolve(uploadFile.getOriginalFilename());
                Files.copy(inputStream,
                        uploadFilePath,
                        StandardCopyOption.REPLACE_EXISTING);
                log.debug("upload file created. path={}, file={}", uploadFilePath, uploadFilePath.getFileName());
            }
            return true;
        }catch(IOException ex){
            log.error("파일 처리 중 에러 발생 {}", ex.getMessage(), ex);
            return false;
        }
    }

    public Optional<Resource> get(String fileName) {
        try {
            Path baseLocation = Paths.get(context.getRealPath(BASE_LOCATION));
            log.debug("base location: {}", baseLocation);

            Path uploadFilePath = baseLocation.resolve(fileName);
            log.debug("upload file: {}", uploadFilePath);
            return Optional.of(new UrlResource(uploadFilePath.toUri()));
        }catch(IOException ex){
            log.error("파일 조회 중 에러가 발생: {}", ex.getMessage(), ex);
        }
        return Optional.empty();
    }
}
