package com.taotao.service;

import com.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017-08-20.
 */
public interface PictureService {
    public PictureResult uploadPic(MultipartFile picFile);
}
