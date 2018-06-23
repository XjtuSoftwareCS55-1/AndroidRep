package com.example.abner.sleepafter15min.Util;

/**
 * Created by 17917 on 2018/6/23.
 **/

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

public class jiemian1 extends FragmentActivity implements OnClickListener {
    // 底部菜单4个Linearlayout
    private LinearLayout mshur;
    private LinearLayout mlunt;
    private LinearLayout mbuzhid;

    // 底部菜单4个菜单标题
    private TextView shur;
    private TextView lunt;
    private TextView buzhid;

    // 4个Fragment
    private Fragment fshur;
    private Fragment flunt;
    private Fragment fbuzhid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
        // 初始化并设置当前Fragment
        initFragment(0);

    }

    private void initFragment(int index) {
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (fshur == null) {
                    fshur = new fshur();
                    transaction.add(R.id.fl_content, fshur);
                } else {
                    transaction.show(fshur);
                }
                break;
            case 1:
                if (flunt == null) {
                    flunt = new flunt();
                    transaction.add(R.id.fl_content, flunt);
                } else {
                    transaction.show(flunt);
                }

                break;
            case 2:
                if (fbuzhid == null) {
                    fbuzhid = new fbuzhid();
                    transaction.add(R.id.fl_content, fbuzhid);
                } else {
                    transaction.show(fbuzhid);
                }

                break;

            default:
                break;
        }

        // 提交事务
        transaction.commit();

    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (fshur != null) {
            transaction.hide(fshur);
        }
        if (flunt != null) {
            transaction.hide(flunt);
        }
        if (fbuzhid != null) {
            transaction.hide(fbuzhid);
        }

    }

    private void initEvent() {
        // 设置按钮监听
        mshur.setOnClickListener(this);
        mlunt.setOnClickListener(this);
        mbuzhid.setOnClickListener(this);

    }

    private void initView() {

        // 底部菜单4个Linearlayout
        this.mshur = (LinearLayout) findViewById(R.id.mshur);
        this.mlunt = (LinearLayout) findViewById(R.id.mlunt);
        this.mbuzhid = (LinearLayout) findViewById(R.id.mbuzhid);
        
        // 底部菜单4个菜单标题
        this.shur = (TextView) findViewById(R.id.shur);
        this.lunt= (TextView) findViewById(R.id.lunt);
        this.buzhid = (TextView) findViewById(R.id.bzhid);

    }

    @Override
    public void onClick(View v) {

        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.mshur:
                shur.setTextColor(0xff1B940A);
                initFragment(0);
                break;
            case R.id.mlunt:
                lunt.setTextColor(0xff1B940A);
                initFragment(1);
                break;
            case R.id.mbuzhid:
                buzhid.setTextColor(0xff1B940A);
                initFragment(2);
                break;
            default:
                break;
        }

    }

    private void restartBotton() {

        // TextView置为白色
        shur.setTextColor(0xffffffff);
        lunt.setTextColor(0xffffffff);
        buzhid.setTextColor(0xffffffff);

    }

}