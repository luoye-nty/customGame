package cn.luoye.game.MonopolyGame.userInfo.controller;


import cn.luoye.game.MonopolyGame.userInfo.entity.UserInfo;
import cn.luoye.game.MonopolyGame.userInfo.service.IUserInfoService;
import cn.luoye.game.common.config.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nitianye
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;


    /**
     * 查询所有用户
     * @return
     */
    @PostMapping("query_list")
    public List<UserInfo> findAll(){
        return userInfoService.queryAllUser();
    }

    /**
     * 注册
     * @param param
     * @return
     */
    @PostMapping("register")
    public GameResponse registerUser(@RequestBody UserInfo param){
        return userInfoService.registerUser(param);
    }

    /**
     * 登陆
     * @param param
     * @return
     */
    @PostMapping("login")
    public GameResponse loginUser(@RequestBody UserInfo param){
        return userInfoService.loginUser(param);
    }

}
