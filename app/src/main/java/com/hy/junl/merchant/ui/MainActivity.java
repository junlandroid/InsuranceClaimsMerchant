package com.hy.junl.merchant.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hy.junl.merchant.R;
import com.hy.junl.merchant.app.AppConstant;
import com.hy.junl.merchant.bean.TabEntity;
import com.hy.junl.merchant.ui.mainfragment.DiscMainFragment;
import com.hy.junl.merchant.ui.mainfragment.HomeMainFragment;
import com.hy.junl.merchant.ui.mainfragment.InfoMainFragment;
import com.hy.junl.merchant.ui.mainfragment.MineMainFragment;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.AppConfig;

import java.util.ArrayList;

import butterknife.Bind;
import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.UpdateKey;

/**
 * ===================================
 *          和董传留别
 *                      苏轼【宋】
 * 粗缯大布裹天涯，腹有诗书气自华。
 * 厌伴老儒烹瓠叶，强随举子踏槐花。
 * 囊空不办寻春马，眼乱行看择婿车。
 * 得意犹堪夸世俗，诏黄新湿字如鸦。
 * ===================================
 * @date 2017-5-24
 * @author Junl
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页","资讯","发现","我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private HomeMainFragment homeMainFragment;
    private InfoMainFragment infoMainFragment;
    private DiscMainFragment discMainFragment;
    private MineMainFragment mineMainFragment;

    /**
     * 入口
     * @param context
     */
    public static void toMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        /**
         *  此处填上在http://fir.im/注册账号后获得的API_TOKEN以及APP的应用ID
         * 是国内首家为移动开发者提供 App 免费托管分发服务的平台，为移动开发者提供极速测试发布、崩溃收集分析、用户反馈收集等一系列开发测试效率工具服务，能够让开发者更专注于产品开发与优化。
         * API_FIRE_TOKEN、APP_FIRE_ID需要在http://fir.im平台上传APP之后生成的，需手动写进去
         */
        UpdateKey.API_TOKEN = AppConfig.API_FIRE_TOKEN;
        UpdateKey.APP_ID = AppConfig.APP_FIRE_ID;
        //如果你想通过Dialog来进行下载，可以如下设置
//        UpdateKey.DialogOrNotification=UpdateKey.WITH_DIALOG;
        /**
         * //代码更新处理
         * init(this) ---> UpdateFunGO(Context context) --->HandleUpdateResult(Context context)--->showNoticeDialog(Context context)--->
         */
        UpdateFunGO.init(this);
        //初始化菜单
        initTab();
    }



    @Override
    public void onCreate(Bundle savedInstanceState)  {
        //切换daynight模式要立即变色的页面
//        ChangeModeController.getInstance().init(this, R.attr.class);
        super.onCreate(savedInstanceState);
        intiFragment(savedInstanceState);
    }



    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    private void intiFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            homeMainFragment = (HomeMainFragment) getSupportFragmentManager().findFragmentByTag("homeMainFragment");
            infoMainFragment = (InfoMainFragment) getSupportFragmentManager().findFragmentByTag("infoMainFragment");
            discMainFragment = (DiscMainFragment) getSupportFragmentManager().findFragmentByTag("discMainFragment");
            mineMainFragment = (MineMainFragment) getSupportFragmentManager().findFragmentByTag("mineMainFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            homeMainFragment = new HomeMainFragment();
            infoMainFragment = new InfoMainFragment();
            discMainFragment = new DiscMainFragment();
            mineMainFragment = new MineMainFragment();

            transaction.add(R.id.fl_body, homeMainFragment, "homeMainFragment");
            transaction.add(R.id.fl_body, infoMainFragment, "infoMainFragment");
            transaction.add(R.id.fl_body, discMainFragment, "discMainFragment");
            transaction.add(R.id.fl_body, mineMainFragment, "mineMainFragment");
        }

        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transation = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0://首页
                transation.show(homeMainFragment);
                transation.hide(infoMainFragment);
                transation.hide(discMainFragment);
                transation.hide(mineMainFragment);
                transation.commitAllowingStateLoss();
                break;
            case 1://资讯
                transation.show(infoMainFragment);
                transation.hide(homeMainFragment);
                transation.hide(discMainFragment);
                transation.hide(mineMainFragment);
                transation.commitAllowingStateLoss();
                break;
            case 2://发现
                transation.show(discMainFragment);
                transation.hide(infoMainFragment);
                transation.hide(homeMainFragment);
                transation.hide(mineMainFragment);
                transation.commitAllowingStateLoss();
                break;
            case 3://我的
                transation.show(mineMainFragment);
                transation.hide(infoMainFragment);
                transation.hide(discMainFragment);
                transation.hide(homeMainFragment);
                transation.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    /**
     * 监听返回 参考：http://blog.csdn.net/dacainiao007/article/details/17352367
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //崩溃前保存位置
        if (tabLayout != null) {
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UpdateFunGO.onStop(this);
    }

}
