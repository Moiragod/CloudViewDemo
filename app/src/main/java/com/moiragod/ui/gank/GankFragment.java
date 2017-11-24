package com.moiragod.ui.gank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.moira.lib.kernal.base.BaseBindingFragment;
import com.moiragod.app.R;
import com.moiragod.app.databinding.FragmentBookBinding;
import com.moiragod.ui.gank.child1.DashboardFragment;
import com.moiragod.ui.gank.child4.EmptyFragment;
import com.moiragod.view.HomeFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * The GankFragment is the first fragment of home activity.
 * We plan to design the second fragment is BLE device list
 * the third fragment has also three child fragments for now.
 *
 *
 * There are four children fragment under this, as below
 * Dashboard
 * Walking
 * Heart beat real-time
 * Outdoor
 *
 */
public class GankFragment extends BaseBindingFragment<FragmentBookBinding> {

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);

    public GankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BookFragment.
     */
    public static GankFragment newInstance() {

        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Todd", "lifecycle GankFragment page init>>>>");
        showLoading();
        initFragmentList();
        initFragmentPager();
        showContentView();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book;
    }

    private void initFragmentPager() {
        /**
         * 注意使用的是：getChildFragmentManager，
         * 这样setOffscreenPageLimit()就可以添加上，保留相邻3个实例，切换时不会卡
         * 但会内存溢出，在显示时加载数据
         */
        HomeFragmentPagerAdapter myAdapter = new HomeFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        mBindingView.vpGank.setAdapter(myAdapter);
        // 左右预加载页面的个数
        mBindingView.vpGank.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        mBindingView.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        mBindingView.tabLayout.setupWithViewPager(mBindingView.vpGank);
    }


    private void initFragmentList() {
        mTitleList.add("Dashboard");
        mTitleList.add("Walking");
        mTitleList.add("Health");
        mTitleList.add("Outdoor");
        mFragments.add(DashboardFragment.newInstance());
        mFragments.add(EmptyFragment.newInstance("Walking"));//"Walking"
        mFragments.add(EmptyFragment.newInstance("Health"));//"Health"
        mFragments.add(EmptyFragment.newInstance("Outdoor"));//"Outdoor"
    }

    @Override
    protected void loadData() {
        Log.e("Todd", "lifecycle GankFragment loadData>>>>");
    }
}
