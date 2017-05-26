package com.jaydenxiao.common.basebean;

/**
 * Created by yuanjunliang on 2017/5/4.
 * description：公共数据格式
 */

public class Result<T> {
    public int Code;        //返回码
    public String Message;  //错误消息
    public int BizCode;     //业务异常码
    public T Results;       //结果

}
