package com.samet.realmexample;


import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Users extends RealmObject {

    private String userName;
    private String userRealName;
    private String userGender;
    private String userPassword;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
