package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class StorageApplicationTotalSizePreferenceController extends StorageSizeBasePreferenceController {
    public StorageApplicationTotalSizePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.storage.StorageSizeBasePreferenceController
    protected long getSize() {
        long appSize = getAppStorageStats().getCodeBytes();
        long cacheBytes = 0;
        long userData = isDataCleared() ? 0L : getAppStorageStats().getDataBytes() - getAppStorageStats().getCacheBytes();
        if (!isCachedCleared() && !isDataCleared()) {
            cacheBytes = getAppStorageStats().getCacheBytes();
        }
        long cacheData = cacheBytes;
        return appSize + userData + cacheData;
    }
}
