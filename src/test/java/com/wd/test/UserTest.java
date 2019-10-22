package com.wd.test;

import com.wd.entity.User;
import com.wd.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {

    private InputStream in;
    private SqlSession session;
    private UserMapper userMapper;



    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件,生成读取字节流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Mapper代理的接口对象
        userMapper = session.getMapper(UserMapper.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After //用于在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
        // session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查询所有
     * @throws Exception
     */
    @Test
    public void testFindAll(){
        List<User> users = userMapper.findAll();
        for (User user : users){
            System.out.println("--------每个user的信息------------");
            System.out.println(user);
            System.out.println(user.getRoles());
        }

    }

}
