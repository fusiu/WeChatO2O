package com.fusiu.o2o.dao;

import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")Shop shopCategory);
}

