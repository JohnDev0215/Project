package com.android.inputmethod.keyboard.internal;

import android.content.res.Resources;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
public final class BogusMoveEventDetector {
    private static final float BOGUS_MOVE_ACCUMULATED_DISTANCE_THRESHOLD = 0.53f;
    private static final float BOGUS_MOVE_RADIUS_THRESHOLD = 1.14f;
    private static final boolean DEBUG_MODE = false;
    private static final String TAG = "BogusMoveEventDetector";
    private static boolean sNeedsProximateBogusDownMoveUpEventHack;
    int mAccumulatedDistanceFromDownKey;
    private int mAccumulatedDistanceThreshold;
    private int mActualDownX;
    private int mActualDownY;
    private int mRadiusThreshold;

    public static void init(final Resources res) {
        int integer = res.getInteger(R.integer.config_screen_metrics);
        boolean z = true;
        boolean z2 = integer == 2;
        boolean z3 = integer == 3;
        boolean z4 = res.getDisplayMetrics().densityDpi < 240;
        if (!z2 && (!z3 || !z4)) {
            z = false;
        }
        sNeedsProximateBogusDownMoveUpEventHack = z;
    }

    public void setKeyboardGeometry(final int keyWidth, final int keyHeight) {
        float fHypot = (float) Math.hypot(keyWidth, keyHeight);
        this.mAccumulatedDistanceThreshold = (int) (BOGUS_MOVE_ACCUMULATED_DISTANCE_THRESHOLD * fHypot);
        this.mRadiusThreshold = (int) (fHypot * BOGUS_MOVE_RADIUS_THRESHOLD);
    }

    public void onActualDownEvent(final int x, final int y) {
        this.mActualDownX = x;
        this.mActualDownY = y;
    }

    public void onDownKey() {
        this.mAccumulatedDistanceFromDownKey = 0;
    }

    public void onMoveKey(final int distance) {
        this.mAccumulatedDistanceFromDownKey += distance;
    }

    public boolean hasTraveledLongDistance(final int x, final int y) {
        return sNeedsProximateBogusDownMoveUpEventHack && Math.abs(x - this.mActualDownX) >= Math.abs(y - this.mActualDownY) && this.mAccumulatedDistanceFromDownKey >= this.mAccumulatedDistanceThreshold;
    }

    public int getAccumulatedDistanceFromDownKey() {
        return this.mAccumulatedDistanceFromDownKey;
    }

    public int getDistanceFromDownEvent(final int x, final int y) {
        return getDistance(x, y, this.mActualDownX, this.mActualDownY);
    }

    private static int getDistance(final int x1, final int y1, final int x2, final int y2) {
        return (int) Math.hypot(x1 - x2, y1 - y2);
    }

    public boolean isCloseToActualDownEvent(final int x, final int y) {
        return sNeedsProximateBogusDownMoveUpEventHack && getDistanceFromDownEvent(x, y) < this.mRadiusThreshold;
    }
}
