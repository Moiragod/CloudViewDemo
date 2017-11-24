package com.moiragod.ui.gank.child4;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.moira.lib.kernal.base.BaseBindingFragment;
import com.moiragod.app.R;
import com.moiragod.app.databinding.FragmentBookBinding;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmptyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmptyFragment extends BaseBindingFragment<FragmentBookBinding> {

    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);

    public EmptyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BookFragment.
     */
    public static EmptyFragment newInstance(String name) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putString("title", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showLoading();
        initFragmentList();

        showContentView();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book;
    }

    private void initFragmentList() {
//        mTitleList.add("文学");
//        mTitleList.add("文化");
//        mTitleList.add("生活");
//        mFragments.add(BookCustomFragment.newInstance("文学"));
//        mFragments.add(BookCustomFragment.newInstance("文化"));
//        mFragments.add(BookCustomFragment.newInstance("生活"));
    }
}
