package com.fusiu.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.Area;
import com.fusiu.o2o.entity.PersonInfo;
import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.entity.ShopCategory;
import com.fusiu.o2o.enums.ShopStateEnum;
import com.fusiu.o2o.service.AreaService;
import com.fusiu.o2o.service.ShopCategoryService;
import com.fusiu.o2o.service.ShopService;
import com.fusiu.o2o.utils.CodeUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    /**
     * 获取店铺信息
     * @return modelMap
     */
    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopInitInfo(){
        Map<String,Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try{
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }

        return modelMap;
    }

    /**
     * 店铺注册
     * @param request
     * @return modelMap
     */
    @RequestMapping(value = "/registershop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        /**
         *
         * 判断验证码是否正确，不正确就执行 if 语句体并且返回
         */
        Boolean checkVerifyCode = CodeUtil.checkVerifyCode(request);
        if (!checkVerifyCode){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入了错误的验证码");
            return modelMap;
        }

        //1.接收并转化相应的参数，包括店铺信息和图片信息
        String shopstr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper  = new ObjectMapper();
        Shop shop = null;
        try{
            shop = mapper.readValue(shopstr,Shop.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

//        创建 CommonsMultipartResolver 对象，并且通过该对象获取 CommonsMultipartFile 对象
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
//        判断是否有文件上传，如果有，则获取 "shopImg" 对应的表单信息
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","上传图片不能为空");
            return modelMap;
        }
//        2.注册店铺
        if (shop != null && shopImg != null){
//            获取店主信息：店主注册、登陆后，会在 session 中保存店主信息。此处使用测试的店主信息
            PersonInfo owner = new PersonInfo();
//           此处为测试用店主信息，写完用户注册和登陆后更改为 session 中保存的店主 TODO
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
            modelMap.put("errMsg","请正确填写店铺信息");
            return modelMap;
        }
    }

}
