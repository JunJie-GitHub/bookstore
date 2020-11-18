package com.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;

    static {
        try {
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取数据库连接池中的Connection
     * @return 如果返回null, 说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException throwables) {
            System.out.println("getConnect报错了!!!");
            throwables.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con){
        if (con!=null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
}
