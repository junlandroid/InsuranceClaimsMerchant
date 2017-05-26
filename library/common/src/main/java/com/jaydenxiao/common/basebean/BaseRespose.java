package com.jaydenxiao.common.basebean;

import java.io.Serializable;
import java.util.List;


/**
 * des:封装服务器返回数据
 * Created by xsf
 * on 2016.09.9:47
 */
public class BaseRespose <T> implements Serializable {
    public int Code;

    public String Message;

    public long ServiceTime;

    public T Results;

    public boolean success() {
        return 0 == Code;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                ", ServiceTime=" + ServiceTime +
                ", Results=" + Results +
                '}';
    }
}
