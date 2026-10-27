package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes3.dex */
public final class SeekBar extends View {
    private AccessibilitySeekListener mAccessibilitySeekListener;
    private int mActiveBarHeight;
    private int mActiveRadius;
    private final Paint mBackgroundPaint;
    private final RectF mBackgroundRect;
    private int mBarHeight;
    private final Paint mKnobPaint;
    private int mKnobx;
    private int mMax;
    private int mProgress;
    private final Paint mProgressPaint;
    private final RectF mProgressRect;
    private int mSecondProgress;
    private final Paint mSecondProgressPaint;
    private final RectF mSecondProgressRect;

    public static abstract class AccessibilitySeekListener {
        public abstract boolean onAccessibilitySeekBackward();

        public abstract boolean onAccessibilitySeekForward();
    }

    public SeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mProgressRect = new RectF();
        this.mSecondProgressRect = new RectF();
        this.mBackgroundRect = new RectF();
        this.mSecondProgressPaint = new Paint(1);
        this.mProgressPaint = new Paint(1);
        this.mBackgroundPaint = new Paint(1);
        this.mKnobPaint = new Paint(1);
        setWillNotDraw(false);
        this.mBackgroundPaint.setColor(-7829368);
        this.mSecondProgressPaint.setColor(-3355444);
        this.mProgressPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mKnobPaint.setColor(-1);
        this.mBarHeight = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_progressbar_bar_height);
        this.mActiveBarHeight = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_progressbar_active_bar_height);
        this.mActiveRadius = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_progressbar_active_radius);
    }

    public void setActiveRadius(int radius) {
        this.mActiveRadius = radius;
        calculate();
    }

    public void setBarHeight(int barHeight) {
        this.mBarHeight = barHeight;
        calculate();
    }

    public void setActiveBarHeight(int activeBarHeight) {
        this.mActiveBarHeight = activeBarHeight;
        calculate();
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        calculate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = isFocused() ? this.mActiveRadius : this.mBarHeight / 2;
        canvas.drawRoundRect(this.mBackgroundRect, radius, radius, this.mBackgroundPaint);
        if (this.mSecondProgressRect.right > this.mSecondProgressRect.left) {
            canvas.drawRoundRect(this.mSecondProgressRect, radius, radius, this.mSecondProgressPaint);
        }
        canvas.drawRoundRect(this.mProgressRect, radius, radius, this.mProgressPaint);
        canvas.drawCircle(this.mKnobx, getHeight() / 2, radius, this.mKnobPaint);
    }

    public void setProgress(int progress) {
        if (progress > this.mMax) {
            progress = this.mMax;
        } else if (progress < 0) {
            progress = 0;
        }
        this.mProgress = progress;
        calculate();
    }

    public void setSecondaryProgress(int progress) {
        if (progress > this.mMax) {
            progress = this.mMax;
        } else if (progress < 0) {
            progress = 0;
        }
        this.mSecondProgress = progress;
        calculate();
    }

    public int getProgress() {
        return this.mProgress;
    }

    public int getSecondProgress() {
        return this.mSecondProgress;
    }

    public int getMax() {
        return this.mMax;
    }

    public void setMax(int max) {
        this.mMax = max;
        calculate();
    }

    public void setProgressColor(int color) {
        this.mProgressPaint.setColor(color);
    }

    public void setSecondaryProgressColor(int color) {
        this.mSecondProgressPaint.setColor(color);
    }

    public int getSecondaryProgressColor() {
        return this.mSecondProgressPaint.getColor();
    }

    private void calculate() {
        int barHeight = isFocused() ? this.mActiveBarHeight : this.mBarHeight;
        int width = getWidth();
        int height = getHeight();
        int verticalPadding = (height - barHeight) / 2;
        RectF rectF = this.mBackgroundRect;
        int i = this.mBarHeight;
        rectF.set(i / 2, verticalPadding, width - (i / 2), height - verticalPadding);
        int radius = isFocused() ? this.mActiveRadius : this.mBarHeight / 2;
        int progressWidth = width - (radius * 2);
        float progressPixels = (this.mProgress / this.mMax) * progressWidth;
        RectF rectF2 = this.mProgressRect;
        int i2 = this.mBarHeight;
        rectF2.set(i2 / 2, verticalPadding, (i2 / 2) + progressPixels, height - verticalPadding);
        float secondProgressPixels = (this.mSecondProgress / this.mMax) * progressWidth;
        this.mSecondProgressRect.set(this.mProgressRect.right, verticalPadding, (this.mBarHeight / 2) + secondProgressPixels, height - verticalPadding);
        this.mKnobx = ((int) progressPixels) + radius;
        invalidate();
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return android.widget.SeekBar.class.getName();
    }

    public void setAccessibilitySeekListener(AccessibilitySeekListener listener) {
        this.mAccessibilitySeekListener = listener;
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        AccessibilitySeekListener accessibilitySeekListener = this.mAccessibilitySeekListener;
        if (accessibilitySeekListener != null) {
            if (action == 4096) {
                return accessibilitySeekListener.onAccessibilitySeekForward();
            }
            if (action == 8192) {
                return accessibilitySeekListener.onAccessibilitySeekBackward();
            }
        }
        return super.performAccessibilityAction(action, arguments);
    }
}
