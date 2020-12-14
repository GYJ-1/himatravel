package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pb = new PageBean<Route>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid,rname);

        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize,rname);
        pb.setList(list);

        int totalPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize+1);

        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public PageBean<Route> pageQuery(int currentPage, String rname, int minPrice, int maxPrice) {
        PageBean<Route> pb = new PageBean<Route>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(8);

        System.out.println(rname+minPrice+maxPrice);

        int totalCount =  routeDao.findTotalCount(rname,minPrice,maxPrice);
        if(totalCount>100){
            totalCount=100;
        }
        pb.setTotalCount(totalCount);

        int totalPage = totalCount%8==0?totalCount/8:(totalCount/8+1);
        pb.setTotalPage(totalPage);

        List<Route> list = routeDao.findByPage(currentPage,rname,minPrice,maxPrice);
        pb.setList(list);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        //查询route对象
        Route route = routeDao.findById(Integer.parseInt(rid));
        //查询route图片
        List<RouteImg> imgs = routeImgDao.findByRid(route.getRid());

        route.setRouteImgList(imgs);

        //根据route sid查询卖家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);

        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        return route;
    }

    @Override
    public void addCount(String rid, int count) {
        routeDao.addCount(Integer.parseInt(rid),count);
    }

    @Override
    public List<Route> findByCount() {
        return routeDao.findByCount();
    }

    @Override
    public List<Route> findByRdate() {
        return routeDao.findByRdate();
    }

    @Override
    public List<Route> findByTheme() {
        return routeDao.findByTheme();
    }

    @Override
    public List<Route> findByCid(String cid) {
        return routeDao.findByCid(Integer.parseInt(cid));
    }
}
