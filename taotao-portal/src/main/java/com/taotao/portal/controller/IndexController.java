package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-21.
 */
@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        //取打广告位
        String json = contentService.getAd1List();
        //传递给页面
        model.addAttribute("ad1",json);
        return "index";
    }

    @RequestMapping(value = "/posttest", method = RequestMethod.POST)
    @ResponseBody
    public String postTest(@RequestBody Map map) {
        System.out.println(map.get("name"));
        System.out.println(map.get("pass"));
        return "OK";
    }
}
