package com.samet.realmexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    EditText userRealName,userName,userPassword;
    Button registerButton;
    RadioGroup genderRadioGroup;
    ListView listView;
    Button updateButton;
    int position=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
        RealmInitialize();
        InsertToRealm();
        ShowRealm();
        PositionFind();


    }


    public void Initialize(){

        listView=(ListView) findViewById(R.id.listView);
        userRealName=(EditText) findViewById(R.id.userRealNameEditText);
        userName=(EditText)findViewById(R.id.userNameEditText);
        userPassword=(EditText)findViewById(R.id.passwordEditText);
        registerButton=(Button) findViewById(R.id.registerButton);
        genderRadioGroup=(RadioGroup) findViewById(R.id.GenderRadioGroup);
        updateButton =(Button) findViewById(R.id.updateButton);


    }

    public void RealmInitialize(){
        realm =Realm.getDefaultInstance();
    }

    public void InsertToRealm()
    {

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameText= userName.getText().toString();
                String userRealNameText= userRealName.getText().toString();
                String passwordText= userPassword.getText().toString();
                int id=genderRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                String gender= radioButton.getText().toString();

                WriteOnDb(userRealNameText,userNameText,passwordText,gender);
                userName.setText("");
                userRealName.setText("");
                userPassword.setText("");
                ShowRealm();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealmResults<Users> list= realm.where(Users.class).findAll();
                Users userToUpdate = list.get(position);
                String name=userName.getText().toString();
                String realname=userRealName.getText().toString();
                String password =userPassword.getText().toString();
                int id=genderRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                String gender= radioButton.getText().toString();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        userToUpdate.setUserName(name);
                        userToUpdate.setUserRealName(realname);
                        userToUpdate.setUserPassword(password);
                        userToUpdate.setUserGender(gender);
                    }
                });

                ShowRealm();

            }
        });

    }


    public void WriteOnDb(String name,String userName, String password,String gender){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Users user= realm.createObject(Users.class);

                user.setUserName(userName);
                user.setUserPassword(password);
                user.setUserRealName(name);
                user.setUserGender(gender);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();

                ShowRealm();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(getApplicationContext(), "Failed Register", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void ShowRealm(){

        RealmResults<Users> user = realm.where(Users.class).findAll();
        if(user.size()>0){
            adapter adapter = new adapter(user,getApplicationContext());
            listView.setAdapter(adapter);
        }


    }

    public void PositionFind(){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            RealmResults<Users> list = realm.where(Users.class).findAll();

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                GiveAlert(i);
                userName.setText(list.get(i).getUserName());
                userRealName.setText(list.get(i).getUserRealName());
                userPassword.setText(list.get(i).getUserPassword());
                userName.setText(list.get(i).getUserName());
                if (list.get(i).getUserGender().equals("Male")){

                    ((RadioButton)genderRadioGroup.getChildAt(0)).setChecked(true);

                }else {
                    ((RadioButton)genderRadioGroup.getChildAt(1)).setChecked(true);
                }

                position=i;


            }
        });

    }

    public void Delete(int position){

        RealmResults<Users> userToDelete = realm.where(Users.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Users userDelete = userToDelete.get(position);
                if (userDelete != null) {
                    userDelete.deleteFromRealm();

                }
                ShowRealm();
            }
        });

    }

    public void GiveAlert(int position){
        LayoutInflater infilater = getLayoutInflater();
        View view = infilater.inflate(R.layout.alert_layout,null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Button yesButton=(Button) view.findViewById(R.id.yesButton);
        Button noButton=(Button) view.findViewById(R.id.noButton);

        alert.setView(view);
        alert.setCancelable(false);

        AlertDialog dialog=alert.create();
        dialog.show();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete(position);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


    }


}