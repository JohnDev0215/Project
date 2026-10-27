package com.android.inputmethod.keyboard.internal;

import android.util.Log;
import android.view.MotionEvent;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyDetector;
import com.android.inputmethod.keyboard.PointerTracker;
import com.android.inputmethod.latin.common.CoordinateUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class NonDistinctMultitouchHelper {
    private static final int MAIN_POINTER_TRACKER_ID = 0;
    private static final String TAG = "NonDistinctMultitouchHelper";
    private Key mOldKey;
    private int mOldPointerCount = 1;
    private int[] mLastCoords = CoordinateUtils.newInstance();

    public void processMotionEvent(final MotionEvent me, final KeyDetector keyDetector) {
        int pointerCount = me.getPointerCount();
        int i = this.mOldPointerCount;
        this.mOldPointerCount = pointerCount;
        if (pointerCount <= 1 || i <= 1) {
            PointerTracker pointerTracker = PointerTracker.getPointerTracker(0);
            int actionMasked = me.getActionMasked();
            int actionIndex = me.getActionIndex();
            long eventTime = me.getEventTime();
            long downTime = me.getDownTime();
            if (i == 1 && pointerCount == 1) {
                if (me.getPointerId(actionIndex) == pointerTracker.mPointerId) {
                    pointerTracker.processMotionEvent(me, keyDetector);
                    return;
                } else {
                    injectMotionEvent(actionMasked, me.getX(actionIndex), me.getY(actionIndex), downTime, eventTime, pointerTracker, keyDetector);
                    return;
                }
            }
            if (i == 1 && pointerCount == 2) {
                pointerTracker.getLastCoordinates(this.mLastCoords);
                int iX = CoordinateUtils.x(this.mLastCoords);
                int iY = CoordinateUtils.y(this.mLastCoords);
                this.mOldKey = pointerTracker.getKeyOn(iX, iY);
                injectMotionEvent(1, iX, iY, downTime, eventTime, pointerTracker, keyDetector);
                return;
            }
            if (i == 2 && pointerCount == 1) {
                int x = (int) me.getX(actionIndex);
                int y = (int) me.getY(actionIndex);
                if (this.mOldKey != pointerTracker.getKeyOn(x, y)) {
                    float f = x;
                    float f2 = y;
                    injectMotionEvent(0, f, f2, downTime, eventTime, pointerTracker, keyDetector);
                    if (actionMasked == 1) {
                        injectMotionEvent(1, f, f2, downTime, eventTime, pointerTracker, keyDetector);
                        return;
                    }
                    return;
                }
                return;
            }
            Log.w(TAG, "Unknown touch panel behavior: pointer count is " + pointerCount + " (previously " + i + ")");
        }
    }

    private static void injectMotionEvent(final int action, final float x, final float y, final long downTime, final long eventTime, final PointerTracker tracker, final KeyDetector keyDetector) {
        MotionEvent motionEventObtain = MotionEvent.obtain(downTime, eventTime, action, x, y, 0);
        try {
            tracker.processMotionEvent(motionEventObtain, keyDetector);
        } finally {
            motionEventObtain.recycle();
        }
    }
}
