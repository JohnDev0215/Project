package com.android.systemui.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlayingImg extends AppCompatImageView {
    private AnimationDrawable mPlayingAnimation;

    public MediaPlayingImg(Context context) {
        super(context);
        init(context);
    }

    public MediaPlayingImg(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MediaPlayingImg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (getDrawable() == null) {
            setImageDrawable(getResources().getDrawable(R.drawable.media_playing_list_anim, null));
        }
        if (getDrawable() instanceof AnimationDrawable) {
            this.mPlayingAnimation = (AnimationDrawable) getDrawable();
        }
    }

    public void resetDrawable(int resId) {
        setImageDrawable(getResources().getDrawable(resId, null));
        if (getDrawable() instanceof AnimationDrawable) {
            this.mPlayingAnimation = (AnimationDrawable) getDrawable();
        }
    }

    public void startAnimation() {
        AnimationDrawable animationDrawable = this.mPlayingAnimation;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    public void stopAnimation() {
        AnimationDrawable animationDrawable = this.mPlayingAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }
}
