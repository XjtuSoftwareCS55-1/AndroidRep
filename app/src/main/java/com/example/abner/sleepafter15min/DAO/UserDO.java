package com.example.abner.sleepafter15min.DAO;

import java.io.Serializable;

/**
 * Created by abner on 2018/6/3.
 */

public class UserDO implements Serializable {
    private String userId;
    private String nickname;
    private String sex;
    private String Token;

    public UserDO(String userId) {
        setUserId( userId );
    }
    public UserDO(String userId, String nickname){
        setUserId( userId );
        setNickname( nickname );
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSex() {
        return sex;
    }

    public String getToken() {
        return Token;
    }

    public String getUserId() {
        return userId;
    }
}
