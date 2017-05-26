package com.hy.junl.merchant.ui.model;

import com.hy.junl.merchant.api.Api;
import com.hy.junl.merchant.bean.LoginResultBean;
import com.hy.junl.merchant.ui.contract.LoginContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by yuanjunliang on 2017/4/28.
 * description：
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<List<LoginResultBean>> getLoginData(String loginName, String password, String Fingerprint) {
        return Api.getDefault("")
                .login(loginName, password, Fingerprint)
                .map(new ServerResultFunc<LoginResultBean>())
                .onErrorResumeNext(new HttpResultFunc<List<LoginResultBean>>())
                .compose(RxSchedulers.<List<LoginResultBean>>io_main());//线程调度器不能少，不然会出现RunMainThread异常
    }


//    @Override
//    public Observable<List<InfoResultBean>> getInfoData() {
//        String token = SPUtils.getSharedStringData(AppApplication.getAppContext(), AppConstant.INSTANCE.getTOKEN());
//        return Api.getDefault(token)
//                .getInfo()
//                .map(new ServerResultFunc<InfoResultBean>())
//                .onErrorResumeNext(new HttpResultFunc<List<InfoResultBean>>())
//                .compose(RxSchedulers.<List<InfoResultBean>>io_main());
//    }
}
