package cn.itcast.travel.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryServise;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryServise servise = new CategoryServiceImpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从redis中查询
        List<Category> cs = servise.findAll();
        writeValue(cs, resp);
    }

}
