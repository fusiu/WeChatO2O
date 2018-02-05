package com.fusiu.o2o.dao;

import com.fusiu.o2o.BaseTest;
import com.fusiu.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest{

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){
        ShopCategory shopCategory = new ShopCategory();
        ShopCategory shopCategoryParent = new ShopCategory();
        shopCategoryParent.setShopCategoryId(1l);
        shopCategory.setParent(shopCategoryParent);
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(new ShopCategory());
        System.out.println("==================================");
        System.out.println(shopCategories.size());
    }
}
