package cn.nuaa.test;

import cn.nuaa.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-08-10.
 */
public class MybatisUserTest {

    // 会话工厂
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void createSqlSessionFactory() throws IOException {
        // 配置文件
        String resource = "mybatis/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    // 根据 id查询用户信息
    @Test
    public void testFindUserById() {
        // 数据库会话实例
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 查询单个记录，根据用户id查询用户信息
            // 第一个参数：映射文件中的statement的id，等于=namespace+statement的id
            // 第二个参数：指定和映射文件中匹配的parameterType类型的参数
            User user = sqlSession.selectOne("test.findUserById", 1);
            // 输出用户信息
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }


    // 根据用户名称模糊查询用户信息
    @Test
    public void testFindUserByUsername() {
        // 数据库会话实例
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 查询单个记录，根据用户id查询用户信息
            List<User> list = sqlSession.selectList("test.findUserByUsername","张");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }



    // 添加用户信息
    @Test
    public void testInsert() {
        // 数据库会话实例
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 添加用户信息
            User user = new User();
            user.setUsername("张小明");
            user.setAddress("河南郑州");
            user.setSex("1");
            user.setBirthday(new Date());
            sqlSession.insert("test.insertUser", user);
            // 提交事务
            sqlSession.commit();
            System.out.println("userID: " + user.getId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }



    // 根据id删除用户
    @Test
    public void testDelete() {
        // 数据库会话实例
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 删除用户
            sqlSession.delete("test.deleteUserById", 7);
            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }



    // 更新用户信息
    @Test
    public void testUpdate() {
        // 数据库会话实例
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 更新用户的信息
            User user = new User();
            user.setId(6);
            user.setUsername("张小芳");
            user.setAddress("河南郑州");
            user.setSex("1");
            user.setBirthday(new Date());
            sqlSession.update("test.updateUser", user);
            // 提交事务
            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

}
