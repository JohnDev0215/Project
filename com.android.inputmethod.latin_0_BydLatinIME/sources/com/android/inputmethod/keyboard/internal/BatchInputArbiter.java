package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.latin.common.InputPointers;

/* JADX INFO: loaded from: classes2.dex */
public class BatchInputArbiter {
    private static long sGestureFirstDownTime;
    private final GestureStrokeRecognitionPoints mRecognitionPoints;
    private static final InputPointers sAggregatedPointers = new InputPointers(128);
    private static int sLastRecognitionPointSize = 0;
    private static long sLastRecognitionTime = 0;

    public interface BatchInputArbiterListener {
        void onEndBatchInput(final InputPointers aggregatedPointers, final long upEventTime);

        void onStartBatchInput();

        void onStartUpdateBatchInputTimer();

        void onUpdateBatchInput(final InputPointers aggregatedPointers, final long moveEventTime);
    }

    public BatchInputArbiter(final int pointerId, final GestureStrokeRecognitionParams params) {
        this.mRecognitionPoints = new GestureStrokeRecognitionPoints(pointerId, params);
    }

    public void setKeyboardGeometry(final int keyWidth, final int keyboardHeight) {
        this.mRecognitionPoints.setKeyboardGeometry(keyWidth, keyboardHeight);
    }

    public int getElapsedTimeSinceFirstDown(final long eventTime) {
        return (int) (eventTime - sGestureFirstDownTime);
    }

    public void addDownEventPoint(final int x, final int y, final long downEventTime, final long lastLetterTypingTime, final int activePointerCount) {
        if (activePointerCount == 1) {
            sGestureFirstDownTime = downEventTime;
        }
        this.mRecognitionPoints.addDownEventPoint(x, y, getElapsedTimeSinceFirstDown(downEventTime), (int) (downEventTime - lastLetterTypingTime));
    }

    public boolean addMoveEventPoint(final int x, final int y, final long moveEventTime, final boolean isMajorEvent, final BatchInputArbiterListener listener) {
        int length = this.mRecognitionPoints.getLength();
        boolean zAddEventPoint = this.mRecognitionPoints.addEventPoint(x, y, getElapsedTimeSinceFirstDown(moveEventTime), isMajorEvent);
        if (this.mRecognitionPoints.getLength() > length) {
            listener.onStartUpdateBatchInputTimer();
        }
        return zAddEventPoint;
    }

    public boolean mayStartBatchInput(final BatchInputArbiterListener listener) {
        if (!this.mRecognitionPoints.isStartOfAGesture()) {
            return false;
        }
        InputPointers inputPointers = sAggregatedPointers;
        synchronized (inputPointers) {
            inputPointers.reset();
            sLastRecognitionPointSize = 0;
            sLastRecognitionTime = 0L;
            listener.onStartBatchInput();
        }
        return true;
    }

    public void updateBatchInputByTimer(final long syntheticMoveEventTime, final BatchInputArbiterListener listener) {
        this.mRecognitionPoints.duplicateLastPointWith(getElapsedTimeSinceFirstDown(syntheticMoveEventTime));
        updateBatchInput(syntheticMoveEventTime, listener);
    }

    public void updateBatchInput(final long moveEventTime, final BatchInputArbiterListener listener) {
        InputPointers inputPointers = sAggregatedPointers;
        synchronized (inputPointers) {
            this.mRecognitionPoints.appendIncrementalBatchPoints(inputPointers);
            if (inputPointers.getPointerSize() > sLastRecognitionPointSize && this.mRecognitionPoints.hasRecognitionTimePast(moveEventTime, sLastRecognitionTime)) {
                listener.onUpdateBatchInput(inputPointers, moveEventTime);
                listener.onStartUpdateBatchInputTimer();
                sLastRecognitionPointSize = inputPointers.getPointerSize();
                sLastRecognitionTime = moveEventTime;
            }
        }
    }

    public boolean mayEndBatchInput(final long upEventTime, final int activePointerCount, final BatchInputArbiterListener listener) {
        InputPointers inputPointers = sAggregatedPointers;
        synchronized (inputPointers) {
            this.mRecognitionPoints.appendAllBatchPoints(inputPointers);
            if (activePointerCount != 1) {
                return false;
            }
            listener.onEndBatchInput(inputPointers, upEventTime);
            return true;
        }
    }
}
