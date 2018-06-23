package com.example.abner.sleepafter15min;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.abner.sleepafter15min.DAO.UserDO;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private UserDO userDO = null;
    //token  登录返回的凭证
//    private Token token;
    TextView textView;
    BottomNavigationBar mBottomNavigationBar;
    FloatingActionButton fab;
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
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(true)
                .setText("10")
                .setBackgroundColorResource(R.color.yellow)
                .setBorderWidth(0);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor( R.color.white );
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.tab_one).setActiveColorResource(R.color.green).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.tab_two).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.tab_three).setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.icon_four, R.string.tab_four))//依次添加item,分别icon和名称
                .setFirstSelectedPosition(0)//设置默认选择item
                .initialise();//初始化

        mBottomNavigationBar.setTabSelectedListener(this);

        fab = (FloatingActionButton) findViewById( R.id.fab );
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

    @Override
    public void onTabSelected(int position) {
       String str= "init";
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                str = "First Fragment";
                textView.setText( str );
                break;
            case 1:
                    str = "2 Fragment";
                textView.setText( str );
                break;
            case 2:
                    str = "3 Fragment";
                textView.setText( str );
                break;
            case 3:

                if(null == userDO){
                    Intent intent = new Intent( MainActivity.this,LoginActivity.class );
//                startActivity( intent );
                    startActivityForResult(intent,0);
                }else {
                    MyHomeFragment home = new MyHomeFragment();
                    transaction.replace(R.id.ll_content, home);
                    str = "4 Fragment";
                    textView.setText( str );
                }

                break;
            default:
                    str = "defalut Fragment";
                textView.setText( str );
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    public static class flunt extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.page2, container, false);
        }

    }
}
