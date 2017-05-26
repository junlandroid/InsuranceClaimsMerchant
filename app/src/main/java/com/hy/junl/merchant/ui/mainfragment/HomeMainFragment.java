package com.hy.junl.merchant.ui.mainfragment;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aspsine.irecyclerview.IRecyclerView;
import com.hy.junl.merchant.R;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;

import butterknife.Bind;


/**
 * Created by yuanjunliang on 2017/4/28.
 * description：首页
 */

public class HomeMainFragment extends BaseFragment {

    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;


    @Override
    protected int getLayoutResource() {
        return R.layout.frag_main_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        ntb.setTitleText("首页");
        ntb.setTvLeftVisiable(false);
    }
}
