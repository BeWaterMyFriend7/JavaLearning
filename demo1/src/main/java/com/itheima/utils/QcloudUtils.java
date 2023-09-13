package com.itheima.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.qcloud.cos.http.HttpProtocol.*;

@Slf4j
@Component
public class QcloudUtils {

    // 1 初始化用户身份信息（secretId, secretKey）。
    // SECRETID 和 SECRETKEY 请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
    @Value("${spring.tengxun.SecretId}")
    private String secretId;//用户的 SecretId，建议使用子账号密钥，授权遵循最小权限指引，降低使用风险。子账号密钥获取可参见 https://cloud.tencent.com/document/product/598/37140
    @Value("${spring.tengxun.SecretKey}")
    private String secretKey;//用户的 SecretKey，建议使用子账号密钥，授权遵循最小权限指引，降低使用风险。子账号密钥获取可参见 https://cloud.tencent.com/document/product/598/37140
    @Value("${spring.tengxun.region}")
    private String region;
    @Value("${spring.tengxun.bucketName}")
    private String bucketName;
    @Value("${spring.tengxun.url}")
    private String endpoint;

    /**
     * 初始化客戶端
     * @return
     */
    private COSClient initClient(){
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        return cosClient;
    }

    public String upload(MultipartFile file) {
        // 3 生成 cos 客户端。
        if(file == null) return null;

        String originalFilename = file.getOriginalFilename();
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String filename = "test/"+ UUID.randomUUID().toString() +"-"+ originalFilename;

        COSClient cosClient = null;
        try {
            File localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
            cosClient = initClient();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

            String url = endpoint.split("//")[0] + "//" + bucketName
                    + "." + endpoint.split("//")[1] + "/" + filename;
            return url;
        } catch (IOException e) {
            log.info("上传文件失败");
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            cosClient.shutdown();
        }
    }


}
