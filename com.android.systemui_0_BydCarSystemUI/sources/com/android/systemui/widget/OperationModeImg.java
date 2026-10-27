package com.android.systemui.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* JADX INFO: loaded from: classes.dex */
public class OperationModeImg extends AppCompatImageView {
    public OperationModeImg(Context context) {
        super(context);
    }

    public OperationModeImg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OperationModeImg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAnimRes(int resId) {
        clearAnimation();
        setImageDrawable(getResources().getDrawable(resId, null));
    }

    public void startModeAnimation() {
        Drawable drawable = getDrawable();
        if (drawable == null || !(drawable instanceof AnimationDrawable)) {
            return;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        animationDrawable.setOneShot(true);
        animationDrawable.start();
    }

    public void stopModeAnimation() {
        Drawable drawable = getDrawable();
        if (drawable == null || !(drawable instanceof AnimationDrawable)) {
            return;
        }
        ((AnimationDrawable) drawable).stop();
    }
}
