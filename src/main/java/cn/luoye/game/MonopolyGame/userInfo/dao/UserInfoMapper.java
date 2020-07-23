package cn.luoye.game.MonopolyGame.userInfo.dao;

import cn.luoye.game.MonopolyGame.userInfo.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nitianye
 * @since 2020-07-23
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> queryAllUser();

    /**
     * 查询用户
     * @param username
     * @return
     */
    UserInfo queryByUsername(@Param("username") String username);

    /**
     * 注册用户
     * @param userInfo
     */
    void registerUser(@Param("param") UserInfo userInfo);

}
