import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2017-09-04.
 */
public class JedisUtils {
    //1 定义一个连接池对象
    private final static JedisPool POOL;

    static{
        //1设置连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置池中最大的链接数量
        config.setMaxTotal(50);
        //设置空闲时池中保有的最大连接数量
        config.setMaxIdle(10);
        //2设置连接池对象
        POOL = new JedisPool("192.168.174.128", 6379);
    }

    /**
     * 从池中获取连接
     */
    public static Jedis getJedis(){
        return POOL.getResource();
    }


}
