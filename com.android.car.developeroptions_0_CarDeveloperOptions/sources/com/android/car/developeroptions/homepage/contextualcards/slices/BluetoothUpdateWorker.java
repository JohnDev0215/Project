package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.content.Context;
import android.net.Uri;
import com.android.car.developeroptions.bluetooth.Utils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothUpdateWorker extends SliceBackgroundWorker implements BluetoothCallback {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public BluetoothUpdateWorker(Context context, Uri uri) {
        super(context, uri);
        Utils.getLocalBtManager(context);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        notifySliceChange();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        notifySliceChange();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onBluetoothStateChanged(int i) {
        notifySliceChange();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        notifySliceChange();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
        notifySliceChange();
    }
}
