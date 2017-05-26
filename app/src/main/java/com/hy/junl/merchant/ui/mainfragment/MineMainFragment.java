package com.hy.junl.merchant.ui.mainfragment;

import com.hy.junl.merchant.R;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;

import butterknife.Bind;

/**
 * Created by yuanjunliang on 2017/4/28.
 * description：
 */

public class MineMainFragment extends BaseFragment {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;


    @Override
    protected int getLayoutResource() {
        return R.layout.frag_main_mine;

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        ntb.setTitleText("我的");
        ntb.setTvLeftVisiable(false);
    }
}
