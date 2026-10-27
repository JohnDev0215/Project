package com.android.car.developeroptions.media;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.media.MediaDevice;

/* JADX INFO: loaded from: classes.dex */
public class MediaOutputSlice implements CustomSliceable {
    private String mPackageName = getUri().getQueryParameter("media_package_name");
    private MediaDeviceUpdateWorker mWorker;

    public MediaOutputSlice(Context context) {
    }

    void init(String str, MediaDeviceUpdateWorker mediaDeviceUpdateWorker) {
        this.mPackageName = str;
        this.mWorker = mediaDeviceUpdateWorker;
    }

    private MediaDeviceUpdateWorker getWorker() {
        if (this.mWorker == null) {
            MediaDeviceUpdateWorker mediaDeviceUpdateWorker = (MediaDeviceUpdateWorker) SliceBackgroundWorker.getInstance(getUri());
            this.mWorker = mediaDeviceUpdateWorker;
            if (mediaDeviceUpdateWorker != null) {
                mediaDeviceUpdateWorker.setPackageName(this.mPackageName);
            }
        }
        return this.mWorker;
    }

    public Uri getUri() {
        return CustomSliceRegistry.MEDIA_OUTPUT_SLICE_URI;
    }

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
        MediaDeviceUpdateWorker worker = getWorker();
        MediaDevice mediaDeviceById = worker.getMediaDeviceById(intent != null ? intent.getStringExtra("media_device_id") : "");
        if (mediaDeviceById != null) {
            Log.d("MediaOutputSlice", "onNotifyChange() device name : " + mediaDeviceById.getName());
            worker.connectDevice(mediaDeviceById);
        }
    }
}
