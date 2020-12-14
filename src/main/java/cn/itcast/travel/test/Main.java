package cn.itcast.travel.test;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;

public class Main {
    public static void main(String[] args) {
        FavoriteService favoriteService = new FavoriteServiceImpl();
        PageBean favorite = favoriteService.findFavorite(1, 1);
        System.out.println(favorite);
    }
}
