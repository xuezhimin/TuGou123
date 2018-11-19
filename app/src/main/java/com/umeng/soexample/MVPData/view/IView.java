package com.umeng.soexample.MVPData.view;

import com.umeng.soexample.bean.Product;

import java.util.List;

public interface IView {

    void getProduct(List<Product.DataBean> productList);

    void getFailed(Exception e);



}
