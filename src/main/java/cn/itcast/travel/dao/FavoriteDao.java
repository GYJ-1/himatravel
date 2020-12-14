package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteDao {
    public Favorite findByRidAndUid(int rid, int uid);

    int findCountByRid(int rid);

    void addFavorite(int parseInt, int uid);

    public int countByuid(int uid);

    public List<Route> findRouteByUR(int uid,int start ,int pageSize);
}
