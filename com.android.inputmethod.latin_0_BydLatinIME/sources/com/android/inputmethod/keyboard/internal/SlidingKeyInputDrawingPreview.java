package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.android.inputmethod.keyboard.PointerTracker;
import com.android.inputmethod.latin.common.CoordinateUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class SlidingKeyInputDrawingPreview extends AbstractDrawingPreview {
    private final Paint mPaint;
    private final float mPreviewBodyRadius;
    private final int[] mPreviewFrom = CoordinateUtils.newInstance();
    private final int[] mPreviewTo = CoordinateUtils.newInstance();
    private final RoundedLine mRoundedLine = new RoundedLine();
    private boolean mShowsSlidingKeyInputPreview;

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void onDeallocateMemory() {
    }

    public SlidingKeyInputDrawingPreview(final TypedArray mainKeyboardViewAttr) {
        Paint paint = new Paint();
        this.mPaint = paint;
        int color = mainKeyboardViewAttr.getColor(57, 0);
        float dimension = mainKeyboardViewAttr.getDimension(59, 0.0f) / 2.0f;
        this.mPreviewBodyRadius = (mainKeyboardViewAttr.getInt(56, 100) / 100.0f) * dimension;
        int i = mainKeyboardViewAttr.getInt(58, 0);
        if (i > 0) {
            paint.setShadowLayer(dimension * (i / 100.0f), 0.0f, 0.0f, color);
        }
        paint.setColor(color);
    }

    public void dismissSlidingKeyInputPreview() {
        this.mShowsSlidingKeyInputPreview = false;
        invalidateDrawingView();
    }

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void drawPreview(final Canvas canvas) {
        if (isPreviewEnabled() && this.mShowsSlidingKeyInputPreview) {
            float f = this.mPreviewBodyRadius;
            canvas.drawPath(this.mRoundedLine.makePath(CoordinateUtils.x(this.mPreviewFrom), CoordinateUtils.y(this.mPreviewFrom), f, CoordinateUtils.x(this.mPreviewTo), CoordinateUtils.y(this.mPreviewTo), f), this.mPaint);
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void setPreviewPosition(final PointerTracker tracker) {
        tracker.getDownCoordinates(this.mPreviewFrom);
        tracker.getLastCoordinates(this.mPreviewTo);
        this.mShowsSlidingKeyInputPreview = true;
        invalidateDrawingView();
    }
}
