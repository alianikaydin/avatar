package com.alian.application.avatarapp.features.avatar;

import android.graphics.Bitmap;

public interface AvatarContract {
    interface View{

        void setImageBitmap(Bitmap bmp);

        void noInternetConnection();

        //TODO: couldn't implement progress mechanism on Retrofit because of the dagger2
        void setProgressBar(int percent);
    }
    interface Presenter {

        void bind();

        void loadNextImage();
    }
}
