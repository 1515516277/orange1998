package com.ming.until;

import com.ming.entity.UserEntity;
import org.springframework.util.StringUtils;

import java.sql.*;

public class JdbcResult {
    static  UserEntity userEntity = null;

    public static UserEntity jdbc(String name) throws Exception {

        String sql="select * from user where username='"+name+"' limit 1";
        System.out.println("sql:"+sql);
        String URL = "jdbc:mysql://127.0.0.1:3306/ssm?useUnicode=true&amp;characterEncoding=utf-8";
        String USER = "root";
        String PASSWORD = "root";
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据库链接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        //4.处理数据库的返回结果(使用ResultSet类)
        while (rs.next()) {
            System.out.println(rs.getString("username") + " "
                    + rs.getString("password"));
            if(!StringUtils.isEmpty(rs.getString("username"))){
                userEntity=new UserEntity();
                userEntity.setUsername(rs.getString("username") );
                userEntity.setPassword(rs.getString("password"));
                userEntity.setRole(rs.getString("role"));
            }

        }
        //关闭资源
        rs.close();
        st.close();
        conn.close();
        return userEntity;
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(StringUtils.isEmpty(null));
        System.out.println(jdbc("zhangsan1"));
    }
}
