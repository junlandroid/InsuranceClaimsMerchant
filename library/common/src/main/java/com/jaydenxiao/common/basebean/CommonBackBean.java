//package com.jaydenxiao.common.basebean;
//
//import java.io.Serializable;
//import java.util.List;
//
///**
// * Created by yuanjunliang on 2017/4/25.
// * description：服务器返回通用的结果bean
// */
//
//public class CommonBackBean<T> implements Serializable {
//    public int Code;
//    public String Message;
//    public long ServiceTime;
//
//    public boolean success() {
//        return 0 == Code;
//    }
//
//    public List<T> Results;
//
//    public int getCode() {
//        return Code;
//    }
//
//    public void setCode(int code) {
//        Code = code;
//    }
//
//    public String getMessage() {
//        return Message;
//    }
//
//    public void setMessage(String message) {
//        Message = message;
//    }
//
//    public long getServiceTime() {
//        return ServiceTime;
//    }
//
//    public void setServiceTime(long serviceTime) {
//        ServiceTime = serviceTime;
//    }
//
//    public List<T> getResults() {
//        return Results;
//    }
//
//    public void setResults(List<T> results) {
//        Results = results;
//    }
//
//    @Override
//    public String toString() {
//        return "CommonBackBean{" +
//                "Code=" + Code +
//                ", Message='" + Message + '\'' +
//                ", ServiceTime=" + ServiceTime +
//                ", Results=" + Results +
//                '}';
//    }
//}
