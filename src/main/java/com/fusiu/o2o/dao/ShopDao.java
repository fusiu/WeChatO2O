package com.fusiu.o2o.dao;

import com.fusiu.o2o.entity.Shop;

public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);
}
