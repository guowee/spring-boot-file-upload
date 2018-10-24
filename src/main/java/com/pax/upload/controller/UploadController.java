package com.pax.upload.controller;


import com.pax.upload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UploadController {


    @Value("${web.file.path}")
    private String filePath;


    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return fileUploadService.uploadFile(file);
    }

    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    @ResponseBody
    public Object uploads(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        return fileUploadService.uploadFiles(files);
    }


}
