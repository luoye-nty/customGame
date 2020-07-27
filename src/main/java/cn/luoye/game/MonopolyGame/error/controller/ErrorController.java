package cn.luoye.game.MonopolyGame.error.controller;

import cn.luoye.game.common.config.GameResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 错误页面 controller
 */
@RestController
@RequestMapping("/error")
public class ErrorController {

    /**
     * token错误返回
     * @return
     */
    @RequestMapping("token")
    public GameResponse tokenError(){
        GameResponse gameResponse = new GameResponse();
        gameResponse.code(-1);
        return gameResponse.message("token error");
    }
}
