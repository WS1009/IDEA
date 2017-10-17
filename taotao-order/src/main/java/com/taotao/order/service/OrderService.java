package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

/**
 * Created by Administrator on 2017-09-01.
 */
public interface OrderService {
    public TaotaoResult createOrder(OrderInfo orderInfo);
}
