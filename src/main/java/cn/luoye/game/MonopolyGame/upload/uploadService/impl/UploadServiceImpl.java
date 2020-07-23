package cn.luoye.game.MonopolyGame.upload.uploadService.impl;


import cn.luoye.game.MonopolyGame.upload.uploadService.UploadService;
import cn.luoye.game.common.config.CodeMessage;
import cn.luoye.game.common.config.GameResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @Author: hhj
 * @Date: 2019/12/5 16:24
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${upload_url.ip}")
    private String ip;
    @Value("${upload_url.url}")
    private String url;

     /**
      * 文件上传
      *   file  文件
      *   catalog  指定目录（可为空）
      *   url 图片上传地址（不可为空）
      * @Author hhj
      * @Date 2019/12/5 16:26
      */
    public GameResponse uploadFile(MultipartFile file){
        GameResponse gameResponse = new GameResponse();
        try{
            String fileName= (UUID.randomUUID()+"").replaceAll("-","");
            String pathRoot = url;
            String path="";
            String fileSuffix=file.getOriginalFilename();
            //获得文件后缀名称
            String suffix= fileSuffix.substring(fileSuffix.lastIndexOf("."));
            //上传的目录文件名扩展名
            path="/"+fileName+suffix;
            File filepath = new File(pathRoot,fileName);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到目标文件当中
            file.transferTo(new File(pathRoot+path));
            gameResponse.code(CodeMessage.SUCCESS_CODE);
            //return gameResponse.message(ip +"/avatar"+ path);
            return gameResponse.message(url + path);
        }catch (Exception e){
            e.printStackTrace();
            gameResponse.code(CodeMessage.SUCCESS_CODE);
            return gameResponse.message("头像上传失败");
        }
    }
}
