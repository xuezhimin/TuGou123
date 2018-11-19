package com.umeng.soexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.fragment.HeadFragment;
import com.umeng.soexample.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mFrag;
    /**
     * 首页
     */
    private TextView mTxtHead;
    /**
     * 我的
     */
    private TextView mTxtMe;
    //fragment集合
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();


    }

    private void initView() {
        mFrag = findViewById(R.id.frag);
        mTxtHead = findViewById(R.id.txt_head);
        mTxtMe = findViewById(R.id.txt_Me);
        mTxtHead.setOnClickListener(this);
        mTxtMe.setOnClickListener(this);

        fragments = new ArrayList<>();
        fragments.add(new HeadFragment());
        fragments.add(new MeFragment());


        //viewpager和fragment切换
        mFrag.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_head:
                mFrag.setCurrentItem(0);
                break;
            case R.id.txt_Me:
                mFrag.setCurrentItem(1);
                break;
        }
    }
}
