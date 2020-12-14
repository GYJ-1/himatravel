package cn.itcast.travel.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.CategoryServise;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private   RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        //页面查询
        String currentPageStr = request.getParameter("currentPage");
        String pagesizeStr = request.getParameter("pagesize");
        String cidStr = request.getParameter("cid");

        String rname = request.getParameter("rname");


        if (rname != null && rname.length() > 0) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }

        int cid = 0;
        int currentpage = 1;
        int pagesize = 5;

        if (!cidStr.equals("null")&&cidStr!=null && cidStr.length() > 0) {
            cid = Integer.parseInt(cidStr);
        }
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentpage = Integer.parseInt(currentPageStr);
        }
        if (pagesizeStr != null && pagesizeStr.length() > 0) {
            pagesize = Integer.parseInt(pagesizeStr);
        }
        PageBean<Route> pageBean = service.pageQuery(cid, currentpage, pagesize, rname);

        writeValue(pageBean, response);
    }

    //根据id查询旅游线路的详细信息
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String rid = request.getParameter("rid");

        Route route =  service.findOne(rid);

        writeValue(route,response);
    }

    //判断登录用户是否收藏过该线路
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            uid=0;
        }else {
            uid=user.getUid();
        }
        boolean flag = favoriteService.isFavorite(rid, uid);
        writeValue(flag,response);
    }

    //添加收藏
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            return;
        }else {
            uid=user.getUid();
        }
        favoriteService.addFavorite(rid, uid);
        Route route = service.findOne(rid);
        int count = route.getCount()+1;
        service.addCount(rid,count);
    }

    //根据收藏数排序查询
    public void findByCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        List<Route> list = service.findByCount();
        writeValue(list,response);
    }

    //根据添加日期
    public void findByRdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Route> list = service.findByRdate();
        writeValue(list,response);
    }

    //是否是主题旅游
    public void findByTheme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Route> list = service.findByTheme();
        writeValue(list,response);
    }

    //根据线路类别
    public void findByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String cid = request.getParameter("cid");
        System.out.println(cid);
        List<Route> list = service.findByCid(cid);
        writeValue(list,response);
    }
}
