package com.fusiu.o2o.dao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.fusiu.o2o.entity.Area;
import com.fusiu.o2o.entity.PersonInfo;
import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.entity.ShopCategory;
import com.fusiu.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopDaoTest extends Serializers.Base{

    @Autowired
    private ShopDao shopDao;

    @Test
    public void testShopInsert(){

        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("猛牛旗舰店");
        shop.setShopDesc("累了困了，就喝猛牛合成奶");
        shop.setShopAddr("E 栋");
        shop.setPhone("19988887777");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");

        int i = shopDao.insertShop(shop);
        System.out.println("===========================================");
        System.out.println(i);
    }
}
