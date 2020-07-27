package cn.luoye.game.MonopolyGame.roomInfo.dao;

import cn.luoye.game.MonopolyGame.roomInfo.entity.RoomInfo;
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
 * @since 2020-07-27
 */
@Mapper
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    List<RoomInfo> queryAllRoom();

    List<RoomInfo> queryFreeRoom();

    RoomInfo queryRoomById(@Param("roomId") Integer roomId);

    void joinRoom(@Param("param") RoomInfo roomInfo);

}
