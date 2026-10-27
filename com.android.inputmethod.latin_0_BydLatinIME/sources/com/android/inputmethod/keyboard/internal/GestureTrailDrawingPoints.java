package com.android.inputmethod.keyboard.internal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.SystemClock;
import androidx.core.internal.view.SupportMenu;
import com.android.inputmethod.latin.common.ResizableIntArray;

/* JADX INFO: loaded from: classes2.dex */
final class GestureTrailDrawingPoints {
    public static final boolean DEBUG_SHOW_POINTS = false;
    private static final int DEFAULT_CAPACITY = 256;
    private static final int DOWN_EVENT_MARKER = -128;
    public static final int POINT_TYPE_INTERPOLATED = 2;
    public static final int POINT_TYPE_SAMPLED = 1;
    private long mCurrentTimeBase;
    private int mLastInterpolatedDrawIndex;
    private int mTrailStartIndex;
    private final ResizableIntArray mXCoordinates = new ResizableIntArray(256);
    private final ResizableIntArray mYCoordinates = new ResizableIntArray(256);
    private final ResizableIntArray mEventTimes = new ResizableIntArray(256);
    private final ResizableIntArray mPointTypes = new ResizableIntArray(0);
    private int mCurrentStrokeId = -1;
    private final RoundedLine mRoundedLine = new RoundedLine();
    private final Rect mRoundedLineBounds = new Rect();

    private static boolean isDownEventXCoord(final int xCoordOrMark) {
        return xCoordOrMark <= DOWN_EVENT_MARKER;
    }

    private static int markAsDownEvent(final int xCoord) {
        return (-128) - xCoord;
    }

    GestureTrailDrawingPoints() {
    }

    private static int getXCoordValue(final int xCoordOrMark) {
        return isDownEventXCoord(xCoordOrMark) ? (-128) - xCoordOrMark : xCoordOrMark;
    }

    public void addStroke(final GestureStrokeDrawingPoints stroke, final long downTime) {
        synchronized (this.mEventTimes) {
            addStrokeLocked(stroke, downTime);
        }
    }

    private void addStrokeLocked(final GestureStrokeDrawingPoints stroke, final long downTime) {
        int length = this.mEventTimes.getLength();
        stroke.appendPreviewStroke(this.mEventTimes, this.mXCoordinates, this.mYCoordinates, this.mPointTypes);
        if (this.mEventTimes.getLength() == length) {
            return;
        }
        int[] primitiveArray = this.mEventTimes.getPrimitiveArray();
        int gestureStrokeId = stroke.getGestureStrokeId();
        this.mLastInterpolatedDrawIndex = stroke.interpolateStrokeAndReturnStartIndexOfLastSegment(gestureStrokeId == this.mCurrentStrokeId ? this.mLastInterpolatedDrawIndex : length, this.mEventTimes, this.mXCoordinates, this.mYCoordinates, this.mPointTypes);
        if (gestureStrokeId != this.mCurrentStrokeId) {
            int i = (int) (downTime - this.mCurrentTimeBase);
            for (int i2 = this.mTrailStartIndex; i2 < length; i2++) {
                primitiveArray[i2] = primitiveArray[i2] - i;
            }
            int[] primitiveArray2 = this.mXCoordinates.getPrimitiveArray();
            primitiveArray2[length] = markAsDownEvent(primitiveArray2[length]);
            this.mCurrentTimeBase = downTime - ((long) primitiveArray[length]);
            this.mCurrentStrokeId = gestureStrokeId;
        }
    }

    private static int getAlpha(final int elapsedTime, final GestureTrailDrawingParams params) {
        if (elapsedTime < params.mFadeoutStartDelay) {
            return 255;
        }
        return 255 - (((elapsedTime - params.mFadeoutStartDelay) * 255) / params.mFadeoutDuration);
    }

    private static float getWidth(final int elapsedTime, final GestureTrailDrawingParams params) {
        return params.mTrailStartWidth - (((params.mTrailStartWidth - params.mTrailEndWidth) * elapsedTime) / params.mTrailLingerDuration);
    }

    public boolean drawGestureTrail(final Canvas canvas, final Paint paint, final Rect outBoundsRect, final GestureTrailDrawingParams params) {
        boolean zDrawGestureTrailLocked;
        synchronized (this.mEventTimes) {
            zDrawGestureTrailLocked = drawGestureTrailLocked(canvas, paint, outBoundsRect, params);
        }
        return zDrawGestureTrailLocked;
    }

