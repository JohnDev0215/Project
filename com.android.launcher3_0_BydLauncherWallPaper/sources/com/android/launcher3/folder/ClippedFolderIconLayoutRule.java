package com.android.launcher3.folder;

/* JADX INFO: loaded from: classes4.dex */
public class ClippedFolderIconLayoutRule {
    public static final int ENTER_INDEX = -3;
    public static final int EXIT_INDEX = -2;
    private static final float ITEM_RADIUS_SCALE_FACTOR = 1.33f;
    public static final int MAX_NUM_ITEMS_IN_PREVIEW = 9;
    private static final float MAX_RADIUS_DILATION = 0.15f;
    private static final float MAX_SCALE = 0.58f;
    private static final int MIN_NUM_ITEMS_IN_PREVIEW = 2;
    private static final float MIN_SCALE = 0.27f;
    private float mAvailableSpace;
    private float mBaselineIconScale;
    private float mIconSize;
    private boolean mIsRtl;
    private float mRadius;
    private float[] mTmpPoint = new float[2];

    public void init(int availableSpace, float intrinsicIconSize, boolean rtl) {
        float f = availableSpace;
        this.mAvailableSpace = f;
        this.mRadius = (ITEM_RADIUS_SCALE_FACTOR * f) / 2.0f;
        this.mIconSize = intrinsicIconSize;
        this.mIsRtl = rtl;
        this.mBaselineIconScale = f / (intrinsicIconSize * 1.0f);
    }

    public PreviewItemDrawingParams computePreviewItemDrawingParams(int index, int curNumItems, PreviewItemDrawingParams params) {
        float fScaleForItem = scaleForItem();
        if (index == -2) {
            getGridPosition(0, 2, this.mTmpPoint);
        } else if (index == -3) {
            getGridPosition(1, 2, this.mTmpPoint);
        } else if (index >= 9) {
            float[] fArr = this.mTmpPoint;
            float f = (this.mAvailableSpace / 2.0f) - ((this.mIconSize * fScaleForItem) / 2.0f);
            fArr[1] = f;
            fArr[0] = f;
        } else {
            getPosition(index, curNumItems, this.mTmpPoint);
        }
        getPosition(index, this.mTmpPoint);
        float[] fArr2 = this.mTmpPoint;
        float f2 = fArr2[0];
        float f3 = fArr2[1];
        if (params == null) {
            return new PreviewItemDrawingParams(f2, f3, fScaleForItem, 0.0f);
        }
        params.update(f2, f3, fScaleForItem);
        params.overlayAlpha = 0.0f;
        return params;
    }

    private void getPosition(int index, float[] result) {
        float f = this.mAvailableSpace;
        float fScaleForItem = this.mIconSize * scaleForItem();
        float f2 = (f - (3.0f * fScaleForItem)) / 4.0f;
        float f3 = fScaleForItem + f2;
        result[0] = ((index % 3) * f3) + f2;
        result[1] = (f3 * (index / 3)) + f2;
    }

    private void getGridPosition(int row, int col, float[] result) {
        getPosition(0, 4, result);
        float f = result[0];
        float f2 = result[1];
        getPosition(3, 4, result);
        float f3 = result[0] - f;
        float f4 = result[1] - f2;
        result[0] = f + (col * f3);
        result[1] = f2 + (row * f4);
    }

    private void getPosition(int index, int curNumItems, float[] result) {
        int i = index;
        int iMax = Math.max(curNumItems, 2);
        boolean z = this.mIsRtl;
        double d = 0.0d;
        double d2 = z ? 0.0d : 3.141592653589793d;
        int i2 = z ? 1 : -1;
        if (iMax == 3) {
            d = 0.5235987755982988d;
        } else if (iMax == 4) {
            d = 0.7853981633974483d;
        }
        double d3 = i2;
        double d4 = d2 + (d * d3);
        if (iMax == 4 && i == 3) {
            i = 2;
        } else if (iMax == 4 && i == 2) {
            i = 3;
        }
        float f = this.mRadius * ((((iMax - 2) * MAX_RADIUS_DILATION) / 7.0f) + 1.0f);
        double d5 = d4 + (((double) i) * (6.283185307179586d / ((double) iMax)) * d3);
        float fScaleForItem = (this.mIconSize * scaleForItem(iMax)) / 2.0f;
        result[0] = ((this.mAvailableSpace / 2.0f) + ((float) ((((double) f) * Math.cos(d5)) / 2.0d))) - fScaleForItem;
        result[1] = ((this.mAvailableSpace / 2.0f) + ((float) ((((double) (-f)) * Math.sin(d5)) / 2.0d))) - fScaleForItem;
    }

    public float scaleForItem(int numItems) {
        return this.mBaselineIconScale * MIN_SCALE;
    }

    public float scaleForItem() {
        return this.mBaselineIconScale * MIN_SCALE;
    }

    public float getIconSize() {
        return this.mIconSize;
    }
}
