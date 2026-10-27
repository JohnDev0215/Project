package com.android.car.developeroptions.connecteddevice.dock;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface DockUpdater {
    default void forceUpdate() {
    }

    default void registerCallback() {
    }

    default void setPreferenceContext(Context context) {
    }

    default void unregisterCallback() {
    }
}
