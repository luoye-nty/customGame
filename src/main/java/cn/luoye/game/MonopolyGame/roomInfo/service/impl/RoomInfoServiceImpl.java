package cn.luoye.game.MonopolyGame.roomInfo.service.impl;

import cn.luoye.game.MonopolyGame.roomInfo.entity.RoomInfo;
import cn.luoye.game.MonopolyGame.roomInfo.dao.RoomInfoMapper;
import cn.luoye.game.MonopolyGame.roomInfo.service.IRoomInfoService;
import cn.luoye.game.common.config.CodeMessage;
import cn.luoye.game.common.config.GameResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nitianye
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements IRoomInfoService {

    /**
     * 查询所有房间
     * @return
     */
    @Override
    public List<RoomInfo> queryAllRoom() {
        return this.baseMapper.queryAllRoom();
    }

    /**
     * 查询空闲房间
     * @return
     */
    @Override
    public List<RoomInfo> queryFreeRoom() {
        return this.baseMapper.queryFreeRoom();
    }

    /**
     * 通过id查询房间
     * @param roomId
     * @return
     */
    @Override
    public RoomInfo queryRoomById(Integer roomId) {
        return this.baseMapper.queryRoomById(roomId);
    }

    /**
     * 加入房间
     * @param roomId
     * @param playerId
     * @return
     */
    @Override
    public GameResponse joinRoom(Integer roomId,Integer playerId) {
        GameResponse gameResponse = new GameResponse();
        RoomInfo roomInfo = queryRoomById(roomId);
        if(roomInfo.getPlayerTotal().equals(5)){
            return gameResponse.code(CodeMessage.ERROR_CODE).message("房间已满");
        }
        switch (roomInfo.getPlayerTotal()){
            case 0 :
                roomInfo.setPlayerOne(playerId);
                //创建游戏



                break;
            case 1 :
                roomInfo.setPlayerTwo(playerId);
                break;
            case 2 :
                roomInfo.setPlayerThree(playerId);
                break;
            case 3 :
                roomInfo.setPlayerFour(playerId);
                break;
            case 4 :
                roomInfo.setPlayerFive(playerId);
        }
        roomInfo.setPlayerTotal(roomInfo.getPlayerTotal()+1);
        try {
            this.baseMapper.joinRoom(roomInfo);
            return gameResponse.code(CodeMessage.SUCCESS_CODE).message("加入房间成功");
        }catch (Exception e){
            return gameResponse.code(CodeMessage.ERROR_CODE).message("加入房间失败");
        }
    }
}
