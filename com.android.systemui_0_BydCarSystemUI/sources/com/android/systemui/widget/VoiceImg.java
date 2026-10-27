package com.android.systemui.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class VoiceImg extends ImageView {
    public VoiceImg(Context context) {
        super(context);
    }

    public VoiceImg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VoiceImg(Context context, AttributeSet attrs, int defStyleAttr) {
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
        animationDrawable.setOneShot(false);
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
