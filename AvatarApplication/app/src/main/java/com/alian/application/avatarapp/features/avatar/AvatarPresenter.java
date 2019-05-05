package com.alian.application.avatarapp.features.avatar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.alian.application.avatarapp.features.avatar.api.AvatarOrchestrator;
import com.alian.application.avatarapp.utils.NetworkConnectionValidator;

import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import timber.log.Timber;

import static android.content.ContentValues.TAG;

public class AvatarPresenter implements AvatarContract.Presenter {
    @Inject
    AvatarContract.View view;

    private final @NonNull
    AvatarOrchestrator avatarOrchestrator;

    @Inject
    AvatarPresenter(@NonNull AvatarContract.View view,
                    @NonNull AvatarOrchestrator avatarOrchestrator) {
        this.view = view;
        this.avatarOrchestrator = avatarOrchestrator;
    }


    @Override
    public void bind() {

    }

    @Override
    public void loadNextImage() {
        int max = 70;
        int min = 1;
        Random rand = new Random();

        int random = rand.nextInt((max - min) + 1) + min;


        avatarOrchestrator.getImage(random).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (responseBody != null) {
                    // display the image data in a ImageView or save it
                    Bitmap bmp = BitmapFactory.decodeStream(responseBody.byteStream());
                    view.setImageBitmap(bmp);
                } else {
                    view.noInternetConnection();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (NetworkConnectionValidator.checkConnectionError(e)) {
                    view.noInternetConnection();
                    Timber.e(TAG, "Connection error:");
                    return;
                }
                Timber.e(TAG, "onError:", e.getMessage());

            }

            @Override
            public void onComplete() {
            }
        });
    }

}
