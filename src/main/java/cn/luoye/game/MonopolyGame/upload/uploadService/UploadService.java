package cn.luoye.game.MonopolyGame.upload.uploadService;


import cn.luoye.game.common.config.GameResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nty
 * @date 2020/7/20
 */
public interface UploadService {
    GameResponse uploadFile(MultipartFile file);
}
