package com.example.abner.sleepafter15min;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.abner.sleepafter15min.DAO.UserDO;

public class MainActivity extends AppCompatActivity {
    private UserDO userDO = null;
    //token  登录返回的凭证
//    private Token token;
    TextView textView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(resultCode==RESULT_OK){//如果是返回的标识
            //获取数据
            Bundle bundle=data.getExtras();
           userDO = (UserDO) bundle.getSerializable( "user" );
            //保留之前的数据
            textView.setText( "Hello, "+ userDO.getNickname() );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        textView = (TextView)findViewById( R.id.hello );
        if(null != userDO){
            textView.setText( "Hello, "+ userDO.getNickname() );
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();

                Intent intent = new Intent( MainActivity.this,LoginActivity.class );
//                startActivity( intent );
                startActivityForResult(intent,0);
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
}
