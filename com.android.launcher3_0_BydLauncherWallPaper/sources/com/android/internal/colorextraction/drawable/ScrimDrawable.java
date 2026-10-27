package com.android.internal.colorextraction.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import com.android.internal.graphics.ColorUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ScrimDrawable extends Drawable {
    private static final long COLOR_ANIMATION_DURATION = 2000;
    private static final String TAG = "ScrimDrawable";
    private int mAlpha = 255;
    private ValueAnimator mColorAnimation;
    private int mMainColor;
    private int mMainColorTo;
    private final Paint mPaint;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public ScrimDrawable() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
    }

    public void setColor(final int i, boolean z) {
        if (i == this.mMainColorTo) {
            return;
        }
        ValueAnimator valueAnimator = this.mColorAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mColorAnimation.cancel();
        }
        this.mMainColorTo = i;
        if (z) {
            final int i2 = this.mMainColor;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(COLOR_ANIMATION_DURATION);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.internal.colorextraction.drawable.-$$Lambda$ScrimDrawable$7I6VftgBO6hfR8os6WTdpnUKHuo
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    this.f$0.lambda$setColor$0$ScrimDrawable(i2, i, valueAnimator2);
                }
            });
            valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.internal.colorextraction.drawable.ScrimDrawable.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator, boolean z2) {
                    if (ScrimDrawable.this.mColorAnimation == animator) {
                        ScrimDrawable.this.mColorAnimation = null;
                    }
                }
            });
            valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
            valueAnimatorOfFloat.start();
            this.mColorAnimation = valueAnimatorOfFloat;
            return;
        }
        this.mMainColor = i;
        invalidateSelf();
    }

    public /* synthetic */ void lambda$setColor$0$ScrimDrawable(int i, int i2, ValueAnimator valueAnimator) {
        this.mMainColor = ColorUtils.blendARGB(i, i2, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void setXfermode(Xfermode xfermode) {
        this.mPaint.setXfermode(xfermode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.mPaint.setColor(this.mMainColor);
        this.mPaint.setAlpha(this.mAlpha);
        canvas.drawRect(getBounds(), this.mPaint);
    }

    public int getMainColor() {
        return this.mMainColor;
    }
}
