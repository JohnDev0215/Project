package com.android.car.developeroptions.connecteddevice.dock;

import android.content.Context;
import com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback;
import com.android.car.developeroptions.overlay.DockUpdaterFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public class DockUpdaterFeatureProviderImpl implements DockUpdaterFeatureProvider {
    @Override // com.android.car.developeroptions.overlay.DockUpdaterFeatureProvider
    public DockUpdater getConnectedDockUpdater(Context context, DevicePreferenceCallback devicePreferenceCallback) {
        return new DockUpdater(this) { // from class: com.android.car.developeroptions.connecteddevice.dock.DockUpdaterFeatureProviderImpl.1
        };
    }

    @Override // com.android.car.developeroptions.overlay.DockUpdaterFeatureProvider
    public DockUpdater getSavedDockUpdater(Context context, DevicePreferenceCallback devicePreferenceCallback) {
        return new DockUpdater(this) { // from class: com.android.car.developeroptions.connecteddevice.dock.DockUpdaterFeatureProviderImpl.2
        };
    }
}
