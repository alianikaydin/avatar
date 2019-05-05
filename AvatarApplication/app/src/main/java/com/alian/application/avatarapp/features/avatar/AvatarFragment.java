package com.alian.application.avatarapp.features.avatar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alian.application.avatarapp.R;
import com.alian.application.avatarapp.core.base.BaseFragment;
import com.alian.application.avatarapp.features.widgets.CircularProgress;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class AvatarFragment extends BaseFragment implements AvatarContract.View {

    @Inject
    AvatarContract.Presenter presenter;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.next_button)
    Button button;

    @BindView(R.id.custom_progress_bar)
    CircularProgress circularProgress;


    @Override
    protected int getContentViewID() {
        return R.layout.fragment_avatar;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this, getView());
        AndroidSupportInjection.inject(this);
        presenter.bind();
    }

    @OnClick(R.id.next_button)
    public void onNextButtonClicked() {
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        presenter.loadNextImage();

    }

    @Override
    public void setImageBitmap(Bitmap bmp) {
        imageView.setImageBitmap(bmp);
    }

    @Override
    public void noInternetConnection() {
        Toast.makeText(getContext(), getString(R.string.slow_or_no_internet_connection), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressBar(int percent) {
        circularProgress.setProgress(percent);
    }
}
