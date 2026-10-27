package com.android.car.developeroptions.connecteddevice;

import androidx.preference.Preference;

/* JADX INFO: loaded from: classes.dex */
public interface DevicePreferenceCallback {
    void onDeviceAdded(Preference preference);

    void onDeviceRemoved(Preference preference);
}
