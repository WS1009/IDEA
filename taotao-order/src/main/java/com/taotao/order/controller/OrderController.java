package com.taotao.order.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017-09-01.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     测试数据：
     {"orderId":null,"payment":"11996.00","paymentType":1,"postFee":"0","status":null,"createTime":null,"updateTime":null,"paymentTime":null,"consignTime":null,"endTime":null,"closeTime":null,"shippingName":null,"shippingCode":null,"userId":44,"buyerMessage":"","buyerNick":"zhangsan6","buyerRate":null,"orderItems":[{"id":null,"itemId":"150408100194289","orderId":null,"num":4,"title":"一加手机5 (A5000) 6GB+64GB 月岩灰 全网通 双卡双待 移动联通电信4G手机","price":299900,"totalFee":1199600,"picPath":"http://192.168.174.128/group1/M00/00/00/wKiugFmmc3CACdcOAASFxFqgPj0473.jpg"}],"orderShipping":{"orderId":null,"receiverName":"入云龙","receiverPhone":null,"receiverMobile":"15891588888","receiverState":"北京","receiverCity":"北京","receiverDistrict":"昌平区","receiverAddress":"西三旗 xxxxxxxxx","receiverZip":null,"created":null,"updated":null}}

     */
    @RequestMapping(value="/order/create", method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody OrderInfo orderInfo) {
        try {
            TaotaoResult result = orderService.createOrder(orderInfo);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }


}
