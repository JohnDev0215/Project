package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public class AutoRotateDrawable extends ForwardingDrawable implements Runnable {
    private boolean mClockwise;
    private int mInterval;
    private boolean mIsScheduled;

    @VisibleForTesting
    float mRotationAngle;

    public AutoRotateDrawable(Drawable drawable, int interval) {
        this(drawable, interval, true);
    }

    public AutoRotateDrawable(Drawable drawable, int interval, boolean clockwise) {
        super((Drawable) Preconditions.checkNotNull(drawable));
        this.mRotationAngle = 0.0f;
        this.mIsScheduled = false;
        this.mInterval = interval;
        this.mClockwise = clockwise;
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int iSave = canvas.save();
        Rect bounds = getBounds();
        int i = bounds.right - bounds.left;
        int i2 = bounds.bottom - bounds.top;
        float f = this.mRotationAngle;
        if (!this.mClockwise) {
            f = 360.0f - f;
        }
        canvas.rotate(f, r3 + (i / 2), r1 + (i2 / 2));
        super.draw(canvas);
        canvas.restoreToCount(iSave);
        scheduleNextFrame();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mIsScheduled = false;
        this.mRotationAngle += getIncrement();
        invalidateSelf();
    }

    private void scheduleNextFrame() {
        if (this.mIsScheduled) {
            return;
        }
        this.mIsScheduled = true;
        scheduleSelf(this, SystemClock.uptimeMillis() + 20);
    }

    private int getIncrement() {
        return (int) ((20.0f / this.mInterval) * 360.0f);
    }
}
