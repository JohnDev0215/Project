package com.byd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class VideoPreview extends TextureView {
    private int mVideoHeight;
    private int mVideoWidth;
    private OnSizeChangeListener onSizeChangeListener;
    private int viewHeight;
    private int viewWidth;

    public interface OnSizeChangeListener {
        void onSizeChange(int i, int i2);
    }

    public VideoPreview(Context context) {
        super(context);
    }

    public VideoPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoPreview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getViewHeight() {
        return this.viewHeight;
    }

    public int getViewWidth() {
        return this.viewWidth;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int defaultSize = TextureView.getDefaultSize(this.mVideoWidth, i);
        int defaultSize2 = TextureView.getDefaultSize(this.mVideoHeight, i2);
        if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i5 = this.mVideoWidth;
                int i6 = i5 * size2;
                int i7 = this.mVideoHeight;
                if (i6 < size * i7) {
                    defaultSize = (i5 * size2) / i7;
                    defaultSize2 = size2;
                } else {
                    if (i5 * size2 > size * i7) {
                        defaultSize2 = (i7 * size) / i5;
                        defaultSize = size;
                    }
                    defaultSize = size;
                    defaultSize2 = size2;
                }
            } else if (mode == 1073741824) {
                int i8 = (this.mVideoHeight * size) / this.mVideoWidth;
                if (mode2 != Integer.MIN_VALUE || i8 <= size2) {
                    defaultSize2 = i8;
                    defaultSize = size;
                }
                defaultSize = size;
                defaultSize2 = size2;
            } else {
                if (mode2 == 1073741824) {
                    i4 = (this.mVideoWidth * size2) / this.mVideoHeight;
                    if (mode == Integer.MIN_VALUE && i4 > size) {
                        defaultSize = size;
                    }
                    defaultSize2 = size2;
                } else {
                    int i9 = this.mVideoWidth;
                    int i10 = this.mVideoHeight;
                    if (mode2 != Integer.MIN_VALUE || i10 <= size2) {
                        i4 = i9;
                        size2 = i10;
                    } else {
                        i4 = (size2 * i9) / i10;
                    }
                    if (mode == Integer.MIN_VALUE && i4 > size) {
                        defaultSize2 = (i10 * size) / i9;
                        defaultSize = size;
                    }
                }
                defaultSize = i4;
                defaultSize2 = size2;
            }
        }
        int i11 = this.viewWidth;
        if (i11 > 0 && (i3 = this.viewHeight) > 0 && i11 == defaultSize && i3 == defaultSize2) {
            setMeasuredDimension(defaultSize, defaultSize2);
            return;
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        this.viewWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.viewHeight = measuredHeight;
        OnSizeChangeListener onSizeChangeListener = this.onSizeChangeListener;
        if (onSizeChangeListener != null) {
            onSizeChangeListener.onSizeChange(this.viewWidth, measuredHeight);
        }
    }

    public void setOnSizeChangeListener(OnSizeChangeListener onSizeChangeListener) {
        this.onSizeChangeListener = onSizeChangeListener;
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        if (i <= 0 || i2 <= 0) {
            return;
        }
        requestLayout();
    }
}
