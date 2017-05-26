package com.hy.junl.merchant.ui.presenter;


import com.hy.junl.merchant.bean.LoginResultBean;
import com.hy.junl.merchant.ui.contract.LoginContract;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.List;

/**
 * Created by yuanjunliang on 2017/4/28.
 * description：
 */

public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void LoginRequest(String loginName, String password, String Fingerprint) {
        mRxManage.add(mModel.getLoginData(loginName, password, Fingerprint)
                .subscribe(new RxSubscriber<List<LoginResultBean>>(mContext, true) {

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    protected void _onNext(List<LoginResultBean> infoResultBeen) {
                        mView.returnLoginData(infoResultBeen);

                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showErrorTip(message);
                    }
                }));
    }
}
