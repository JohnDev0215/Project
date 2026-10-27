package com.amazon.music.voice.ui.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class AnimationUtils {
    public static Animation newCollapseAnimation(final View view, final boolean z) {
        final int height = view.getHeight();
        Animation animation = new Animation() { // from class: com.amazon.music.voice.ui.util.AnimationUtils.2
            @Override // android.view.animation.Animation
            public boolean willChangeBounds() {
                return true;
            }

            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                if (f == 1.0f) {
                    view.setVisibility(8);
                } else {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    int i = height;
                    layoutParams.height = i - ((int) (i * f));
                    view.requestLayout();
                }
                if (z) {
                    view.setAlpha(1.0f - f);
                }
            }
        };
        animation.setDuration((int) (height / view.getContext().getResources().getDisplayMetrics().density));
        return animation;
    }
}
