package com.moiragod.ui.one;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.moira.lib.kernal.base.BaseBindingFragment;
import com.moiragod.app.R;
import com.moiragod.app.databinding.FragmentBookBinding;

import com.moiragod.ui.book.BookCustomFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneFragment extends BaseBindingFragment<FragmentBookBinding> {

    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);

    public OneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BookFragment.
     */
    public static OneFragment newInstance() {

        OneFragment fragment = new OneFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Todd", "lifecycle OneFragment page init>>>>");
        showLoading();
        initFragmentList();

        showContentView();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book;
    }

    private void initFragmentList() {
        mTitleList.add("文学");
        mTitleList.add("文化");
        mTitleList.add("生活");
        mFragments.add(BookCustomFragment.newInstance("文学"));
        mFragments.add(BookCustomFragment.newInstance("文化"));
        mFragments.add(BookCustomFragment.newInstance("生活"));
    }

    @Override
    protected void loadData() {
        Log.e("Todd", "lifecycle OneFragment loadData>>>>");
    }
}
