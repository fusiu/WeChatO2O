package com.fusiu.o2o.utils;

public class PathUtil {
    /**
     * 不同的操作系统的文件分隔符不同，路径的书写格式也不同
     * 因此需要获取操作系统的文件分隔符 swparator
     */
    private static String separator = System.getProperty("file.separator");


    /**
     * 获取项目图片的跟路径
     * @return basePath
     */
    public static String getImgBasePath() {
        /**
         * 获取操作系统的类型
         */
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/projectdev/image";

        }else if (os.toLowerCase().startsWith("mac")){
            basePath = "/Users/fusiu/Documents/fusiu/WeChatO2O/image";
        }else {
            basePath = "/home/fusiu/WeChatO2O/image";
        }
        return basePath.replace("/",separator);
    }

    /**
     * 根据业务需求返回相对应的子路径
     * @param shopId 店铺 ID 用于生成子目录
     * @return imagePath 根据 shopId 创建的子目录
     */
    public static String getShopImagePath(Long shopId){
        String imagePath = String.format("/upload/item/shop/%s/",shopId);
        return imagePath.replace("/",separator);
    }
}