    private boolean drawGestureTrailLocked(final Canvas canvas, final Paint paint, final Rect outBoundsRect, final GestureTrailDrawingParams params) {
        int i;
        int i2;
        int i3;
        outBoundsRect.setEmpty();
        int length = this.mEventTimes.getLength();
        if (length == 0) {
            return false;
        }
        int[] primitiveArray = this.mEventTimes.getPrimitiveArray();
        int[] primitiveArray2 = this.mXCoordinates.getPrimitiveArray();
        int[] primitiveArray3 = this.mYCoordinates.getPrimitiveArray();
        this.mPointTypes.getPrimitiveArray();
        int iUptimeMillis = (int) (SystemClock.uptimeMillis() - this.mCurrentTimeBase);
        int i4 = this.mTrailStartIndex;
        while (i4 < length && iUptimeMillis - primitiveArray[i4] >= params.mTrailLingerDuration) {
            i4++;
        }
        this.mTrailStartIndex = i4;
        if (i4 < length) {
            paint.setColor(params.mTrailColor);
            paint.setStyle(Paint.Style.FILL);
            RoundedLine roundedLine = this.mRoundedLine;
            int xCoordValue = getXCoordValue(primitiveArray2[i4]);
            int i5 = primitiveArray3[i4];
            float width = getWidth(iUptimeMillis - primitiveArray[i4], params) / 2.0f;
            int i6 = i4 + 1;
            while (i6 < length) {
                int i7 = iUptimeMillis - primitiveArray[i6];
                int xCoordValue2 = getXCoordValue(primitiveArray2[i6]);
                int i8 = iUptimeMillis;
                int i9 = primitiveArray3[i6];
                float width2 = getWidth(i7, params) / 2.0f;
                if (isDownEventXCoord(primitiveArray2[i6])) {
                    i = xCoordValue2;
                    i2 = i9;
                    i3 = i6;
                } else {
                    i = xCoordValue2;
                    i2 = i9;
                    i3 = i6;
                    Path pathMakePath = roundedLine.makePath(xCoordValue, i5, params.mTrailBodyRatio * width, xCoordValue2, i9, width2 * params.mTrailBodyRatio);
                    if (!pathMakePath.isEmpty()) {
                        roundedLine.getBounds(this.mRoundedLineBounds);
                        if (params.mTrailShadowEnabled) {
                            float f = params.mTrailShadowRatio * width2;
                            paint.setShadowLayer(f, 0.0f, 0.0f, params.mTrailColor);
                            int i10 = -((int) Math.ceil(f));
                            this.mRoundedLineBounds.inset(i10, i10);
                        }
                        outBoundsRect.union(this.mRoundedLineBounds);
                        paint.setAlpha(getAlpha(i7, params));
                        canvas.drawPath(pathMakePath, paint);
                    }
                }
                i6 = i3 + 1;
                iUptimeMillis = i8;
                width = width2;
                xCoordValue = i;
                i5 = i2;
            }
        }
        int i11 = length - i4;
        if (i11 < i4) {
            this.mTrailStartIndex = 0;
            if (i11 > 0) {
                System.arraycopy(primitiveArray, i4, primitiveArray, 0, i11);
                System.arraycopy(primitiveArray2, i4, primitiveArray2, 0, i11);
                System.arraycopy(primitiveArray3, i4, primitiveArray3, 0, i11);
            }
            this.mEventTimes.setLength(i11);
            this.mXCoordinates.setLength(i11);
            this.mYCoordinates.setLength(i11);
            this.mLastInterpolatedDrawIndex = Math.max(this.mLastInterpolatedDrawIndex - i4, 0);
        }
        return i11 > 0;
    }

    private void debugDrawPoints(final Canvas canvas, final int startIndex, final int endIndex, final Paint paint) {
        int[] primitiveArray = this.mXCoordinates.getPrimitiveArray();
        int[] primitiveArray2 = this.mYCoordinates.getPrimitiveArray();
        int[] primitiveArray3 = this.mPointTypes.getPrimitiveArray();
        paint.setAntiAlias(false);
        paint.setStrokeWidth(0.0f);
        while (startIndex < endIndex) {
            int i = primitiveArray3[startIndex];
            if (i == 2) {
                paint.setColor(SupportMenu.CATEGORY_MASK);
            } else if (i == 1) {
                paint.setColor(-6291201);
            } else {
                paint.setColor(-16711936);
            }
            canvas.drawPoint(getXCoordValue(primitiveArray[startIndex]), primitiveArray2[startIndex], paint);
            startIndex++;
        }
        paint.setAntiAlias(true);
    }
}
