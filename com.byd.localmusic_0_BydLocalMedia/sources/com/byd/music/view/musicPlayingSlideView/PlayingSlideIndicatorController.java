package com.byd.music.view.musicPlayingSlideView;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public class PlayingSlideIndicatorController {
    private AnimationDrawable animationDrawable;
    private WeakReference<View> mTarget;

    public enum AnimStatus {
        START,
        END,
        CANCEL
    }

    public void setTarget(View view) {
        this.mTarget = new WeakReference<>(view);
    }

    public View getTarget() {
        WeakReference<View> weakReference = this.mTarget;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getWidth() {
        if (getTarget() != null) {
            return getTarget().getWidth();
        }
        return 0;
    }

    public int getHeight() {
        if (getTarget() != null) {
            return getTarget().getHeight();
        }
        return 0;
    }

    public void postInvalidate() {
        if (getTarget() != null) {
            getTarget().postInvalidate();
        }
    }

    public void initAnimation(AnimationDrawable animationDrawable) {
        this.animationDrawable = animationDrawable;
        postInvalidate();
    }

    public void setAnimationStatus(AnimStatus animStatus) {
        if (this.animationDrawable == null) {
            return;
        }
        if (AnonymousClass1.$SwitchMap$com$byd$music$view$musicPlayingSlideView$PlayingSlideIndicatorController$AnimStatus[animStatus.ordinal()] == 1) {
            this.animationDrawable.start();
        } else {
            this.animationDrawable.stop();
        }
    }

    /* JADX INFO: renamed from: com.byd.music.view.musicPlayingSlideView.PlayingSlideIndicatorController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$music$view$musicPlayingSlideView$PlayingSlideIndicatorController$AnimStatus;

        static {
            int[] iArr = new int[AnimStatus.values().length];
            $SwitchMap$com$byd$music$view$musicPlayingSlideView$PlayingSlideIndicatorController$AnimStatus = iArr;
            try {
                iArr[AnimStatus.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
