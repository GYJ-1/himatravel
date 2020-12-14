package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    public int findTotalCount(int cid,String rname);
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);
    public Route findById(int id);

    void addCount(int rid, int count);

    List<Route> findByCount();

    List<Route> findByRdate();

    List<Route> findByTheme();

    List<Route> findByCid(int cid);

    int findTotalCount(String rname, int minPrice, int maxPrice);

    List<Route> findByPage(int currentPage,String rname, int minPrice, int maxPrice);

}
