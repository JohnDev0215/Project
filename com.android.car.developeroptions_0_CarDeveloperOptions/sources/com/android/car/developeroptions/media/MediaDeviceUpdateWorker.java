package com.android.car.developeroptions.media;

import android.content.Context;
import android.net.Uri;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.media.LocalMediaManager;
import com.android.settingslib.media.MediaDevice;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaDeviceUpdateWorker extends SliceBackgroundWorker implements LocalMediaManager.DeviceCallback {
    LocalMediaManager mLocalMediaManager;
    private final List<MediaDevice> mMediaDevices;

    public void setPackageName(String str) {
    }

    public MediaDeviceUpdateWorker(Context context, Uri uri) {
        super(context, uri);
        this.mMediaDevices = new ArrayList();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mLocalMediaManager = null;
    }

    public void connectDevice(MediaDevice mediaDevice) {
        this.mLocalMediaManager.connectDevice(mediaDevice);
    }

    public MediaDevice getMediaDeviceById(String str) {
        return this.mLocalMediaManager.getMediaDeviceById(this.mMediaDevices, str);
    }
}
