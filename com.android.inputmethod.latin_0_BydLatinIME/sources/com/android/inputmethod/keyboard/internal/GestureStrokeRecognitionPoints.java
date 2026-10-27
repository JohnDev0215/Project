package com.android.inputmethod.keyboard.internal;

import android.util.Log;
import com.android.inputmethod.latin.common.InputPointers;
import com.android.inputmethod.latin.common.ResizableIntArray;

/* JADX INFO: loaded from: classes2.dex */
public final class GestureStrokeRecognitionPoints {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_SPEED = false;
    public static final float EXTRA_GESTURE_TRAIL_AREA_ABOVE_KEYBOARD_RATIO = 0.25f;
    private static final int MSEC_PER_SEC = 1000;
    private static final String TAG = "GestureStrokeRecognitionPoints";
    private boolean mAfterFastTyping;
    private int mDetectFastMoveSpeedThreshold;
    private int mDetectFastMoveTime;
    private int mDetectFastMoveX;
    private int mDetectFastMoveY;
    private int mGestureDynamicDistanceThresholdFrom;
    private int mGestureDynamicDistanceThresholdTo;
    private int mGestureRecognitionSpeedThreshold;
    private int mGestureSamplingMinimumDistance;
    private int mIncrementalRecognitionSize;
    private int mKeyWidth;
    private int mLastIncrementalBatchSize;
    private long mLastMajorEventTime;
    private int mLastMajorEventX;
    private int mLastMajorEventY;
    private int mMaxYCoordinate;
    private int mMinYCoordinate;
    private final int mPointerId;
    private final GestureStrokeRecognitionParams mRecognitionParams;
    private final ResizableIntArray mEventTimes = new ResizableIntArray(128);
    private final ResizableIntArray mXCoordinates = new ResizableIntArray(128);
    private final ResizableIntArray mYCoordinates = new ResizableIntArray(128);

    public GestureStrokeRecognitionPoints(final int pointerId, final GestureStrokeRecognitionParams recognitionParams) {
        this.mPointerId = pointerId;
        this.mRecognitionParams = recognitionParams;
    }

    public void setKeyboardGeometry(final int keyWidth, final int keyboardHeight) {
        this.mKeyWidth = keyWidth;
        this.mMinYCoordinate = -((int) (keyboardHeight * 0.25f));
        this.mMaxYCoordinate = keyboardHeight;
        float f = keyWidth;
        this.mDetectFastMoveSpeedThreshold = (int) (this.mRecognitionParams.mDetectFastMoveSpeedThreshold * f);
        this.mGestureDynamicDistanceThresholdFrom = (int) (this.mRecognitionParams.mDynamicDistanceThresholdFrom * f);
        this.mGestureDynamicDistanceThresholdTo = (int) (this.mRecognitionParams.mDynamicDistanceThresholdTo * f);
        this.mGestureSamplingMinimumDistance = (int) (this.mRecognitionParams.mSamplingMinimumDistance * f);
        this.mGestureRecognitionSpeedThreshold = (int) (f * this.mRecognitionParams.mRecognitionSpeedThreshold);
    }

    public int getLength() {
        return this.mEventTimes.getLength();
    }

    public void addDownEventPoint(final int x, final int y, final int elapsedTimeSinceFirstDown, final int elapsedTimeSinceLastTyping) {
        reset();
        if (elapsedTimeSinceLastTyping < this.mRecognitionParams.mStaticTimeThresholdAfterFastTyping) {
            this.mAfterFastTyping = true;
        }
        addEventPoint(x, y, elapsedTimeSinceFirstDown, true);
    }

    private int getGestureDynamicDistanceThreshold(final int deltaTime) {
        if (!this.mAfterFastTyping || deltaTime >= this.mRecognitionParams.mDynamicThresholdDecayDuration) {
            return this.mGestureDynamicDistanceThresholdTo;
        }
        return this.mGestureDynamicDistanceThresholdFrom - (((this.mGestureDynamicDistanceThresholdFrom - this.mGestureDynamicDistanceThresholdTo) * deltaTime) / this.mRecognitionParams.mDynamicThresholdDecayDuration);
    }

    private int getGestureDynamicTimeThreshold(final int deltaTime) {
        if (!this.mAfterFastTyping || deltaTime >= this.mRecognitionParams.mDynamicThresholdDecayDuration) {
            return this.mRecognitionParams.mDynamicTimeThresholdTo;
        }
        return this.mRecognitionParams.mDynamicTimeThresholdFrom - (((this.mRecognitionParams.mDynamicTimeThresholdFrom - this.mRecognitionParams.mDynamicTimeThresholdTo) * deltaTime) / this.mRecognitionParams.mDynamicThresholdDecayDuration);
    }

