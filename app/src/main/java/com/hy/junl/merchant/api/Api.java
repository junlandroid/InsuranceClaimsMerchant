package com.hy.junl.merchant.api;

import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hy.junl.merchant.app.AppApplication;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 版    本：1.0
 * 创建日期：2017/5/26
 * 描    述：
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */

public class Api {

    /**
     * 正式服务器地址
     */
//    public static final String COMMON_SERVICE_URL = "http://116.211.87.73:1003/";
    /**
     * 外网测试
     */
//    public static final String COMMON_SERVICE_URL = "http://116.211.87.73:2003/";
    /**
     * 内网测试
     */
    public static final String COMMON_SERVICE_URL = "http://192.168.1.2:2003/";

    /**
     * 存储ApiService实例的标签
     */
    public static final int FORMAL_SERVICE = 1;

    /**
     * 多少种host类型  正式服务器ApiService、内网测试服务器ApiService、外网测试服务器ApiService
     */
    public static final int TYPE_COUNT = 3;


    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;
    public Retrofit retrofit;
    public ApiService movieService;

    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(TYPE_COUNT);

    /*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    //构造方法私有
    private Api(final String token) {
        //开启log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();//日志拦截器
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(AppApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);//缓存大小100MB
        /**
         * 添加请求头部信息拦截器
         * 具体解析参考:http://blog.csdn.net/qq122627018/article/details/68957782
         */
        Interceptor headIntercepter = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
//                Request request = chain.request().newBuilder()
//                        .addHeader("HY-TPA-CLIENT", AppApplication.getHYTPAClient())
//                        .addHeader("CONTENT-TYPE", "application/json")
//                        .addHeader("HY-TPA-Token", token)
//                        .addHeader("ACCEPT", "application/json")
//                        .build();
//                LogUtils.loge(AppApplication.getHYTPAClient());
//                return chain.proceed(request);
                return null;
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(logInterceptor)
                .addInterceptor(headIntercepter)
                .cache(cache)
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        //Retrofit本身会抛出HttpException，Gson解析会抛出解析异常，
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(COMMON_SERVICE_URL)//通常情况下就是域名
                .build();
        movieService = retrofit.create(ApiService.class);

    }


    /**
     * @param token 令牌
     * @return ApiService实例
     */
    public static ApiService getDefault(String token) {
//        Api retrofitManager = sRetrofitManager.get(FORMAL_SERVICE);
//        if (retrofitManager == null) {
//            retrofitManager = new Api(token);
//            sRetrofitManager.append(FORMAL_SERVICE, retrofitManager);
//        }
        return new Api(token).movieService;//每次需要new一个对象  需优化
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            if (!NetWorkUtils.isNetConnected(AppApplication.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl)? CacheControl.FORCE_NETWORK:CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(AppApplication.getAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置

                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}

