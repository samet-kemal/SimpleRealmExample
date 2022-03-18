package com.samet.realmexample;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmInitialize();
        AddToTable();
        ShowDummyData();

    }

    public void RealmInitialize(){
        realm =Realm.getDefaultInstance();
    }

    public void AddToTable(){

        realm.beginTransaction();
        Users user= realm.createObject(Users.class);
        user.setUserName("Leonardo");
        user.setUserSurName("DaVinci");
        user.setUserSalary(50000);
        user.setUserAge(45);
        realm.commitTransaction();

    }

    public void ShowDummyData(){
        realm.beginTransaction();

        RealmResults<Users> result= realm.where(Users.class).findAll();

        for (Users user: result) {

            System.out.println("RESULT:"+user.toString());

        };



        realm.commitTransaction();
    }


}