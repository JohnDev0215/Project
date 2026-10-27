package com.byd.music.view.musicPlayingSlideView;

import android.animation.Animator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public abstract class IndicatorController {
    private List<Animator> mAnimators;
    private WeakReference<View> mTarget;

    public enum AnimStatus {
        START,
        END,
        CANCEL
    }

    public abstract List<Animator> createAnimation(float[] fArr);

    public abstract void draw(Canvas canvas, Paint paint, float[] fArr);

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

    public void initAnimation(float[] fArr) {
        this.mAnimators = createAnimation(fArr);
    }

    public void setAnimationStatus(AnimStatus animStatus) {
        List<Animator> list = this.mAnimators;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Animator animator = this.mAnimators.get(i);
            boolean zIsRunning = animator.isRunning();
            int i2 = AnonymousClass1.$SwitchMap$com$byd$music$view$musicPlayingSlideView$IndicatorController$AnimStatus[animStatus.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (zIsRunning) {
                        animator.pause();
                    }
                } else if (zIsRunning) {
                    animator.cancel();
                }
            } else if (!zIsRunning) {
                animator.start();
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.music.view.musicPlayingSlideView.IndicatorController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$music$view$musicPlayingSlideView$IndicatorController$AnimStatus;

        static {
            int[] iArr = new int[AnimStatus.values().length];
            $SwitchMap$com$byd$music$view$musicPlayingSlideView$IndicatorController$AnimStatus = iArr;
            try {
                iArr[AnimStatus.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$music$view$musicPlayingSlideView$IndicatorController$AnimStatus[AnimStatus.CANCEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
