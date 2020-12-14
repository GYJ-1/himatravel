package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
     UserDao dao = new UserDaoImpl();
    public boolean regist(User user) {
        User u = dao.findByUsername(user.getUsername());
        if(u!=null){
            return false;
        }
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");

        dao.regist(user);

        String context = "<a href=\"http://localhost/travel/user/active?code="+user.getCode()+"\">点击激活【黑马旅行网】</a>";
        //发送邮件
        MailUtils.sendMail(user.getEmail(),context,"激活邮件");

        return true;
    }

    public boolean active(String code) {
       User user =  dao.findByCode(code);
       if(user!=null){
           dao.updateStatus(user);
           return true;
       }else {
           return false;
       }
        
    }

    @Override
    public User login(User user) {
        User u = dao.findByUsernameAndPassWord(user.getUsername(), user.getPassword());
        return u;
    }
}
