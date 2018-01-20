package com.fusiu.o2o.dao;

import com.fusiu.o2o.entity.Shop;

public interface ShopDao {
    /**
     * 新增店铺
     * @param shop 要新增的店铺
     * @return 操作结果
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop 要更新的店铺信息
     * @return 操作结果
     */
    int updateShop(Shop shop);
}
