package cn.luoye.game.MonopolyGame.roomInfo.service;

import cn.luoye.game.MonopolyGame.roomInfo.entity.RoomInfo;
import cn.luoye.game.common.config.GameResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nitianye
 */
public interface IRoomInfoService extends IService<RoomInfo> {

    List<RoomInfo> queryAllRoom();

    List<RoomInfo> queryFreeRoom();

    RoomInfo queryRoomById(Integer roomId);

    GameResponse joinRoom(Integer roomId,Integer playerId);
}
