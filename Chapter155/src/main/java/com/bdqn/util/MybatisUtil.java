package com.bdqn.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory sessionFactory;
    static {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-cfg.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession openSession(){
        return sessionFactory.openSession();
    }
}
