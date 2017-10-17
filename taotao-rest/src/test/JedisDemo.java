import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2017-09-04.
 */
public class JedisDemo {
    /**
     * 单实例链接Redis数据库
     */
    @Test
    public void run1(){
        //设置关闭防火墙
        Jedis jedis = new Jedis("192.168.174.128", 6379);
        jedis.set("sex","nan");
        String sex = jedis.get("sex");
        System.out.println(sex);
    }

    /**
     * Redis的连接池
     */
    @Test
    public void run2(){
        //1设置连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置池中最大的链接数量
        config.setMaxTotal(50);
        //设置空闲时池中保有的最大连接数量
        config.setMaxIdle(10);
        //2设置连接池对象
        JedisPool pool = new JedisPool("192.168.174.128", 6379);
        //3从池中获取连接对象
        Jedis jedis = pool.getResource();
        String sex = jedis.get("sex");
        System.out.println(sex);
        //4jedis需要连接池归还池中
        jedis.close();
    }
}
