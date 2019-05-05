package com.alian.application.avatarapp.features.avatar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alian.application.avatarapp.R;
import com.alian.application.avatarapp.core.base.BaseActivity;

public class AvatarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
    }
}