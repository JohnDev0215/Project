package com.amazon.avod.media.playback.android;

import android.content.Context;
import android.view.SurfaceView;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonSurfaceView extends SurfaceView implements FixedDimensionView {
    private boolean mFitToScreen;
    private int mFixedHeight;
    private int mFixedWidth;
    private float mVideoAspectRatio;

    public AmazonSurfaceView(Context context) {
        super(context);
        this.mVideoAspectRatio = 0.0f;
        this.mFixedWidth = -1;
        this.mFixedHeight = -1;
    }

    @Override // com.amazon.avod.media.playback.android.FixedDimensionView
    public void setFixedDimensions(int i, int i2) {
        this.mFixedWidth = i;
        this.mFixedHeight = i2;
        this.mFitToScreen = false;
        if (i > 0 && i2 > 0) {
            this.mVideoAspectRatio = i / i2;
        }
        requestLayout();
    }

    public void setFitToScreen(boolean z) {
        this.mFitToScreen = z;
        requestLayout();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        if (!this.mFitToScreen) {
            int i4 = this.mFixedWidth;
            if (i4 >= 0 && (i3 = this.mFixedHeight) >= 0) {
                setMeasuredDimension(i4, i3);
                return;
            } else {
                super.onMeasure(i, i2);
                return;
            }
        }
        if (this.mVideoAspectRatio <= 0.0f) {
            super.onMeasure(i, i2);
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth <= 0 || measuredHeight <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        float f = measuredWidth;
        float f2 = measuredHeight;
        float f3 = (this.mVideoAspectRatio / (f / f2)) - 1.0f;
        if (Math.abs(f3) <= 0.01f) {
            super.onMeasure(i, i2);
            return;
        }
        if (f3 > 0.0f) {
            measuredHeight = (int) (f / this.mVideoAspectRatio);
        } else {
            measuredWidth = (int) (f2 * this.mVideoAspectRatio);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
    }
}
