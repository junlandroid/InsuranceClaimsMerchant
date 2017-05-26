package com.hy.junl.merchant.ui.base;

import com.hy.junl.merchant.app.AppConstant;
import com.hy.junl.merchant.bean.CommonResult;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.exception.ExceptionEngine;
import com.jaydenxiao.common.exception.ServerException;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by yuanjunliang on 2017/5/4.
 * description：主要作用：进行异常处理，反馈给用户。
 * 为什么写一个接口又继承BaseModel:
 * 为了解决异常Exception.map(new ServerResultFunc<LoginResultBean>())  onErrorResumeNext(new HttpResultFunc<List<LoginResultBean>>())
 * 做的适配，原因：BaseModel在lib包中，而自定义的bean在App中，无法适配。如将ServerResultFunc类和HttpResultFunc放在BaseModel中会报错，所以重新写个接口做适配
 */

public interface JunlBaseModel extends BaseModel {

    //HTTP请求请求成功，
    class ServerResultFunc<T> implements Func1<CommonResult<T>, List<T>> {
        @Override
        public List<T> call(CommonResult<T> httpResult) {
            if (httpResult.Code != 0) {
                //code != 0 ，服务器返回异常的错误码，抛出异常
                throw new ServerException(httpResult.Code, httpResult.Message);
            }
            //code = 0 , 服务器返回正常code = 0
            AppConstant.CODE = httpResult.getCode();
            return httpResult.Results;//返回服务器返回的所有数据
        }
    }
////HTTP请求请求成功，
//class ServerResultFunc<T> implements Func1<CommonBackBean<T>, T implements CommonBackBean> {
//    @Override
//    public T call(CommonBackBean<T> httpResult) {
//        if (httpResult.Code != 0) {
//            //code != 0 ，服务器返回异常的错误码，抛出异常
//            throw new ServerException(httpResult.Code, httpResult.Message);
//        }
//        //code = 0 , 服务器返回正常code = 0
//        return (T) httpResult;//返回服务器返回的所有数据
//    }
//}

    //HTTP请求失败
    class HttpResultFunc<T> implements Func1<Throwable, Observable<T>> {
        @Override
        public Observable<T> call(Throwable throwable) {
            return Observable.error(ExceptionEngine.handleException(throwable));
        }
    }

}
