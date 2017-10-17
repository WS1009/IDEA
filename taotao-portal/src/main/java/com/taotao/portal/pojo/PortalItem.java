package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * Created by Administrator on 2017-08-30.
 */
public class PortalItem extends TbItem {
    public String[] getImages() {
        String images = this.getImage();
        if (images != null && !images.equals("")) {
            String[] imgs = images.split(",");
            return imgs;
        }
        return null;
    }
}
