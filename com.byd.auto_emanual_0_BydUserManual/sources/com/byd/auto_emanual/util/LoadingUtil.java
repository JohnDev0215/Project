package com.byd.auto_emanual.util;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.byd.auto_emanual.R;

/* JADX INFO: loaded from: classes.dex */
public class LoadingUtil {
    public static void startLoading(Context context, ImageView imageView) {
        imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.loading));
    }

    public static void stopLoading(ImageView imageView) {
        if (imageView == null || imageView.getAnimation() == null) {
            return;
        }
        imageView.getAnimation().cancel();
    }

    public static ObjectAnimator startLoading(ImageView imageView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.setRepeatCount(-1);
        objectAnimatorOfFloat.setRepeatMode(1);
        objectAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        objectAnimatorOfFloat.start();
        return objectAnimatorOfFloat;
    }

    public static void stopLoading(ObjectAnimator animator) {
        if (animator != null) {
            animator.cancel();
        }
    }
}
