package cn.luoye.game.MonopolyGame.userInfo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author nitianye
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 明文密码
     */
    private String plaintextPassword;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 账号余额
     */
    private BigDecimal balance;

    /**
     * 创建日期
     */
    private LocalDateTime creatTime;

    /**
     * 最后修改日期
     */
    private LocalDateTime lastupdateTime;


}
