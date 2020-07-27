package cn.luoye.game.MonopolyGame.userInfo.service.impl;

import cn.luoye.game.MonopolyGame.userInfo.entity.UserInfo;
import cn.luoye.game.MonopolyGame.userInfo.dao.UserInfoMapper;
import cn.luoye.game.MonopolyGame.userInfo.service.IUserInfoService;
import cn.luoye.game.common.config.CodeMessage;
import cn.luoye.game.common.config.GameResponse;
import cn.luoye.game.common.config.RedisConfig;
import cn.luoye.game.common.utils.MD5Util;
import cn.luoye.game.common.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author nitianye
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    RedisUtil redisUtil;

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  HH:mm:ss

    private static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserInfo> queryAllUser() {
        return this.baseMapper.queryAllUser();
    }

    /**
     * 查询用户
     * @param username
     * @return
     */
    @Override
    public UserInfo queryByUsername(String username) {
        return this.baseMapper.queryByUsername(username);
    }

    /**
     * 注册用户
     * @param userInfo
     * @return
     */
    @Override
    public GameResponse registerUser(UserInfo userInfo) {
        GameResponse gameResponse = new GameResponse();
        UserInfo user = queryByUsername(userInfo.getUsername());
        if(null!=user){
            gameResponse.message("用户名已存在,请重新输入用户名");
            return gameResponse.code(CodeMessage.ERROR_CODE);
        }
        try {
            String Md5 = MD5Util.encrypt(userInfo.getPassword());
            userInfo.setPlaintextPassword(userInfo.getPassword());
            userInfo.setPassword(Md5);
            userInfo.setBalance(new BigDecimal("0.00"));
            String createdate = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            userInfo.setCreatTime(LocalDateTime.parse(createdate,sdf));
            userInfo.setLastupdateTime(LocalDateTime.parse(createdate,sdf));

            this.baseMapper.registerUser(userInfo);

            gameResponse.code(CodeMessage.SUCCESS_CODE);
            return gameResponse.message("注册账号:"+userInfo.getUsername()+"成功");
        }catch (Exception e){
            gameResponse.message("注册失败，请联系管理员");
            return gameResponse.code(CodeMessage.ERROR_CODE);
        }
    }

    /**
     * 登陆
     * @param userInfo
     * @return
     */
    @Override
    public GameResponse loginUser(UserInfo userInfo) {
        GameResponse gameResponse = new GameResponse();
        String md5Pass=MD5Util.encrypt(userInfo.getPassword());
        UserInfo user = queryByUsername(userInfo.getUsername());
        if(null!=user){
            if(user.getPassword().equals(md5Pass)){
                gameResponse.code(CodeMessage.SUCCESS_CODE);
                gameResponse.message("登陆成功");
                gameResponse.data(user);
                String token = UUID.randomUUID().toString();
                token =userInfo.getUsername()+" "+token;
                redisUtil.set(userInfo.getUsername()+"token",token,36000);
                gameResponse.put("token",token);
            }else{
                gameResponse.code(CodeMessage.ERROR_CODE);
                gameResponse.message("用户名密码错误");
            }
        }else{
            gameResponse.code(CodeMessage.ERROR_CODE);
            gameResponse.message("用户不存在");
        }
        return gameResponse;
    }
}
