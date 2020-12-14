package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    public void regist(User user);

    public User findByUsername(String username);


    void updateStatus(User user);

    User findByCode(String code);

    User findByUsernameAndPassWord(String name, String password);
}
