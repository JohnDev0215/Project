package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

/* JADX INFO: loaded from: classes.dex */
public interface HotspotController extends CallbackController<Callback>, Dumpable {

    public interface Callback {
        default void onHotspotAvailabilityChanged(boolean available) {
        }

        void onHotspotChanged(boolean enabled, int numDevices);
    }

    int getNumConnectedDevices();

    boolean isHotspotEnabled();

    boolean isHotspotSupported();

    boolean isHotspotTransient();

    void setHotspotEnabled(boolean enabled);
}
