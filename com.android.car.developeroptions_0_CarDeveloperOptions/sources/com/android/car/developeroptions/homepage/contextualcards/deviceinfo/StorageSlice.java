package com.android.car.developeroptions.homepage.contextualcards.deviceinfo;

import android.content.Context;
import android.content.Intent;
import android.os.storage.StorageManager;
import android.text.format.Formatter;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.deviceinfo.storage.StorageSummaryDonutPreferenceController;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;

/* JADX INFO: loaded from: classes.dex */
public class StorageSlice implements CustomSliceable {
    private final Context mContext;

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
    }

    public StorageSlice(Context context) {
        this.mContext = context;
    }

    @VisibleForTesting
    PrivateStorageInfo getPrivateStorageInfo() {
        return PrivateStorageInfo.getPrivateStorageInfo(new StorageManagerVolumeProvider((StorageManager) this.mContext.getSystemService(StorageManager.class)));
    }

    @VisibleForTesting
    CharSequence getStorageUsedText(PrivateStorageInfo privateStorageInfo) {
        return StorageSummaryDonutPreferenceController.convertUsedBytesToFormattedText(this.mContext, privateStorageInfo.totalBytes - privateStorageInfo.freeBytes);
    }

    @VisibleForTesting
    CharSequence getStorageSummaryText(PrivateStorageInfo privateStorageInfo) {
        Context context = this.mContext;
        return context.getString(R.string.storage_volume_total, Formatter.formatShortFileSize(context, privateStorageInfo.totalBytes));
    }
}
