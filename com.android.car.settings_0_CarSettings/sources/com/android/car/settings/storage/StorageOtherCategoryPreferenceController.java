package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.UserHandle;
import android.util.SparseArray;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.storage.StorageAsyncLoader;

/* JADX INFO: loaded from: classes5.dex */
public class StorageOtherCategoryPreferenceController extends StorageUsageBasePreferenceController {
    public StorageOtherCategoryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.storage.StorageUsageBasePreferenceController
    protected long calculateCategoryUsage(SparseArray<StorageAsyncLoader.AppsStorageResult> result, long usedSizeBytes) {
        StorageAsyncLoader.AppsStorageResult data = result.get(UserHandle.myUserId());
        return data.getOtherAppsSize();
    }
}
