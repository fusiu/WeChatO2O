package com.fusiu.o2o.dto;

import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //店铺数量
    private int count;

    //操作的 shop ( 增删改店铺的时候用到)
    private Shop shop;

    //shop列表（查询店铺列表的时候用到）
    private List<Shop> shopList;

    //无参的构造器
    public ShopExecution(){

    }

    //店铺操作失败的时候使用的构造器
    public ShopExecution(ShopStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //店铺操作成功的时候使用的构造器
    public ShopExecution(ShopStateEnum stateEnum,Shop shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    //
    public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
