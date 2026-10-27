package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.latin.common.ResizableIntArray;

/* JADX INFO: loaded from: classes2.dex */
public final class GestureStrokeDrawingPoints {
    public static final int PREVIEW_CAPACITY = 256;
    private static final double TWO_PI = 6.283185307179586d;
    private double mDistanceFromLastSample;
    private final GestureStrokeDrawingParams mDrawingParams;
    private int mLastInterpolatedPreviewIndex;
    private int mLastPreviewSize;
    private int mLastX;
    private int mLastY;
    private int mStrokeId;
    private final ResizableIntArray mPreviewEventTimes = new ResizableIntArray(256);
    private final ResizableIntArray mPreviewXCoordinates = new ResizableIntArray(256);
    private final ResizableIntArray mPreviewYCoordinates = new ResizableIntArray(256);
    private final HermiteInterpolator mInterpolator = new HermiteInterpolator();

    private static double angularDiff(final double a1, final double a0) {
        double d = a1 - a0;
        while (d > 3.141592653589793d) {
            d -= TWO_PI;
        }
        while (d < -3.141592653589793d) {
            d += TWO_PI;
        }
        return d;
    }

    public GestureStrokeDrawingPoints(final GestureStrokeDrawingParams drawingParams) {
        this.mDrawingParams = drawingParams;
    }

    private void reset() {
        this.mStrokeId++;
        this.mLastPreviewSize = 0;
        this.mLastInterpolatedPreviewIndex = 0;
        this.mPreviewEventTimes.setLength(0);
        this.mPreviewXCoordinates.setLength(0);
        this.mPreviewYCoordinates.setLength(0);
    }

    public int getGestureStrokeId() {
        return this.mStrokeId;
    }

    public void onDownEvent(final int x, final int y, final int elapsedTimeSinceFirstDown) {
        reset();
        onMoveEvent(x, y, elapsedTimeSinceFirstDown);
    }

    private boolean needsSampling(final int x, final int y) {
        this.mDistanceFromLastSample += Math.hypot(x - this.mLastX, y - this.mLastY);
        this.mLastX = x;
        this.mLastY = y;
        boolean z = this.mPreviewEventTimes.getLength() == 0;
        if (this.mDistanceFromLastSample < this.mDrawingParams.mMinSamplingDistance && !z) {
            return false;
        }
        this.mDistanceFromLastSample = 0.0d;
        return true;
    }

    public void onMoveEvent(final int x, final int y, final int elapsedTimeSinceFirstDown) {
        if (needsSampling(x, y)) {
            this.mPreviewEventTimes.add(elapsedTimeSinceFirstDown);
            this.mPreviewXCoordinates.add(x);
            this.mPreviewYCoordinates.add(y);
        }
    }

    public void appendPreviewStroke(final ResizableIntArray eventTimes, final ResizableIntArray xCoords, final ResizableIntArray yCoords, final ResizableIntArray types) {
        int length = this.mPreviewEventTimes.getLength();
        int i = this.mLastPreviewSize;
        int i2 = length - i;
        if (i2 <= 0) {
            return;
        }
        eventTimes.append(this.mPreviewEventTimes, i, i2);
        xCoords.append(this.mPreviewXCoordinates, this.mLastPreviewSize, i2);
        yCoords.append(this.mPreviewYCoordinates, this.mLastPreviewSize, i2);
        this.mLastPreviewSize = this.mPreviewEventTimes.getLength();
    }

    public int interpolateStrokeAndReturnStartIndexOfLastSegment(final int lastInterpolatedIndex, final ResizableIntArray eventTimes, final ResizableIntArray xCoords, final ResizableIntArray yCoords, final ResizableIntArray types) {
        int length = this.mPreviewEventTimes.getLength();
        int[] primitiveArray = this.mPreviewEventTimes.getPrimitiveArray();
        int[] primitiveArray2 = this.mPreviewXCoordinates.getPrimitiveArray();
        int[] primitiveArray3 = this.mPreviewYCoordinates.getPrimitiveArray();
        this.mInterpolator.reset(primitiveArray2, primitiveArray3, 0, length);
        int i = lastInterpolatedIndex;
        int i2 = this.mLastInterpolatedPreviewIndex + 1;
        int i3 = i;
        while (i2 < length) {
            int i4 = i2 - 1;
            int i5 = i2 + 1;
            this.mLastInterpolatedPreviewIndex = i4;
            this.mInterpolator.setInterval(i4 - 1, i4, i2, i5);
            int i6 = i;
            int iMin = Math.min(this.mDrawingParams.mMaxInterpolationSegments, Math.max((int) Math.ceil(Math.abs(angularDiff(Math.atan2(this.mInterpolator.mSlope2Y, this.mInterpolator.mSlope2X), Math.atan2(this.mInterpolator.mSlope1Y, this.mInterpolator.mSlope1X))) / this.mDrawingParams.mMaxInterpolationAngularThreshold), (int) Math.ceil(Math.hypot(this.mInterpolator.mP1X - this.mInterpolator.mP2X, this.mInterpolator.mP1Y - this.mInterpolator.mP2Y) / this.mDrawingParams.mMaxInterpolationDistanceThreshold)));
            int i7 = eventTimes.get(i6);
            int i8 = primitiveArray[i2] - primitiveArray[i4];
            int i9 = i6 + 1;
            int i10 = 1;
            while (i10 < iMin) {
                float f = i10 / iMin;
                this.mInterpolator.interpolate(f);
                eventTimes.addAt(i9, ((int) (i8 * f)) + i7);
                xCoords.addAt(i9, (int) this.mInterpolator.mInterpolatedX);
                yCoords.addAt(i9, (int) this.mInterpolator.mInterpolatedY);
                i9++;
                i10++;
                length = length;
            }
            eventTimes.addAt(i9, primitiveArray[i2]);
            xCoords.addAt(i9, primitiveArray2[i2]);
            yCoords.addAt(i9, primitiveArray3[i2]);
            i2 = i5;
            length = length;
            i = i9;
            i3 = i6;
        }
        return i3;
    }
}
