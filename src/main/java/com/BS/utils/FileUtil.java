package com.BS.utils;

import com.aliyun.oss.OSSClient;
import com.BS.constant.OSSClientConstants;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
<<<<<<< HEAD
 * 
 * @Date: 2018/7/21 11:29
=======
>>>>>>> branch 'master' of https://github.com/dingxiaowei1995/bishe-dw.git
 * Describe: 文件工具
 */
public class FileUtil {
	
	static String bucketName = "625073235-1257455783"; //桶的名称
    static String key = "/upload_demo.pdf";         //上传到云上路径
    static String region = "ap-shanghai";//区域北京则  beijing
    static String appId = "1257455783"; //APPID
    static COSCredentials cred = null;
    static TransferManager transferManager = null;
    static COSClient cosClient = null;

    static {
        // 1 初始化用户身份信息(secretId, secretKey)
        //SecretId 是用于标识 API 调用者的身份
        String SecretId = "";
        //SecretKey是用于加密签名字符串和服务器端验证签名字符串的密钥
        String SecretKey = "";
        cred = new BasicCOSCredentials(SecretId, SecretKey);

        // 2 设置bucket的区域,
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
        // 指定要上传到 COS 上的路径
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        // 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
        transferManager = new TransferManager(cosClient, threadPool);
    }

    public static void main(String[] args) {
        //上传
        upload();
        //下载
        //download();
        //删除
        //delete();
        cosClient.shutdown();
    }

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm ss");

    /**
     * 上传
     */
    public static void upload() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("上传开始时间:" + sdf.format(new Date()));
                    // .....(提交上传下载请求, 如下文所属)
                    // bucket 的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
                    String bucket = bucketName + "-" + appId;
                    //本地文件路径
                    File localFile = new File("src/test/resources/test.pdf");
                    PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, localFile);
                    // 本地文件上传
                    Upload upload = transferManager.upload(putObjectRequest);
                    // 异步（如果想等待传输结束，则调用 waitForUploadResult）
                    //UploadResult uploadResult = upload.waitForUploadResult();
                    //同步的等待上传结束waitForCompletion
                    upload.waitForCompletion();
                    System.out.println("上传结束时间:" + sdf.format(new Date()));
                    System.out.println("上传成功");
                    //获取上传成功之后文件的下载地址
                    URL url = cosClient.generatePresignedUrl(bucketName + "-" + appId, key, new Date(new Date().getTime() + 5 * 60 * 10000));
                    System.out.println(url);
                } catch (Throwable tb) {
                    System.out.println("上传失败");
                    tb.printStackTrace();
                } finally {
                    // 关闭 TransferManger
                    transferManager.shutdownNow();
                }
            }
        }).start();

    }

    /**
     * 下载
     */
    public static void download() {
        try {
            //下载到本地指定路径
            File localDownFile = new File("src/test/resources/download.pdf");
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName + "-" + appId, key);
            // 下载文件
            Download download = transferManager.download(getObjectRequest, localDownFile);
            // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            download.waitForCompletion();
            System.out.println("下载成功");
        } catch (Throwable tb) {
            System.out.println("下载失败");
            tb.printStackTrace();
        } finally {
            // 关闭 TransferManger
            transferManager.shutdownNow();
        }
    }

    /**
     * 删除
     */
    public static void delete() {
        new Thread(new Runnable() {
            public void run() {
                // 指定要删除的 bucket 和路径
                try {
                    cosClient.deleteObject(bucketName + "-" + appId, key);
                    System.out.println("删除成功");
                } catch (Throwable tb) {
                    System.out.println("删除文件失败");
                    tb.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 上传文件到阿里云OSS
     * @param file 文件流
     * @return 返回文件URL
     */
    public String uploadFile(File file, String subCatalog){

        //初始化OSSClient
        OSSClient ossClient = AliYunOSSClientUtil.getOSSClient();

        String md5Key = AliYunOSSClientUtil.uploadObject2OSS(ossClient, file, OSSClientConstants.BACKET_NAME,
                OSSClientConstants.FOLDER + subCatalog + "/");
        String url = AliYunOSSClientUtil.getUrl(ossClient, md5Key);
        String picUrl = "https://" + OSSClientConstants.BACKET_NAME + "." + OSSClientConstants.ENDPOINT +
                "/" + OSSClientConstants.FOLDER + subCatalog + "/" + file.getName();

        //删除临时生成的文件
        File deleteFile = new File(file.toURI());
        deleteFile.delete();

        return picUrl;

    }

    /**
     * base64字符转换成file
     * @param base64 图片字符串
     * @return file
     */
    public File base64ToFile(String destPath,String base64, String fileName) {
        File file = null;
        //创建文件目录
        String filePath=destPath;
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            file=new File(filePath+"/"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 将file转换成base64字符串
     * @param path
     * @return
     */
    public String fileToBase64(String path) {
        String base64 = null;
        InputStream in = null;
        try {
            File file = new File(path);
            in = new FileInputStream(file);
            byte[] bytes=new byte[(int)file.length()];
            in.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }

    /**
     * MultipartFile类型文件转File
     * @return File类型文件
     */
    public File multipartFileToFile(MultipartFile multipartFile, String filePath, String fileName){
        File f = null;
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        if("".equals(multipartFile) || multipartFile.getSize() <= 0){
            multipartFile = null;
        } else {
            try {
                InputStream ins = multipartFile.getInputStream();
                f = new File(filePath + fileName);
                OutputStream os = new FileOutputStream(f);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1){
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

}
