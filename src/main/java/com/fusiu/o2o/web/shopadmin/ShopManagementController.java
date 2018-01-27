package com.fusiu.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.PersonInfo;
import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.enums.ShopStateEnum;
import com.fusiu.o2o.service.ShopService;
import com.fusiu.o2o.utils.HttpServletRequestUtil;
import com.fusiu.o2o.utils.ImageUtil;
import com.fusiu.o2o.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/registershop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //1.接收并转化相应的参数，包括店铺信息和图片信息
        String shopstr = HttpServletRequestUtil.getString(request, "shopstr");
        ObjectMapper mapper  = new ObjectMapper();
        Shop shop = null;
        try{
            shop = mapper.readValue(shopstr,Shop.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null){
            PersonInfo owner = new PersonInfo();
            //sesseion TODO
            owner.setUserId(1l);
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg","请输入店铺信息");
                }
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入店铺信息");
            return modelMap;

        }
    }

//    private static void inputStreamTofile(InputStream ins, File file){
//        OutputStream os = null;
//        try{
//            os = new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = ins.read(buffer)) != -1){
//                os.write(buffer,0,bytesRead);
//
//            }
//        }catch (Exception e){
//            throw new RuntimeException("调用 inputStreamFile 产生异常"+e.getMessage());
//        }finally {
//            try {
//                if (os != null) os.close();
//                if (ins != null) ins.close();
//
//            }catch (IOException e){
//                throw new RuntimeException("inputStreamFile 关闭 io 产生异常"+e.getMessage());
//
//            }
//        }
//    }
}
