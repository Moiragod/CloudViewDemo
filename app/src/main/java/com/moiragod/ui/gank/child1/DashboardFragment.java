package com.moiragod.ui.gank.child1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.moira.lib.kernal.base.BaseBindingFragment;
import com.moiragod.app.R;
import com.moiragod.app.databinding.FragmentDashboardBinding;


/**
 * Created by tdu011 on 11/14/17.
 */

public class DashboardFragment extends BaseBindingFragment<FragmentDashboardBinding> {

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_dashboard;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showContentView();
    }

    @Override
    protected void loadData() {
        Log.e("Todd", "lifecycle DashboardFragment loadData>>>>");
        postDelayLoad();
    }

    private void postDelayLoad() {

    }
}
