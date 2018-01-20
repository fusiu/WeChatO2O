package com.fusiu.o2o.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ImageUtil {
    /**
     * 通过线程获取当前项目的绝对值路径
     */
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final Random random = new Random();
    //日志
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将 CommonsMultipartFile 转换成 File 类
     * @param commonsMultipartFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile commonsMultipartFile){
        File newFile =  new File(commonsMultipartFile.getOriginalFilename());
        try {
            commonsMultipartFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }
    /**
     * 处理缩略图，并返回新生成图片的绝对路径
     * @param thumbnail 获取的文件名
     * @param targetAddr 子目录
     * @return 最终的图片保存路径
     */
    public static String generateThumbnail(File thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = String.format("%s%s%s", targetAddr, realFileName, extension);
        logger.debug("current relativeAddr is :"+relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :"+PathUtil.getImgBasePath()+relativeAddr);
        try {
            Thumbnails.of(thumbnail)
                    .size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(String.format("%s%s", basePath, "/watermark.jpg"))), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;

    }

    /**
     * 生成随机文件名，当前的毫秒数+5位随机数
     * @return 随机文件名
     */
    public static String getRandomFileName(){
        //获取随机的五位数
        int rannum = random.nextInt(89999)+10000;
        long nowTime = System.currentTimeMillis();
        return String.format("%s%s",rannum,nowTime);
    }

    /**
     * 获取输入文件流的扩展名
     * @param thumbnail 输入文件流
     * @return 扩展名
     */
    private static String getFileExtension(File thumbnail){
        String originalFilename = thumbnail.getName();
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    /**
     * 创建目标路径所涉及到的目录，即 /home/work/fusiu/WeChatO2O/xxx.jpg,
     * 那么 home work fusiu WeChatO2O 这几个文件夹都要自动创建
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr){
        String realFileParentPath = String.format("%s%s",PathUtil.getImgBasePath(),targetAddr);
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

}
