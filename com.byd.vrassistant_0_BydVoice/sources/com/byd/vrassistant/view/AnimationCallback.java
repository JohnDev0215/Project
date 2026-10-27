package com.byd.vrassistant.view;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes2.dex */
public class AnimationCallback implements Drawable.Callback {
    private static final String TAG = "AnimationCallback";
    AnimationDrawable animationDrawable;
    Handler handler = new Handler();
    ImageView imageView;
    OnAnimEndListener onAnimEndListener;

    public interface OnAnimEndListener {
        void onAnimEnd();
    }

    public AnimationCallback(ImageView imageView, AnimationDrawable animationDrawable, OnAnimEndListener onAnimEndListener) {
        this.imageView = imageView;
        this.onAnimEndListener = onAnimEndListener;
        this.animationDrawable = animationDrawable;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (this.animationDrawable.getFrame(r0.getNumberOfFrames() - 1) == drawable && this.animationDrawable.isOneShot() && this.animationDrawable.isRunning() && this.animationDrawable.isVisible()) {
            long duration = this.animationDrawable.getDuration(r4.getNumberOfFrames() - 1);
            Log.i(TAG, "invalidateDrawable: ");
            this.handler.postDelayed(new Runnable() { // from class: com.byd.vrassistant.view.AnimationCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m188x1888e1ba();
                }
            }, duration);
        }
    }

    /* JADX INFO: renamed from: lambda$invalidateDrawable$0$com-byd-vrassistant-view-AnimationCallback, reason: not valid java name */
    /* synthetic */ void m188x1888e1ba() {
        this.onAnimEndListener.onAnimEnd();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.imageView.scheduleDrawable(drawable, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.imageView.unscheduleDrawable(drawable, runnable);
    }
}
