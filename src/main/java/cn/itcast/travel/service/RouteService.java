package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteService {
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    public PageBean<Route> pageQuery(int currentPage,String rname,int minPrice,int maxPrice);

    Route findOne(String rid);

    void addCount(String rid, int count);

    List<Route> findByCount();

    List<Route> findByRdate();

    List<Route> findByTheme();

    List<Route> findByCid(String cid);

}
