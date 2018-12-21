package com.pengshengtj.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImgUtils {

    /**
     *
     * @param path：图片上传的路径
     * @param file:要处理的图片
     * @return
     */
    public static String onload(String path, MultipartFile file){
        /**
         * 1.判断file是不是图片
         * oldFile:原始的文件名
         */
      String oldFile= file.getOriginalFilename();
      String newFile=String.valueOf(System.currentTimeMillis()+oldFile.substring(oldFile.lastIndexOf(".")));
        /**
         * 2.判读path路径是否存在
         * 若不存在，则去创建
         */
        File pathFile=new File(path);
      if (!pathFile.exists()){
          pathFile.mkdir();
      }
        /**
         * 3.判断路径中的文件是否存在
         * 若不存在，则去创建
         */
        File pathFileName=new File(path,newFile);
        if (!pathFileName.exists()){
            try {
                pathFileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 上传图片
         */
        try {
            file.transferTo(pathFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }

}