    public final boolean isStartOfAGesture() {
        int length;
        if (!hasDetectedFastMove() || (length = getLength()) <= 0) {
            return false;
        }
        int i = length - 1;
        int i2 = this.mEventTimes.get(i) - this.mDetectFastMoveTime;
        if (i2 < 0) {
            return false;
        }
        return i2 >= getGestureDynamicTimeThreshold(i2) && getDistance(this.mXCoordinates.get(i), this.mYCoordinates.get(i), this.mDetectFastMoveX, this.mDetectFastMoveY) >= getGestureDynamicDistanceThreshold(i2);
    }

    public void duplicateLastPointWith(final int time) {
        int length = getLength() - 1;
        if (length >= 0) {
            int i = this.mXCoordinates.get(length);
            int i2 = this.mYCoordinates.get(length);
            appendPoint(i, i2, time);
            updateIncrementalRecognitionSize(i, i2, time);
        }
    }

    private void reset() {
        this.mIncrementalRecognitionSize = 0;
        this.mLastIncrementalBatchSize = 0;
        this.mEventTimes.setLength(0);
        this.mXCoordinates.setLength(0);
        this.mYCoordinates.setLength(0);
        this.mLastMajorEventTime = 0L;
        this.mDetectFastMoveTime = 0;
        this.mAfterFastTyping = false;
    }

    private void appendPoint(final int x, final int y, final int time) {
        int length = getLength() - 1;
        if (length >= 0 && this.mEventTimes.get(length) > time) {
            Log.w(TAG, String.format("[%d] drop stale event: %d,%d|%d last: %d,%d|%d", Integer.valueOf(this.mPointerId), Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(time), Integer.valueOf(this.mXCoordinates.get(length)), Integer.valueOf(this.mYCoordinates.get(length)), Integer.valueOf(this.mEventTimes.get(length))));
            return;
        }
        this.mEventTimes.add(time);
        this.mXCoordinates.add(x);
        this.mYCoordinates.add(y);
    }

    private void updateMajorEvent(final int x, final int y, final int time) {
        this.mLastMajorEventTime = time;
        this.mLastMajorEventX = x;
        this.mLastMajorEventY = y;
    }

    private final boolean hasDetectedFastMove() {
        return this.mDetectFastMoveTime > 0;
    }

    private int detectFastMove(final int x, final int y, final int time) {
        int length = getLength() - 1;
        int i = this.mXCoordinates.get(length);
        int i2 = this.mYCoordinates.get(length);
        int distance = getDistance(i, i2, x, y);
        int i3 = time - this.mEventTimes.get(length);
        if (i3 > 0) {
            int distance2 = getDistance(i, i2, x, y) * 1000;
            if (!hasDetectedFastMove() && distance2 > this.mDetectFastMoveSpeedThreshold * i3) {
                this.mDetectFastMoveTime = time;
                this.mDetectFastMoveX = x;
                this.mDetectFastMoveY = y;
            }
        }
        return distance;
    }

    public boolean addEventPoint(final int x, final int y, final int time, final boolean isMajorEvent) {
        if (getLength() <= 0) {
            appendPoint(x, y, time);
            updateMajorEvent(x, y, time);
        } else if (detectFastMove(x, y, time) > this.mGestureSamplingMinimumDistance) {
            appendPoint(x, y, time);
        }
        if (isMajorEvent) {
            updateIncrementalRecognitionSize(x, y, time);
            updateMajorEvent(x, y, time);
        }
        return y >= this.mMinYCoordinate && y < this.mMaxYCoordinate;
    }

    private void updateIncrementalRecognitionSize(final int x, final int y, final int time) {
        int i = (int) (((long) time) - this.mLastMajorEventTime);
        if (i > 0 && getDistance(this.mLastMajorEventX, this.mLastMajorEventY, x, y) * 1000 < this.mGestureRecognitionSpeedThreshold * i) {
            this.mIncrementalRecognitionSize = getLength();
        }
    }

    public final boolean hasRecognitionTimePast(final long currentTime, final long lastRecognitionTime) {
        return currentTime > lastRecognitionTime + ((long) this.mRecognitionParams.mRecognitionMinimumTime);
    }

    public final void appendAllBatchPoints(final InputPointers out) {
        appendBatchPoints(out, getLength());
    }

    public final void appendIncrementalBatchPoints(final InputPointers out) {
        appendBatchPoints(out, this.mIncrementalRecognitionSize);
    }

    private void appendBatchPoints(final InputPointers out, final int size) {
        int i = this.mLastIncrementalBatchSize;
        int i2 = size - i;
        if (i2 <= 0) {
            return;
        }
        out.append(this.mPointerId, this.mEventTimes, this.mXCoordinates, this.mYCoordinates, i, i2);
        this.mLastIncrementalBatchSize = size;
    }

    private static int getDistance(final int x1, final int y1, final int x2, final int y2) {
        return (int) Math.hypot(x1 - x2, y1 - y2);
    }
}
