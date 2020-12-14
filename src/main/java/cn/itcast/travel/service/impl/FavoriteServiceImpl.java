package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.FavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDao dao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = dao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite!=null; //如果有值返回ture,没值为false
    }

    @Override
    public void addFavorite(String rid, int uid) {
        dao.addFavorite(Integer.parseInt(rid),uid);
    }

    @Override
    public PageBean findFavorite(int uid, int currentPage) {
        PageBean pageBean = new PageBean();
        int pageSize = 12;
        int start = (currentPage-1)*12;
        int count = dao.countByuid(uid);
        int totalPage = count%pageSize==0?count/pageSize:(count/pageSize+1);
        pageBean.setTotalCount(count);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalPage);
        List<Route> routes = dao.findRouteByUR(uid, start, pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(routes);
        return pageBean;
    }

}
