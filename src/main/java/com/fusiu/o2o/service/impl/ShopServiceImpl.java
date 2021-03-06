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
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private ShopDao shopDao;

    @Override
//    @Transactional 是 SSM 中的处理事务的注解
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {

//        判断 shop 是否为空
        if (shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try{
//            给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
//            添加店铺信息
            int i = shopDao.insertShop(shop);

            if (i<=0){
                throw new ShopOperationException("店铺创建失败");
            }else {
                if (shopImgInputStream != null){
//                    存储图片
                    try{
                        addShopImg(shop,shopImgInputStream,fileName);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg erro:"+e.getMessage());
                    }

//                    更新店铺的图片地址
                    int i1 = shopDao.updateShop(shop);
                    if (i1 <= 0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new ShopOperationException("addShop error:"+e.getMessage());
        }
        return new ShopExecution();
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
        //获取 shop 图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String s = ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        shop.setShopImg(s);
    }
}
