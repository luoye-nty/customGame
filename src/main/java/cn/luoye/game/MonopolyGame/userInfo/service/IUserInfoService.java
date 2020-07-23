package cn.luoye.game.MonopolyGame.userInfo.service;

import cn.luoye.game.MonopolyGame.userInfo.entity.UserInfo;
import cn.luoye.game.common.config.GameResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nitianye
 */
public interface IUserInfoService extends IService<UserInfo> {
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> queryAllUser();

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    UserInfo queryByUsername(String username);

    /**
     * 注册用户
     * @param userInfo
     * @return
     */
    GameResponse registerUser(UserInfo userInfo);

    /**
     * 登陆
     * @param userInfo
     * @return
     */
    GameResponse loginUser(UserInfo userInfo);

}
