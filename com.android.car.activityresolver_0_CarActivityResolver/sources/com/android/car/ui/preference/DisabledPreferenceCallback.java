package com.android.car.ui.preference;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface DisabledPreferenceCallback extends UxRestrictablePreference {
    @Deprecated
    default void setMessageToShowWhenDisabledPreferenceClicked(String message) {
        Log.w("carui", "setMessageToShowWhenDisabledPreferenceClicked is deprecated, and does nothing!");
    }
}
