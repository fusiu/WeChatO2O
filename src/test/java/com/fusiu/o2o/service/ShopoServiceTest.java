package com.fusiu.o2o.service;

import com.fusiu.o2o.BaseTest;
import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.Area;
import com.fusiu.o2o.entity.PersonInfo;
import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.entity.ShopCategory;
import com.fusiu.o2o.enums.ShopStateEnum;
import com.fusiu.o2o.service.impl.ShopServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopoServiceTest extends BaseTest{

    //@Autowired
    //private ShopService shopService;
    private ShopService shopService = new ShopServiceImpl();

    @Test
    public void testAddShop(){
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

        File shopImg = new File("/Users/fusiu/Downloads/image/xiaohuangren.jpg");

        ShopExecution shopExecution = shopService.addShop(shop, shopImg);
    }
}
