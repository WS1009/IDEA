import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017-09-06.
 * 阿里云redis测试
 */
public class jedistest {
    @Test
    public void jedisTest() {
        try {
            String host = "r-2zebae20d7d126b4.redis.rds.aliyuncs.com";//控制台显示访问地址
            int port = 6379;
            Jedis jedis = new Jedis(host, port);
            //鉴权信息
            jedis.auth("Ws123456");//password
            String key = "redis";
            String value = "aliyun-redis";
            //select db默认为0
            jedis.select(1);
            //set一个key
            jedis.set(key, value);
            System.out.println("Set Key " + key + " Value: " + value);
            //get 设置进去的key
            String getvalue = jedis.get(key);
            System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
            jedis.quit();
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}