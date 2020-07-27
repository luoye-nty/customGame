package cn.luoye.game.MonopolyGame.roomInfo.controller;


import cn.luoye.game.MonopolyGame.roomInfo.entity.RoomInfo;
import cn.luoye.game.MonopolyGame.roomInfo.service.IRoomInfoService;
import cn.luoye.game.MonopolyGame.userInfo.entity.UserInfo;
import cn.luoye.game.MonopolyGame.userInfo.service.IUserInfoService;
import cn.luoye.game.common.config.CodeMessage;
import cn.luoye.game.common.config.GameResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author nitianye
 */
@RestController
@RequestMapping("/roomInfo")
public class RoomInfoController {
    @Autowired
    IRoomInfoService roomInfoService;

    @Autowired
    IUserInfoService userInfoService;

    @PostMapping("query_all_room")
    public GameResponse queryAllRoom(){
        GameResponse gameResponse = new GameResponse();
        return gameResponse.code(CodeMessage.SUCCESS_CODE).message(CodeMessage.SUCCESS_MESSAGE).data(roomInfoService.queryAllRoom());
    }

    @PostMapping("query_free_room")
    public GameResponse queryFreeRoom(){
        GameResponse gameResponse = new GameResponse();
        return gameResponse.code(CodeMessage.SUCCESS_CODE).message(CodeMessage.SUCCESS_MESSAGE).data(roomInfoService.queryFreeRoom());
    }

    @PostMapping("query_room/by_id")
    public GameResponse queryRoomById(@RequestBody JSONObject param){
        GameResponse gameResponse = new GameResponse();
        if(param.containsKey("roomId")){
           return gameResponse.code(CodeMessage.ERROR_CODE).message("参数错误");
        }
        Integer roomId = (Integer) param.get("roomId");
        return gameResponse.code(CodeMessage.SUCCESS_CODE).message(CodeMessage.SUCCESS_MESSAGE).data(roomInfoService.queryRoomById(roomId));
    }

    @PostMapping("join_room")
    public GameResponse joinRoom(@RequestBody JSONObject param,ServletRequest servletRequest){
        GameResponse gameResponse = new GameResponse();
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");
        UserInfo user = userInfoService.queryByUsername(token.split(" ")[0]);
        Integer playerId = user.getId();
        if(!param.containsKey("roomId")){
            return gameResponse.code(CodeMessage.ERROR_CODE).message("参数错误");
        }
        Integer roomId = (Integer) param.get("roomId");
        return roomInfoService.joinRoom(roomId,playerId);
    }

}
