package com.hy.junl.merchant.app;

import com.jaydenxiao.common.BuildConfig;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.squareup.leakcanary.LeakCanary;


/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 版    本：1.0
 * 描    述：
 * <p>
 * 创建日期：2017/5/26
 * 开心一刻：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */

public class AppApplication extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger
        LogUtils.logInit(BuildConfig.LOG_DEBUG);
        //内存泄漏检测
        LeakCanary.install(this);
    }

}
