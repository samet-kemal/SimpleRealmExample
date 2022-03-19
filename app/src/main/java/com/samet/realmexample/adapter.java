package com.samet.realmexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adapter extends BaseAdapter {

    List<Users> list;
    Context context;

    public adapter(List<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.list_view_layout,viewGroup,false);
        TextView userName= view.findViewById(R.id.UserNameTextView);
        TextView userRealName = view.findViewById(R.id.UserRealNameTextView);
        TextView password=view.findViewById(R.id.UserPasswordTextView);
        TextView userGender = view.findViewById(R.id.UserGenderTextView);

        userName.setText(list.get(i).getUserName());
        userRealName.setText(list.get(i).getUserRealName());
        password.setText(list.get(i).getUserPassword());
        userGender.setText(list.get(i).getUserGender());
        return view;
    }
}
