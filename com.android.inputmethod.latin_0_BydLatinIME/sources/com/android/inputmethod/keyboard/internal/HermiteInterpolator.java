package com.android.inputmethod.keyboard.internal;

/* JADX INFO: loaded from: classes2.dex */
public final class HermiteInterpolator {
    public float mInterpolatedX;
    public float mInterpolatedY;
    private int mMaxPos;
    private int mMinPos;
    public int mP1X;
    public int mP1Y;
    public int mP2X;
    public int mP2Y;
    public float mSlope1X;
    public float mSlope1Y;
    public float mSlope2X;
    public float mSlope2Y;
    private int[] mXCoords;
    private int[] mYCoords;

    public void reset(final int[] xCoords, final int[] yCoords, final int minPos, final int maxPos) {
        this.mXCoords = xCoords;
        this.mYCoords = yCoords;
        this.mMinPos = minPos;
        this.mMaxPos = maxPos;
    }

    public void setInterval(final int p0, final int p1, final int p2, final int p3) {
        int i;
        int[] iArr = this.mXCoords;
        int i2 = iArr[p1];
        this.mP1X = i2;
        int[] iArr2 = this.mYCoords;
        int i3 = iArr2[p1];
        this.mP1Y = i3;
        int i4 = iArr[p2];
        this.mP2X = i4;
        int i5 = iArr2[p2];
        this.mP2Y = i5;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int i8 = this.mMinPos;
        if (p0 >= i8) {
            this.mSlope1X = (i4 - iArr[p0]) / 2.0f;
            this.mSlope1Y = (i5 - iArr2[p0]) / 2.0f;
            i = i5;
        } else if (p3 < this.mMaxPos) {
            float f = (iArr[p3] - i2) / 2.0f;
            float f2 = (iArr2[p3] - i3) / 2.0f;
            float f3 = i6;
            float f4 = i7;
            float f5 = (f3 * f2) - (f4 * f);
            float f6 = (f * f3) + (f2 * f4);
            float f7 = (1.0f / ((i6 * i6) + (i7 * i7))) / 2.0f;
            i = i5;
            this.mSlope1X = f7 * ((f6 * f3) + (f5 * f4));
            this.mSlope1Y = f7 * ((f6 * f4) - (f5 * f3));
        } else {
            i = i5;
            this.mSlope1X = i6;
            this.mSlope1Y = i7;
        }
        if (p3 < this.mMaxPos) {
            this.mSlope2X = (iArr[p3] - i2) / 2.0f;
            this.mSlope2Y = (iArr2[p3] - i3) / 2.0f;
            return;
        }
        if (p0 >= i8) {
            float f8 = (i4 - iArr[p0]) / 2.0f;
            float f9 = (i - iArr2[p0]) / 2.0f;
            float f10 = i6;
            float f11 = i7;
            float f12 = (f10 * f9) - (f11 * f8);
            float f13 = (f8 * f10) + (f9 * f11);
            float f14 = (1.0f / ((i6 * i6) + (i7 * i7))) / 2.0f;
            this.mSlope2X = ((f13 * f10) + (f12 * f11)) * f14;
            this.mSlope2Y = f14 * ((f13 * f11) - (f12 * f10));
            return;
        }
        this.mSlope2X = i6;
        this.mSlope2Y = i7;
    }

    public void interpolate(final float t) {
        float f = 1.0f - t;
        float f2 = 2.0f * t;
        float f3 = 1.0f + f2;
        float f4 = 3.0f - f2;
        float f5 = f * f;
        float f6 = t * t;
        this.mInterpolatedX = (((this.mP1X * f3) + (this.mSlope1X * t)) * f5) + (((this.mP2X * f4) - (this.mSlope2X * f)) * f6);
        this.mInterpolatedY = (((f3 * this.mP1Y) + (t * this.mSlope1Y)) * f5) + (((f4 * this.mP2Y) - (f * this.mSlope2Y)) * f6);
    }
}
