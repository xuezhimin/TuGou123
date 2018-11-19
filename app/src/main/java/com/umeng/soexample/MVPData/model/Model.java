package com.umeng.soexample.MVPData.model;

import com.umeng.soexample.net.HttpUtils;
import com.umeng.soexample.net.ICallBack;

import java.lang.reflect.Type;

public class Model {

    public void getData(String url, ICallBack callBack, Type type) {
        HttpUtils.getInstance().get(url, callBack, type);
    }

}
