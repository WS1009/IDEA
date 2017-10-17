package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-08-31.
 */
public interface UserService {
    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
}
