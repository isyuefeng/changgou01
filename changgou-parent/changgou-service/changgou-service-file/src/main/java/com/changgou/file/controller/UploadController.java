package com.changgou.file.controller;


import com.changgou.file.FastDFSFile;
import com.changgou.file.util.FastDFSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yf
 * @date 2021/5/27
 **/
@RestController
public class UploadController {
    /**
     * 接收页面的上传图片的请求
     * 请求是一个POST
     * 请求路径 /upload
     * 返回值：图片的url
     *
     * @param file @RequestParam(name="file")可以不用写,如果前端写的就是file参数
     * @return 路径
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                //1.获取字节数组
                byte[] bytes = file.getBytes();
                //2.获取原文件的扩展名
                String extName = StringUtils.getFilenameExtension(file.getOriginalFilename());//1234.jpg
                //3.使用工具类调用方法 实现上传图片到fastdfs somewhere
                FastDFSFile filex = new FastDFSFile(
                        file.getOriginalFilename(),
                        bytes,
                        extName
                );
                // [0] -->group1
                // [1] --->M00/00/00/wKjThF9p7QuAafXvAAX7uZ3HmDk345.jpg
                // 访问的路径： http://192.168.211.132:8080/group1/M00/00/00/wKjThF9p7QuAafXvAAX7uZ3HmDk345.jpg
                String[] upload = FastDFSClient.upload(filex);

                String realPath = "http://192.168.211.132:8080/" + upload[0] + "/" + upload[1];

                //4.拼接URL 返回给前端
                return realPath;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
