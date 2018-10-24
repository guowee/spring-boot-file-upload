package com.pax.upload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pax.upload.entity.ResponseData;

@Service
public class FileUploadService {

    @Value("${web.file.path}")
    private String filePath;

    @PostConstruct
    public void prepareDirectories() throws IOException {
        Files.createDirectories(Paths.get(filePath));

        /* Fail the context booting if directories are not accessible */
        if (!isDirectoryExist(filePath)) {
            throw new IOException("Cannot access directories.");
        }
    }

    private boolean isDirectoryExist(String directory) {
        return Paths.get(directory).toFile().exists();
    }

    public Object uploadFile(MultipartFile file) {

        // 文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件名
        String fileName = UUID.randomUUID() + suffix;
        // 服务器端保存的文件对象
        File dest = new File(filePath + fileName);
        // 将上传的文件写入到服务器端文件内
        try {
            file.transferTo(dest);

            return new ResponseData(1, "success");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseData(0, "error");

    }

    public Object uploadFiles(MultipartFile[] files) {

        try {
            for (int i = 0; i < files.length; i++) {
                // 文件后缀名
                String suffix = files[i].getOriginalFilename().substring(
                        files[i].getOriginalFilename().lastIndexOf("."));
                // 上传文件名
                String fileName = UUID.randomUUID() + suffix;
                // 服务器端保存的文件对象
                File dest = new File(filePath + fileName);
                // 将上传的文件写入到服务器端文件内
                files[i].transferTo(dest);
            }
            return new ResponseData(1,"success");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseData(0,"error");
    }

    @PreDestroy
    public void deleteDirectories() throws IOException {
        Files.deleteIfExists(Paths.get(filePath));
    }

}
