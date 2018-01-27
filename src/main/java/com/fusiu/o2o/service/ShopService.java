package com.fusiu.o2o.service;

import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.exceptions.ShopOperationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException;
}

