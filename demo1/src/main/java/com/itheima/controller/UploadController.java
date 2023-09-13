package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.QcloudUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private QcloudUtils qcloudUtils;

//    // 本地存储方案
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile file) throws IOException {
//        log.info("文件上传：{},{},{}",username,age,file);
//        String originalFilename = file.getOriginalFilename();
//        String exname = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFilename = UUID.randomUUID() + exname;
//        log.info("新文件名，{}",newFilename);
//
//        file.transferTo( new File("D://images"+newFilename) );
//
//        return Result.success();
//    }

    @PostMapping("upload")
    public Result upload(MultipartFile image){
        log.info("文件上传，{}",image);
        String url = qcloudUtils.upload(image);
        log.info("文件上传成功，{}",url);
        return Result.success(url);
    }
}
