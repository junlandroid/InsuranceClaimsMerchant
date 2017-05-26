package com.hy.junl.merchant.bean;

import java.util.List;

/**
 * Created by yuanjunliang on 2017/4/25.
 * description：服务器返回通用的结果bean
 */

public class CommonResult<T> {
    /**
     * Results : []
     * Total : 0
     * Code : 8
     * Message : 大保单号有误!
     * ServiceTime : 1494487159
     */

    public int Total;
    public int Code;
    public String Message;
    public int ServiceTime;
    public List<T> Results;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(int ServiceTime) {
        this.ServiceTime = ServiceTime;
    }

    public List<?> getResults() {
        return Results;
    }

    public void setResults(List<T> Results) {
        this.Results = Results;
    }
}
