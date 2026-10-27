package com.android.car.developeroptions.media;

import android.content.Context;
import android.util.Log;
import com.android.car.developeroptions.bluetooth.Utils;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes.dex */
public class MediaOutputIndicatorSlice implements CustomSliceable {
    private LocalBluetoothManager mLocalBluetoothManager;

    public MediaOutputIndicatorSlice(Context context) {
        LocalBluetoothManager localBtManager = Utils.getLocalBtManager(context);
        this.mLocalBluetoothManager = localBtManager;
        if (localBtManager == null) {
            Log.e("MediaOutputIndicatorSlice", "Bluetooth is not supported on this device");
        } else {
            localBtManager.getProfileManager();
        }
    }
}
