package com.moiragod.ui.book;

import android.content.Context;
import android.os.Bundle;

import com.moira.lib.kernal.base.BaseBindingFragment;
import com.moiragod.app.R;
import com.moiragod.ui.MainActivity;

/**
 * Created by tdu011 on 11/10/17.
 */

public class BookCustomFragment extends BaseBindingFragment {
    private MainActivity activity;
    private static final String TYPE = "param1";
    private String mType = "综合";

    public BookCustomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    public static BookCustomFragment newInstance(String param1) {
        BookCustomFragment fragment = new BookCustomFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(TYPE);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book;
    }
}
