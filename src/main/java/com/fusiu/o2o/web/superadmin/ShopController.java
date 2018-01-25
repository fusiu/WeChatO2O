package com.fusiu.o2o.web.superadmin;

import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.Area;
import com.fusiu.o2o.entity.PersonInfo;
import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.entity.ShopCategory;
import com.fusiu.o2o.enums.ShopStateEnum;
import com.fusiu.o2o.service.ShopService;
import com.fusiu.o2o.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;


    @GetMapping(value = "/shopinsert")
    public String shopinsert(){

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

        return shopExecution.toString()+"";
    }
}
