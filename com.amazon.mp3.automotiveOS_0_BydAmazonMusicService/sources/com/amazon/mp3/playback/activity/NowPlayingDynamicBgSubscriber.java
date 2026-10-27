package com.amazon.mp3.playback.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: NowPlayingFragmentActivity.java */
/* JADX INFO: loaded from: classes3.dex */
class NowPlayingDynamicBgSubscriber implements DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber {
    WeakReference<Context> context;
    WeakReference<ImageView> mDynamicBackgroundImage;

    NowPlayingDynamicBgSubscriber(WeakReference<ImageView> dynamicBackgroundImage, WeakReference<Context> context) {
        this.mDynamicBackgroundImage = dynamicBackgroundImage;
        this.context = context;
    }

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onPauseBackgroundImageUpdate() {
        if (this.mDynamicBackgroundImage.get() != null) {
            this.mDynamicBackgroundImage.get().setVisibility(8);
        }
    }

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onResumeBackgroundImageUpdate() {
        if (this.mDynamicBackgroundImage.get() == null || isStage()) {
            return;
        }
        this.mDynamicBackgroundImage.get().setVisibility(0);
    }

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onBackgroundImageUpdate(Bitmap bitmap) {
        if (this.mDynamicBackgroundImage.get() == null || this.context.get() == null) {
            return;
        }
        if (bitmap != null) {
            DynamicBackgroundImagePublisher.getInstance().setBackgroundWithTransition(this.context.get().getApplicationContext(), this.context.get().getResources(), this.mDynamicBackgroundImage.get(), bitmap);
        } else {
            this.mDynamicBackgroundImage.get().setImageDrawable(null);
        }
    }

    public boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }
}
