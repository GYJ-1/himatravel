package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "SELECT count(*) FROM tab_route WHERE 1=1 ";
        List<Object> params = new ArrayList();
        StringBuilder sb = new StringBuilder(sql);
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (!rname.equals("null")&&rname!=null&&rname.length()>0){
            sb.append(" and rname LIKE ? ");
            params.add('%'+rname+'%');
        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = "select * from tab_route where 1 = 1 ";
        List params = new ArrayList();
        StringBuilder sb = new StringBuilder(sql);
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (!rname.equals("null")&&rname!=null&&rname.length()>0){
            sb.append(" and rname like ? ");
            params.add('%'+rname+'%');
        }
        sb.append(" limit ? , ? ");//分页条件
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findById(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }

    @Override
    public void addCount(int rid, int count) {
        String sql = "UPDATE tab_route SET count=? WHERE rid = ?";
        template.update(sql,count,rid);
    }

    @Override
    public List<Route> findByCount() {
        String sql = "SELECT * FROM tab_route ORDER BY count DESC LIMIT 0,4";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public List<Route> findByRdate() {
        String sql = "SELECT * FROM tab_route ORDER BY rdate DESC LIMIT 0,4";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public List<Route> findByTheme() {
        String sql = "SELECT * FROM tab_route WHERE isThemeTour =1 LIMIT 0,4";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public List<Route> findByCid(int cid) {
        String sql = "SELECT * FROM tab_route WHERE cid = ? ORDER BY RAND() LIMIT 8;";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid);
    }

    @Override
    public int findTotalCount(String rname, int minPrice, int maxPrice) {
        String sql = "SELECT count(*) FROM tab_route WHERE 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if(rname!=null&& !rname.equals("null") && rname.length()>0){
            sb.append(" AND rname LIKE ? ");
            params.add('%'+rname+'%');
        }
        sb.append(" AND price BETWEEN ? AND ?");
            params.add(minPrice);
            params.add(maxPrice);
            sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int currentPage,String rname, int minPrice, int maxPrice) {
        String sql ="SELECT * FROM tab_route  WHERE 1=1 ";

        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if(rname!=null&& !rname.equals("null") && rname.length()>0){
            sb.append(" AND rname LIKE ? ");
            params.add('%'+rname+'%');
        }
        sb.append(" AND price BETWEEN ? AND ? ");
        params.add(minPrice);
        params.add(maxPrice);
        sb.append(" ORDER BY count DESC LIMIT ?,8 ");
        int start = (currentPage-1)*8;
        params.add(start);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());
    }
}
