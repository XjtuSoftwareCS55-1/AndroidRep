package com.example.abner.sleepafter15min;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class LoginActivity extends AppCompatActivity {
    private Button btn;
    private EditText userIdInput;
    private EditText passwdInput;
    private String url;
    private Intent intent;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login );
        initData();
//        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
//        setSupportActionBar( toolbar );
        url = WebServer.getSite();
        btn = (Button) findViewById( R.id.SignInButton );
        userIdInput = findViewById( R.id.userIdInput );
        passwdInput = findViewById( R.id.passwdInput );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = userIdInput.getText().toString();
                String passwd = passwdInput.getText().toString();
                new WebTask().execute( url, userId, passwd);
                setResult(RESULT_OK,intent);
//                finish();
            }
        } );

    }

    private void initData(){
        intent=this.getIntent();
        bundle=intent.getExtras();
    }


    class WebTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            String path = objects[0].toString();
            String userId = objects[1].toString();
            String password = objects[2].toString();
            try{
                URL url = new URL( path +"?UserId=" + userId + "&password="+password );
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
                Toast.makeText( LoginActivity.this, s,Toast.LENGTH_LONG ).show();
                // 给bnt1添加点击响应事件
                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                //启动
                startActivity(intent);
            }else {
//                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                bundle=new Bundle();
                bundle.putSerializable("user",new UserDO("1223","Abenr"));
                intent.putExtras( bundle );
                //启动
//                startActivity(intent);
                finish();
            }

        }
    }

//    class WebTask extends AsyncTask{
//        /**
//         * 在 doInBackground(Params...)之前被调用，在ui线程执行.
//         * 进行准备工作
//         */
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        /**
//         * 处理后台执行的任务，在后台线程执行
//         * @param params 因为需要的是地址，所以是String类型
//         */
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            return null;
//        }
//
//        /**
//         * 在调用publishProgress之后被调用，在ui线程执行(non-Javadoc)
//         * 进行进度更新
//         * @see android.os.AsyncTask#onProgressUpdate(java.lang.Object[])
//         */
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate( values );
//        }
//
//        /**
//         * 后台任务执行完之后被调用，在ui线程执行
//         * @param result 此类型为doInBackground()返回的参数类型
//         */
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute( bitmap );
//        }
//    }


}
