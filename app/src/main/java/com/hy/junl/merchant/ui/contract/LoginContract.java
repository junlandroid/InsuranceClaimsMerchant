package com.hy.junl.merchant.ui.contract;


import com.hy.junl.merchant.bean.LoginResultBean;
import com.hy.junl.merchant.ui.base.JunlBaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.List;

import rx.Observable;


/**
 * Created by yuanjunliang on 2017/4/27.
 * description：
 */

public interface LoginContract {

    interface Model extends JunlBaseModel {
        Observable<List<LoginResultBean>> getLoginData(String loginName, String password, String Fingerprint);
    }

    interface View extends BaseView {
        void returnLoginData(List<LoginResultBean> result);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void LoginRequest(String loginName, String password, String Fingerprint);
    }
}
