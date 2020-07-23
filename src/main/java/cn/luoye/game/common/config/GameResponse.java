package cn.luoye.game.common.config;

import java.util.HashMap;

public class GameResponse extends HashMap<String,Object> {

    private static final long serialVersionUID = -2512473826296495158L;

    public GameResponse message(String message){
        this.put("message",message);
        return this;
    }

    public GameResponse code(Integer code){
        this.put("code",code);
        return this;
    }

    public GameResponse data(Object object){
        this.put("data",object);
        return this;
    }

    public GameResponse put(String key,Object value){
        super.put(key,value);
        return this;
    }
}
