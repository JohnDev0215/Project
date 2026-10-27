package com.visualon.OSMPUtils;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes7.dex */
public class voSurfaceView extends SurfaceView {
    private static final String TAG = "voSurfaceView.java";
    private final RectF mAfter;
    private final RectF mBefore;
    private final Matrix mTempMatrix;
    private float mTranslationX;
    private float mTranslationY;
    private int mVideoHeight;
    private int mVideoWidth;
    private View relevanceTextureView;

    public voSurfaceView(Context context) {
        super(context);
        this.relevanceTextureView = null;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mBefore = new RectF();
        this.mAfter = new RectF();
        this.mTempMatrix = new Matrix();
    }

    public voSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.relevanceTextureView = null;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mBefore = new RectF();
        this.mAfter = new RectF();
        this.mTempMatrix = new Matrix();
    }

    public voSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.relevanceTextureView = null;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mBefore = new RectF();
        this.mAfter = new RectF();
        this.mTempMatrix = new Matrix();
    }

    public void addRelevanceTextureView(View view) {
        this.relevanceTextureView = view;
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize = View.getDefaultSize(this.mVideoWidth, i);
        int defaultSize2 = View.getDefaultSize(this.mVideoHeight, i2);
        if (this.mVideoHeight <= 0 || this.mVideoWidth <= 0) {
            setMeasuredDimension(defaultSize, defaultSize2);
            return;
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "(%d, %d)", Integer.valueOf(this.mVideoWidth), Integer.valueOf(this.mVideoHeight));
        }
        setMeasuredDimension(this.mVideoWidth, this.mVideoHeight);
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.mVideoWidth = layoutParams.width;
        this.mVideoHeight = layoutParams.height;
        super.setLayoutParams(layoutParams);
        View view = this.relevanceTextureView;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            layoutParams2.width = this.mVideoWidth;
            layoutParams2.height = this.mVideoHeight;
            this.relevanceTextureView.setLayoutParams(layoutParams2);
        }
    }

    public void voSetX(float f) {
        setX(f);
    }

    public void voSetY(float f) {
        setY(f);
    }

    private void voSetTranslationX(float f) {
        if (this.mTranslationX != f) {
            prepareForUpdate();
            this.mTranslationX = f;
            invalidateAfterUpdate();
        }
    }

    private void voSetTranslationY(float f) {
        if (this.mTranslationY != f) {
            prepareForUpdate();
            this.mTranslationY = f;
            invalidateAfterUpdate();
        }
    }

    private void prepareForUpdate() {
        computeRect(this.mBefore, this);
    }

    private void computeRect(RectF rectF, View view) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        this.mTempMatrix.mapRect(rectF);
        rectF.offset(view.getLeft(), view.getTop());
        rectF.sort();
    }

    private void invalidateAfterUpdate() {
        if (getParent() == null) {
            return;
        }
        RectF rectF = this.mAfter;
        computeRect(rectF, this);
        rectF.union(this.mBefore);
        ((View) getParent()).invalidate((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
    }
}
