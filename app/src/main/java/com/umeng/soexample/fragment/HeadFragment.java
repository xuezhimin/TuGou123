package com.umeng.soexample.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.umeng.soexample.HomeActivity;
import com.umeng.soexample.MVPData.presenter.Presenter;
import com.umeng.soexample.MVPData.view.IView;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.MyRecycleAdapter;
import com.umeng.soexample.bean.Product;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeadFragment extends Fragment implements IView {

    private RecyclerView mRRecyclerView;
    private Presenter presenter;
    //数据集合
    private List<Product.DataBean> list;
    private MyRecycleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_head, container, false);
        mRRecyclerView = view.findViewById(R.id.recycle_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //实例化P层
        presenter = new Presenter();
        presenter.attach(this);
        presenter.getProduct();
        list = new ArrayList<>();
        adapter = new MyRecycleAdapter(getActivity(), list);
        //布局管理器
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRRecyclerView.setLayoutManager(layoutManager);
        //适配器
        mRRecyclerView.setAdapter(adapter);
    }
    @Override
    public void getProduct(List<Product.DataBean> productList) {
        if (productList != null) {
            list.clear();
            list.addAll(productList);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getFailed(Exception e) {
        Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
