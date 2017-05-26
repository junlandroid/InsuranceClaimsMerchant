package com.hy.junl.merchant.api;


import com.hy.junl.merchant.bean.CommonResult;
import com.hy.junl.merchant.bean.LoginResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 版    本：1.0
 * 创建日期：2017/5/26
 * 描    述：description：设置接口service   参考:http://blog.csdn.net/wzgiceman/article/details/51939574
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */


public interface ApiService {

//    //获取闪屏页
//    @POST("GetAppSplash")
//    Observable<CommonResult<SplashBean>> getSplash();
//
//    //获取应用密钥
//    @POST("GetSecret")
//    Observable<SecretBean> getSecret();
//
    //登录
    @FormUrlEncoded
    @POST("Login")
    Observable<CommonResult<LoginResultBean>> login(
            @Field("LoginName") String loginName,
            @Field("PassWord") String passWord,
            @Field("Fingerprint") String fingerprint
    );
//
//    //资讯列表
//    @POST("GetArticleCollection")
//    Observable<CommonResult<InfoResultBean>> getInfo(
//
//    );
//
//    /*
//    * 上传图片  返回文件路径
//    */
//    @FormUrlEncoded
//    @POST("UploadFile")
//    Observable<CommonResult<String>> upLoadFile(
//            @Field("ScanID") String ScanID,
//            @Field("ClaimFormId") String ClaimFormId,
//            @Field("Type") int Type,
//            @Field("FileStream") String FileStream
//    );


}

