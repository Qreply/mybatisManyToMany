package com.wd.test;

import com.wd.entity.Role;
import com.wd.mapper.RoleMapper;
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

public class RoleTest {

    private InputStream in;
    private SqlSession session;
    private RoleMapper roleMapper;



    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件,生成读取字节流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Mapper代理的接口对象
        roleMapper = session.getMapper(RoleMapper.class);
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
        List<Role> roles = roleMapper.findAll();
        for (Role role : roles){
            System.out.println("--------每个role的信息------------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }

    }

}
