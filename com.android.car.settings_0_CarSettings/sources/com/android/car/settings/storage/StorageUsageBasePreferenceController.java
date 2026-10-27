package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.res.Resources;
import android.os.storage.VolumeInfo;
import android.util.SparseArray;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.common.ProgressBarPreference;
import com.android.car.settings.storage.StorageAsyncLoader;
import com.android.car.settings.storage.StorageSettingsManager;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StorageUsageBasePreferenceController extends PreferenceController<ProgressBarPreference> implements StorageSettingsManager.VolumeListener {
    private static final int PROGRESS_MAX = 100;
    private VolumeInfo mVolumeInfo;

    protected abstract long calculateCategoryUsage(SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray, long j);

    public StorageUsageBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ProgressBarPreference> getPreferenceType() {
        return ProgressBarPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        getPreference().setSummary(R.string.memory_calculating_size);
        getPreference().setMax(100);
    }

    @Override // com.android.car.settings.storage.StorageSettingsManager.VolumeListener
    public void onDataLoaded(SparseArray<StorageAsyncLoader.AppsStorageResult> result, long usedSizeBytes, long totalSizeBytes) {
        setStorageSize(calculateCategoryUsage(result, usedSizeBytes), totalSizeBytes);
    }

    public VolumeInfo getVolumeInfo() {
        return this.mVolumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.mVolumeInfo = volumeInfo;
    }

    private void setStorageSize(long size, long total) {
        int progressPercent;
        getPreference().setSummary(FileSizeFormatter.formatFileSize(getContext(), size, getGigabyteSuffix(getContext().getResources()), FileSizeFormatter.GIGABYTE_IN_BYTES));
        if (total == 0) {
            progressPercent = 0;
        } else {
            progressPercent = (int) ((100 * size) / total);
        }
        getPreference().setProgress(progressPercent);
    }

    private static int getGigabyteSuffix(Resources res) {
        return res.getIdentifier("gigabyteShort", "string", SystemMediaRouteProvider.PACKAGE_NAME);
    }
}
