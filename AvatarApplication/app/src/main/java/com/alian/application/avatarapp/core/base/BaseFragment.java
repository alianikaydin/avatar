package com.alian.application.avatarapp.core.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected Activity activity;
    protected Context mContext;

    protected abstract @LayoutRes
    int getContentViewID();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentViewID(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof Activity){
            activity=(Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
        activity = null;
    }

    @NonNull
    @Override
    @SuppressWarnings("ConstantConditions")
    public View getView() {
        return super.getView();
    }
}
