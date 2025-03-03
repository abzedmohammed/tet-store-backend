package com.tet_store.file_upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String saveFile(MultipartFile file) throws IOException;
}
