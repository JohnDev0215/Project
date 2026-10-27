package com.android.car.settings.storage;

import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.os.storage.VolumeInfo;
import com.android.car.settings.common.AsyncLoader;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageVolumeProvider;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class VolumeSizesLoader extends AsyncLoader<PrivateStorageInfo> {
    private final StorageStatsManager mStats;
    private final VolumeInfo mVolume;
    private final StorageVolumeProvider mVolumeProvider;

    public VolumeSizesLoader(Context context, StorageVolumeProvider volumeProvider, StorageStatsManager stats, VolumeInfo volume) {
        super(context);
        this.mVolumeProvider = volumeProvider;
        this.mStats = stats;
        this.mVolume = volume;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public PrivateStorageInfo loadInBackground() {
        try {
            PrivateStorageInfo volumeSizes = getVolumeSize();
            return volumeSizes;
        } catch (IOException e) {
            return null;
        }
    }

    private PrivateStorageInfo getVolumeSize() throws IOException {
        long privateTotalBytes = this.mVolumeProvider.getTotalBytes(this.mStats, this.mVolume);
        long privateFreeBytes = this.mVolumeProvider.getFreeBytes(this.mStats, this.mVolume);
        return new PrivateStorageInfo(privateFreeBytes, privateTotalBytes);
    }
}
