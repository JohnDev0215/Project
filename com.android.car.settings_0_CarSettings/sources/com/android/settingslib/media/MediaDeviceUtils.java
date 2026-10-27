package com.android.settingslib.media;

import android.bluetooth.BluetoothDevice;
import android.media.MediaRoute2Info;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes6.dex */
public class MediaDeviceUtils {
    public static String getId(CachedBluetoothDevice cachedDevice) {
        if (cachedDevice.isHearingAidDevice()) {
            return Long.toString(cachedDevice.getHiSyncId());
        }
        return cachedDevice.getAddress();
    }

    public static String getId(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice.getAddress();
    }

    public static String getId(MediaRoute2Info route) {
        return route.getId();
    }
}
