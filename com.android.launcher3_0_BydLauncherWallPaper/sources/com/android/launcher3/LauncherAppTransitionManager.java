package com.android.launcher3;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.launcher3.util.ResourceBasedOverride;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppTransitionManager implements ResourceBasedOverride {
    public int getStateElementAnimationsCount() {
        return 0;
    }

    public boolean supportsAdaptiveIconAnimation() {
        return false;
    }

    public static LauncherAppTransitionManager newInstance(Context context) {
        return (LauncherAppTransitionManager) ResourceBasedOverride.Overrides.getObject(LauncherAppTransitionManager.class, context, R.string.app_transition_manager_class);
    }

    public ActivityOptions getActivityLaunchOptions(Launcher launcher, View v) {
        int i;
        Drawable icon;
        int measuredWidth = v.getMeasuredWidth();
        int measuredHeight = v.getMeasuredHeight();
        int iWidth = 0;
        if (!(v instanceof BubbleTextView) || (icon = ((BubbleTextView) v).getIcon()) == null) {
            i = 0;
        } else {
            Rect bounds = icon.getBounds();
            iWidth = (measuredWidth - bounds.width()) / 2;
            int paddingTop = v.getPaddingTop();
            int iWidth2 = bounds.width();
            measuredHeight = bounds.height();
            i = paddingTop;
            measuredWidth = iWidth2;
        }
        return ActivityOptions.makeClipRevealAnimation(v, iWidth, i, measuredWidth, measuredHeight);
    }

    public Animator createStateElementAnimation(int index, float... values) {
        throw new RuntimeException("Unknown gesture animation " + index);
    }
}
