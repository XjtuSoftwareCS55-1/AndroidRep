package com.example.abner.sleepafter15min;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abner.sleepafter15min.DAO.UserDO;

/**
 * Created by abner on 2018/6/23.
 */

public class MyHomeFragment extends Fragment {
    public UserDO userDO;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //引用创建好的xml布局
        View view = inflater.inflate(R.layout.myhome,container,false);
        textView1 = (TextView) getView().findViewById(R.id.theInfoName);
//        textView2 = (TextView) getView().findViewById(R.id.theHistory);
//        textView3 = (TextView) getView().findViewById(R.id.the_setting);
//        textView4 = (TextView) getView().findViewById(R.id.thesaved);
//        textView5 = (TextView) getView().findViewById(R.id.mystar);
        return view;
//        return super.onCreateView( inflater, container, savedInstanceState );
    }
    public void displayInfo(){
        textView1.setText( userDO.getNickname() );
    }
    public void setUserDO(UserDO userDO){
        this.userDO = userDO;
        displayInfo();
    }
}
