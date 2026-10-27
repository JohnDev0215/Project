package com.android.car.developeroptions.overlay;

import android.content.Context;
import com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback;
import com.android.car.developeroptions.connecteddevice.dock.DockUpdater;

/* JADX INFO: loaded from: classes.dex */
public interface DockUpdaterFeatureProvider {
    DockUpdater getConnectedDockUpdater(Context context, DevicePreferenceCallback devicePreferenceCallback);

    DockUpdater getSavedDockUpdater(Context context, DevicePreferenceCallback devicePreferenceCallback);
}
