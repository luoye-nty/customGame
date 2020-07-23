package cn.luoye.game.MonopolyGame.upload.controller;


import cn.luoye.game.MonopolyGame.upload.uploadService.UploadService;
import cn.luoye.game.common.config.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author nty
 * @date 2020/7/17
 */
@RestController
@RequestMapping("/upload")
public class UploadImageController {

    @Autowired
    UploadService uploadService;

    @ResponseBody
    @PostMapping("image")
    public GameResponse uploadaaa(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //String path = request.getSession().getServletContext().getRealPath("upload");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String path = "F:/testimage/" + date + "/";
        UUID uuid = UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
        // String fileName = uuid.toString() + originalFilename;
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String fileName = uuid.toString() + extendName;
        File dirr = new File(path, fileName);
        File filepath = new File(path);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        GameResponse gameResponse = new GameResponse();
        try {
            file.transferTo(dirr);
            return gameResponse.message(path + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return gameResponse.message("头像上传失败");
        }
    }

    @ResponseBody
    @RequestMapping("/avatar")
    public GameResponse uploadAvatar(MultipartFile file){
        return uploadService.uploadFile(file);
    }
}
