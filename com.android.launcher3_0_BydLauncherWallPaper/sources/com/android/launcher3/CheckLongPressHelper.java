package com.android.launcher3;

import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes4.dex */
public class CheckLongPressHelper {
    public static final float DEFAULT_LONG_PRESS_TIMEOUT_FACTOR = 0.75f;
    boolean mHasPerformedLongPress;
    View.OnLongClickListener mListener;
    private float mLongPressTimeoutFactor = 0.75f;
    private CheckForLongPress mPendingCheckForLongPress;
    View mView;

    class CheckForLongPress implements Runnable {
        CheckForLongPress() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean zPerformLongClick;
            if (CheckLongPressHelper.this.mView.getParent() == null || !CheckLongPressHelper.this.mView.hasWindowFocus() || CheckLongPressHelper.this.mHasPerformedLongPress) {
                return;
            }
            if (CheckLongPressHelper.this.mListener != null) {
                zPerformLongClick = CheckLongPressHelper.this.mListener.onLongClick(CheckLongPressHelper.this.mView);
            } else {
                zPerformLongClick = CheckLongPressHelper.this.mView.performLongClick();
            }
            if (zPerformLongClick) {
                CheckLongPressHelper.this.mView.setPressed(false);
                CheckLongPressHelper.this.mHasPerformedLongPress = true;
            }
        }
    }

    public CheckLongPressHelper(View v) {
        this.mView = v;
    }

    public CheckLongPressHelper(View v, View.OnLongClickListener listener) {
        this.mView = v;
        this.mListener = listener;
    }

    public void setLongPressTimeoutFactor(float longPressTimeoutFactor) {
        this.mLongPressTimeoutFactor = longPressTimeoutFactor;
    }

    public void postCheckForLongPress() {
        this.mHasPerformedLongPress = false;
        if (this.mPendingCheckForLongPress == null) {
            this.mPendingCheckForLongPress = new CheckForLongPress();
        }
        this.mView.postDelayed(this.mPendingCheckForLongPress, (long) (ViewConfiguration.getLongPressTimeout() * this.mLongPressTimeoutFactor));
    }

    public void cancelLongPress() {
        this.mHasPerformedLongPress = false;
        CheckForLongPress checkForLongPress = this.mPendingCheckForLongPress;
        if (checkForLongPress != null) {
            this.mView.removeCallbacks(checkForLongPress);
            this.mPendingCheckForLongPress = null;
        }
    }

    public boolean hasPerformedLongPress() {
        return this.mHasPerformedLongPress;
    }
}
