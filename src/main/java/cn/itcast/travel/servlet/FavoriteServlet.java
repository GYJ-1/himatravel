package cn.itcast.travel.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet{

    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private RouteService routeService = new RouteServiceImpl();

    public void farQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");

        User user = (User) request.getSession().getAttribute("user");

        int uid;
        if(user==null){
            return;
        }else {
            uid=user.getUid();
        }

        PageBean favorite = favoriteService.findFavorite(uid, Integer.parseInt(currentPageStr));
        writeValue(favorite,response);
    }

    public void findPageBean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String currentPageStr = request.getParameter("currentPage");
        String rname = request.getParameter("rname");
        String minpricestr = request.getParameter("minprice");
        String maxpricestr = request.getParameter("maxprice");

        int currentPage = 1;
        int minprice = 0;
        int maxprice = 999999;
        if (!rname.equals("null")&&rname != null && rname.length() > 0) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }
        if (!currentPageStr.equals("null")&&currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        if (!minpricestr.equals("null")&&minpricestr != null && minpricestr.length() > 0) {
            minprice = Integer.parseInt(minpricestr);
        }
        if (!maxpricestr.equals("null")&&maxpricestr != null && maxpricestr.length() > 0) {
            maxprice = Integer.parseInt(maxpricestr);
        }

       PageBean<Route> pb =  routeService.pageQuery(currentPage,rname,minprice,maxprice);

        writeValue(pb,response);
    }
}
