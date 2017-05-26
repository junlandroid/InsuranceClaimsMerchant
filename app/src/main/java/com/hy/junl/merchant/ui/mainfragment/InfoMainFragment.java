package com.hy.junl.merchant.ui.mainfragment;


import com.aspsine.irecyclerview.IRecyclerView;
import com.hy.junl.merchant.R;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import butterknife.Bind;


/**
 * Created by yuanjunliang on 2017/4/28.
 * description：
 */

public class InfoMainFragment extends BaseFragment {

    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_main_info;
    }

    @Override
    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        ntb.setTitleText("资讯");
        ntb.setTvLeftVisiable(false);
    }
}
