package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteService {
    public boolean isFavorite(String rid,int uid);

    void addFavorite(String rid, int uid);

    public PageBean findFavorite(int uid, int currentPage);
}
