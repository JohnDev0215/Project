package com.android.car.settings.storage;

import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;

/* JADX INFO: loaded from: classes5.dex */
public class StorageUtils {
    private StorageUtils() {
    }

    public static VolumeInfo maybeInitializeVolume(StorageManager sm, Bundle bundle) {
        String volumeId = "private";
        if (bundle != null) {
            volumeId = bundle.getString("android.os.storage.extra.VOLUME_ID", "private");
        }
        VolumeInfo volume = sm.findVolumeById(volumeId);
        if (isVolumeValid(volume)) {
            return volume;
        }
        return null;
    }

    private static boolean isVolumeValid(VolumeInfo volume) {
        return volume != null && volume.getType() == 1 && volume.isMountedReadable();
    }
}
