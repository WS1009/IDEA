package com.taotao;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2017-08-20.
 */
public class TestUpload {

    @Test
    public void testUpload() throws IOException, MyException {
        // 1、把FastDFS提供的jar包添加到工程中
        // 2、初始化全局配置。加载一个配置文件。
        ClientGlobal.init("E:\\AS_Eclipse_WorkSpace\\IDEA_Space\\Test\\taotao\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");

        // 3、创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();
        // 4、创建一个TrackerServer对象。
        TrackerServer trackerServer = null;

            trackerServer = trackerClient.getConnection();

        // 5、声明一个StorageServer对象，null。
        StorageServer storageServer = null;
        // 6、获得StorageClient对象。
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 7、直接调用StorageClient对象方法上传文件即可。
        String[] strings = new String[0];

        strings = storageClient.upload_file("C:\\Users\\Administrator\\Desktop\\timg.jpg", "jpg", null);

        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testFastDfsClient() throws Exception {
        FastDFSClient client = new FastDFSClient("properties/client.conf");
        String uploadFile = client.uploadFile("C:\\Users\\Administrator\\Desktop\\22.jpg","jpg");
        System.out.println(uploadFile);
    }

}

