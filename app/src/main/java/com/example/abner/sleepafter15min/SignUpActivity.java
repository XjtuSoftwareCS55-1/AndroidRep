package com.example.abner.sleepafter15min;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abner.sleepafter15min.DAO.UserDO;
import com.example.abner.sleepafter15min.DAO.WebServer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by abner on 2018/6/4.
 */

public class SignUpActivity extends AppCompatActivity {
    private Button btn;
    private EditText userIdInput;
    private EditText passwdInput;
    private EditText nicknameInput;
    private String url;
    private Intent intent;
    private Bundle bundle;
    private UserDO userDO = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sign_up );
        btn = (Button)findViewById( R.id.SignUpButton );
        userIdInput = (EditText) findViewById( R.id.userIdInput1 );
        passwdInput = (EditText) findViewById( R.id.passwdInput1 );
        nicknameInput = (EditText) findViewById( R.id.nicknameInput1 );
        initData();
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = userIdInput.getText().toString();
                String passwd = passwdInput.getText().toString();
                String nickname = passwdInput.getText().toString();

                if("".equals( userId ) ){
                    Toast.makeText( SignUpActivity.this,"Id不能为空",Toast.LENGTH_LONG ).show();
                    return ;
                } else if("".equals( passwd )){
                    Toast.makeText( SignUpActivity.this,"密码不能为空",Toast.LENGTH_LONG ).show();
                    return ;
                } else if("".equals( nickname )){
                    Toast.makeText( SignUpActivity.this,"用户名不能为空",Toast.LENGTH_LONG ).show();
                    return ;
                }
                new WebSignUpTask().execute( WebServer.getSignUp(),userId, passwd, nickname );
                setResult( RESULT_OK,  intent);
            }
        } );

    }
    private void initData(){
        intent=this.getIntent();
        bundle=intent.getExtras();
    }

    class WebSignUpTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String path = objects[0].toString();
            String userId = objects[1].toString();
            String password = objects[2].toString();
            String nickname = objects[2].toString();
            userDO = new UserDO( userId, nickname );
            try{
                URL url = new URL( path +"?userId=" + userId + "&password="+password +"&nickname="+nickname);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                //设置网络超时时间
                conn.setConnectTimeout(5000);

                int code = conn.getResponseCode();
                if (code == 200) {
                    //用io流与web后台进行数据交互
                    InputStream is = conn.getInputStream();
                    //字节流转字符流
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    //读出每一行的数据
                    String s = br.readLine();
                    //返回读出的每一行的数据
                    return s;
                } else {
                    return "Warning";
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //获取Android studio与web后台数据交互获得的值
            String s= (String) o;
            if (null != o){
                Toast.makeText( SignUpActivity.this, s,Toast.LENGTH_LONG ).show();
                // 给bnt1添加点击响应事件
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                //启动
                startActivity(intent);
            }else {
//                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                bundle=new Bundle();
                bundle.putSerializable("user",userDO);
                intent.putExtras( bundle );
                //启动
//                startActivity(intent);
                finish();
            }

        }
    }
}
