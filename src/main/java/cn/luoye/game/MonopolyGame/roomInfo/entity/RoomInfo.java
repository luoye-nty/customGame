package cn.luoye.game.MonopolyGame.roomInfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author nitianye
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoomInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房间号
     */
    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    /**
     * 玩家一
     */
    private Integer playerOne;

    /**
     * 玩家二
     */
    private Integer playerTwo;

    /**
     * 玩家三
     */
    private Integer playerThree;

    /**
     * 玩家四
     */
    private Integer playerFour;

    /**
     * 玩家五
     */
    private Integer playerFive;

    /**
     * 房间状态
     */
    private Integer roomState;

    /**
     * 房间人数
     */
    private Integer playerTotal;


}
