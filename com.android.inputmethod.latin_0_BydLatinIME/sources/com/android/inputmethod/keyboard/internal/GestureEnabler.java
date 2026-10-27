package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.accessibility.AccessibilityUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class GestureEnabler {
    private boolean mGestureHandlingEnabledByInputField;
    private boolean mGestureHandlingEnabledByUser;
    private boolean mMainDictionaryAvailable;
    private boolean mShouldHandleGesture;

    private void updateGestureHandlingMode() {
        this.mShouldHandleGesture = this.mMainDictionaryAvailable && this.mGestureHandlingEnabledByInputField && this.mGestureHandlingEnabledByUser && !AccessibilityUtils.getInstance().isTouchExplorationEnabled();
    }

    public void setMainDictionaryAvailability(final boolean mainDictionaryAvailable) {
        this.mMainDictionaryAvailable = mainDictionaryAvailable;
        updateGestureHandlingMode();
    }

    public void setGestureHandlingEnabledByUser(final boolean gestureHandlingEnabledByUser) {
        this.mGestureHandlingEnabledByUser = gestureHandlingEnabledByUser;
        updateGestureHandlingMode();
    }

    public void setPasswordMode(final boolean passwordMode) {
        this.mGestureHandlingEnabledByInputField = !passwordMode;
        updateGestureHandlingMode();
    }

    public boolean shouldHandleGesture() {
        return this.mShouldHandleGesture;
    }
}
