package com.fusiu.o2o.service.impl;

import com.fusiu.o2o.dao.ShopDao;
import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.enums.ShopStateEnum;
import com.fusiu.o2o.exceptions.ShopOperationException;
import com.fusiu.o2o.service.ShopService;
import com.fusiu.o2o.utils.ImageUtil;
import com.fusiu.o2o.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution addShop(Shop shop, File shopImg) {

        //判断 shop 是否为空
        if (shop == null){
            return new ShopExecution(ShopStateEnum.CHECK.NULL_SHOP);
        }

        try{
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int i = shopDao.insertShop(shop);

            if (i<=0){
                throw new ShopOperationException("店铺创建失败");
            }else {
                if (shopImg != null){
                    //存储图片
                    try{
                        addShopImg(shop,shopImg);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg erro:"+e.getMessage());
                    }

                    //更新店铺的图片地址
                    int i1 = shopDao.updateShop(shop);
                    if (i1 <= 0){
                        throw new ShopOperationException("更新图片地址失败");
                    }

                }
            }

        }catch (Exception e){
            throw new ShopOperationException("addShop error:"+e.getMessage());
        }
        return null;
    }

    private void addShopImg(Shop shop, File shopImg) {
        //获取 shop 图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String s = ImageUtil.generateThumbnail(shopImg, dest);

        shop.setShopImg(s);
    }
}
