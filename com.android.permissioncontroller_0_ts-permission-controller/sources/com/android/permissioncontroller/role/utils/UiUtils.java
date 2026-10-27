package com.android.permissioncontroller.role.utils;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes8.dex */
public class UiUtils {
    private UiUtils() {
    }

    public static float dpToPx(float dp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(1, dp, metrics);
    }

    public static int dpToPxOffset(float dp, Context context) {
        return (int) dpToPx(dp, context);
    }

    public static int dpToPxSize(float dp, Context context) {
        float value = dpToPx(dp, context);
        int size = (int) (value >= 0.0f ? 0.5f + value : value - 0.5f);
        if (size != 0) {
            return size;
        }
        if (value == 0.0f) {
            return 0;
        }
        if (value > 0.0f) {
            return 1;
        }
        return -1;
    }

    public static void setViewShown(final View view, boolean shown) {
        int i;
        if (shown && view.getVisibility() == 0 && view.getAlpha() == 1.0f) {
            view.animate().alpha(1.0f).setDuration(0L);
            return;
        }
        if (!shown && (view.getVisibility() != 0 || view.getAlpha() == 0.0f)) {
            view.animate().alpha(0.0f).setDuration(0L);
            view.setVisibility(4);
            return;
        }
        if (shown && view.getVisibility() != 0) {
            view.setAlpha(0.0f);
            view.setVisibility(0);
        }
        int duration = view.getResources().getInteger(R.integer.config_mediumAnimTime);
        Context context = view.getContext();
        if (shown) {
            i = 17563661;
        } else {
            i = 17563663;
        }
        Interpolator interpolator = AnimationUtils.loadInterpolator(context, i);
        view.animate().alpha(shown ? 1.0f : 0.0f).setDuration(duration).setInterpolator(interpolator).setListener(shown ? null : new AnimatorListenerAdapter() { // from class: com.android.permissioncontroller.role.utils.UiUtils.1
            private boolean mCanceled = false;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                this.mCanceled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!this.mCanceled) {
                    view.setVisibility(4);
                }
            }
        });
    }
}
