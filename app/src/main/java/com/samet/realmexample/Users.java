package com.samet.realmexample;


import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Users extends RealmObject {

    private String userName;
    private String userSurName;
    private Integer userSalary;
    private Integer userAge;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public void setUserSalary(Integer userSalary) {
        this.userSalary = userSalary;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public Integer getUserSalary() {
        return userSalary;
    }

    public Integer getUserAge() {
        return userAge;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", userSurName='" + userSurName + '\'' +
                ", userSalary=" + userSalary +
                ", userAge=" + userAge +
                '}';
    }
}
