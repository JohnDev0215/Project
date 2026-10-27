package com.android.car.developeroptions.media;

import android.content.Context;
import android.net.Uri;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class MediaOutputIndicatorWorker extends SliceBackgroundWorker implements BluetoothCallback {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public MediaOutputIndicatorWorker(Context context, Uri uri) {
        super(context, uri);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onBluetoothStateChanged(int i) {
        notifySliceChange();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        if (i == 2) {
            notifySliceChange();
        }
    }
}
