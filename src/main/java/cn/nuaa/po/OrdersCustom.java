package cn.nuaa.po;

/**
 * @author WS
 * @version 1.0
 *          通过此类映射订单和用户查询的结果，让此类继承包括字段较多的pojo类
 * @date 创建时间：2017-6-23 下午3:56:48
 */
public class OrdersCustom extends Orders {
    //添加用户的属性
    private String sex;

    private String username;// 用户名称

    private String address;// 用户地址


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrdersCustom [sex=" + sex + ", username=" + username
                + ", address=" + address + "]";
    }


}
