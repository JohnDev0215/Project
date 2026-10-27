package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class PhoneStatusBarView extends FrameLayout {
    private static final boolean DEBUG = false;
    private static final String PANEL_BAR_SUPER_PARCELABLE = "panel_bar_super_parcelable";
    private static final String STATE = "state";
    public static final int STATE_CLOSED = 0;
    public static final int STATE_OPEN = 2;
    public static final int STATE_OPENING = 1;
    private static final String TAG = "PhoneStatusBarView";
    StatusBar mBar;
    private boolean mExpanded;
    private int mState;

    public static void LOG(String fmt, Object... args) {
    }

    public void onBootCompleted() {
    }

    public PhoneStatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mState = 0;
    }

    public void setBar(StatusBar bar) {
        this.mBar = bar;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        updateResources();
    }

    public void go(int state) {
        this.mState = state;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PANEL_BAR_SUPER_PARCELABLE, super.onSaveInstanceState());
        bundle.putInt(STATE, this.mState);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !(state instanceof Bundle)) {
            super.onRestoreInstanceState(state);
            return;
        }
        Bundle bundle = (Bundle) state;
        super.onRestoreInstanceState(bundle.getParcelable(PANEL_BAR_SUPER_PARCELABLE));
        if (bundle.containsKey(STATE)) {
            go(bundle.getInt(STATE, 0));
        }
    }

    public boolean onRequestSendAccessibilityEventInternal(View child, AccessibilityEvent event) {
        Log.d(TAG, "onRequestSendAccessibilityEventInternal : " + child.getId());
        if (!super.onRequestSendAccessibilityEventInternal(child, event)) {
            return false;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        onInitializeAccessibilityEvent(accessibilityEventObtain);
        dispatchPopulateAccessibilityEvent(accessibilityEventObtain);
        event.appendRecord(accessibilityEventObtain);
        return true;
    }

    public void updateResources() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.status_bar_height);
            setLayoutParams(layoutParams);
        }
    }
}
