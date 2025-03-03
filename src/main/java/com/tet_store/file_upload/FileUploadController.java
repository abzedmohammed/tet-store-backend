package com.tet_store.file_upload;

import com.tet_store.utils.StandardJsonResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/upload")
public class FileUploadController {
    public final FileUploadService fileUploadService;

    @PostMapping
    public StandardJsonResponse uploadFile(@RequestParam("file")MultipartFile file){
        try {
            String filename = fileUploadService.saveFile(file);
            StandardJsonResponse response = new StandardJsonResponse();
            response.setSuccess(true);
            response.setMessage("message","File uploaded successfully", response);
            response.setData("result", filename,response);
            return response;
        } catch (IOException e) {
            StandardJsonResponse response = new StandardJsonResponse();
            response.setSuccess(false);
            System.out.println(e);
            response.setMessage("message","File upload failed: " , response);
            return response;
        }
    }
}
