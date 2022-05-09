package io.labs.springreact.upload.controller;

import io.labs.springreact.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/upload")
public class UploadController {
    private final UploadService service;

    @PostMapping(value="", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(@RequestPart("uploadFile") MultipartFile uploadFile){
        log.debug("upload File : {}", uploadFile.getOriginalFilename());

        boolean isSaved = service.save(uploadFile);
        if(isSaved) {
            return ResponseEntity.ok(uploadFile.getOriginalFilename());
        }
        return ResponseEntity.badRequest().body("파일 저장 실패");
    }

    @GetMapping(value="/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        log.debug("download File : {}", fileName);

        Optional<Resource> fileOptional = service.get(fileName);
        if(fileOptional.isPresent()){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName+"\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream; charset=utf-8")
                    .body(fileOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


}
