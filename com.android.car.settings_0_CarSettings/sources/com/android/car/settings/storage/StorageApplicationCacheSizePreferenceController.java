package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class StorageApplicationCacheSizePreferenceController extends StorageSizeBasePreferenceController {
    public StorageApplicationCacheSizePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.storage.StorageSizeBasePreferenceController
    protected long getSize() {
        if (isCachedCleared() || isDataCleared()) {
            return 0L;
        }
        return getAppStorageStats().getCacheBytes();
    }
}
