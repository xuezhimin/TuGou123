package com.umeng.soexample.MVPData.presenter;

import com.google.gson.reflect.TypeToken;
import com.umeng.soexample.MVPData.model.Model;
import com.umeng.soexample.MVPData.view.IView;
import com.umeng.soexample.bean.Product;
import com.umeng.soexample.net.ICallBack;

import java.lang.reflect.Type;

public class Presenter {

    //view
    private IView iView;
    //model
    private Model model;

    public void attach(IView iView) {
        this.iView = iView;
        model = new Model();
    }

    public void getProduct() {
        Type type = new TypeToken<Product>() {
        }.getType();
        String url = "http://www.wanandroid.com/tools/mockapi/6523/restaurants_offset_0_limit_4";
        model.getData(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                Product product = (Product) obj;
                if (product != null) {
                    iView.getProduct(product.getData());
                }
            }

            @Override
            public void failed(Exception e) {
                iView.getFailed(e);
            }
        }, type);
    }


}
