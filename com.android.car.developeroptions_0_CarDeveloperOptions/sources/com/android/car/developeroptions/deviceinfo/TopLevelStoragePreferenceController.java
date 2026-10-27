package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.os.storage.StorageManager;
import android.text.format.Formatter;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes.dex */
public class TopLevelStoragePreferenceController extends BasePreferenceController {
    private final StorageManager mStorageManager;
    private final StorageManagerVolumeProvider mStorageManagerVolumeProvider;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TopLevelStoragePreferenceController(Context context, String str) {
        super(context, str);
        StorageManager storageManager = (StorageManager) this.mContext.getSystemService(StorageManager.class);
        this.mStorageManager = storageManager;
        this.mStorageManagerVolumeProvider = new StorageManagerVolumeProvider(storageManager);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        PrivateStorageInfo privateStorageInfo = PrivateStorageInfo.getPrivateStorageInfo(this.mStorageManagerVolumeProvider);
        return this.mContext.getString(R.string.storage_summary, percentInstance.format((r2 - privateStorageInfo.freeBytes) / privateStorageInfo.totalBytes), Formatter.formatFileSize(this.mContext, privateStorageInfo.freeBytes));
    }
}
