package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDaoImpl();
        User byUsername = dao.findByUsername("123");
        System.out.println(byUsername);
    }
}

