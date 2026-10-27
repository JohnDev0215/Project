package com.android.car.apps.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class CropAlignedImageView extends ImageView {
    private static final int ALIGN_HORIZONTAL_CENTER = 0;
    private static final int ALIGN_HORIZONTAL_END = 2;
    private static final int ALIGN_HORIZONTAL_START = 1;
    private float mAdditionalScale;
    private int mAlignHorizontal;
    private int mFrameHeight;
    private int mFrameWidth;

    public CropAlignedImageView(Context context) {
        this(context, null);
    }

    public CropAlignedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CropAlignedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CropAlignedImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mAdditionalScale = 1.0f;
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CrossfadeImageView, defStyleAttr, defStyleRes);
        this.mAlignHorizontal = ta.getInt(R.styleable.CrossfadeImageView_align_horizontal, 0);
        ta.recycle();
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int frameLeft, int frameTop, int frameRight, int frameBottom) {
        this.mFrameWidth = frameRight - frameLeft;
        this.mFrameHeight = frameBottom - frameTop;
        setMatrix();
        return super.setFrame(frameLeft, frameTop, frameRight, frameBottom);
    }

    private void setMatrix() {
        if (getDrawable() != null) {
            float originalImageWidth = getDrawable().getIntrinsicWidth();
            float originalImageHeight = getDrawable().getIntrinsicHeight();
            float fitHorizontallyScaleFactor = this.mFrameWidth / originalImageWidth;
            float fitVerticallyScaleFactor = this.mFrameHeight / originalImageHeight;
            float usedScaleFactor = Math.max(fitHorizontallyScaleFactor, fitVerticallyScaleFactor);
            int layoutDirection = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
            boolean isRTL = layoutDirection == 1;
            float fittedImageWidth = originalImageWidth * usedScaleFactor;
            Matrix matrix = new Matrix();
            matrix.setTranslate((-originalImageWidth) / 2.0f, (-originalImageHeight) / 2.0f);
            float f = this.mAdditionalScale;
            matrix.postScale(usedScaleFactor * f, f * usedScaleFactor);
            float dx = 0.0f;
            int i = this.mAlignHorizontal;
            if (i != 0) {
                if (i == 1) {
                    dx = isRTL ? this.mFrameWidth - (fittedImageWidth / 2.0f) : fittedImageWidth / 2.0f;
                } else if (i == 2) {
                    dx = isRTL ? fittedImageWidth / 2.0f : this.mFrameWidth - (fittedImageWidth / 2.0f);
                }
            } else {
                dx = this.mFrameWidth / 2.0f;
            }
            matrix.postTranslate(dx, this.mFrameHeight / 2.0f);
            setImageMatrix(matrix);
        }
    }

    public void setImageAdditionalScale(float scale) {
        this.mAdditionalScale = scale;
        setMatrix();
    }
}
