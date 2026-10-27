package com.android.settingslib.wifi;

import android.content.Context;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.wifi.WifiTracker;

/* JADX INFO: loaded from: classes3.dex */
public class WifiTrackerFactory {
    private static WifiTracker sTestingWifiTracker;

    public static void setTestingWifiTracker(WifiTracker tracker) {
        sTestingWifiTracker = tracker;
    }

    public static WifiTracker create(Context context, WifiTracker.WifiListener wifiListener, Lifecycle lifecycle, boolean includeSaved, boolean includeScans) {
        WifiTracker wifiTracker = sTestingWifiTracker;
        if (wifiTracker != null) {
            return wifiTracker;
        }
        return new WifiTracker(context, wifiListener, lifecycle, includeSaved, includeScans);
    }
}
