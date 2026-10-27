package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.text.format.Formatter;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.storage.AppsStorageStatsManager;
import com.android.settingslib.applications.StorageStatsSource;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StorageSizeBasePreferenceController extends PreferenceController<StorageAppDetailPreference> implements AppsStorageStatsManager.Callback {
    private StorageStatsSource.AppStorageStats mAppStorageStats;
    private AppsStorageStatsManager mAppsStorageStatsManager;
    private boolean mCachedCleared;
    private Context mContext;
    private boolean mDataCleared;

    protected abstract long getSize();

    public StorageSizeBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mDataCleared = false;
        this.mCachedCleared = false;
        this.mContext = context;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<StorageAppDetailPreference> getPreferenceType() {
        return StorageAppDetailPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        AppsStorageStatsManager appsStorageStatsManager = this.mAppsStorageStatsManager;
        if (appsStorageStatsManager == null) {
            return;
        }
        appsStorageStatsManager.registerListener(this);
    }

    public void setAppsStorageStatsManager(AppsStorageStatsManager appsStorageStatsManager) {
        this.mAppsStorageStatsManager = appsStorageStatsManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(StorageAppDetailPreference preference) {
        if (this.mAppStorageStats == null) {
            return;
        }
        preference.setDetailText(getSizeStr(getSize()));
    }

    public void setAppStorageStats(StorageStatsSource.AppStorageStats appStorageStats) {
        this.mAppStorageStats = appStorageStats;
    }

    public StorageStatsSource.AppStorageStats getAppStorageStats() {
        return this.mAppStorageStats;
    }

    boolean isCachedCleared() {
        return this.mCachedCleared;
    }

    boolean isDataCleared() {
        return this.mDataCleared;
    }

    private String getSizeStr(long size) {
        return Formatter.formatFileSize(this.mContext, size);
    }

    @Override // com.android.car.settings.storage.AppsStorageStatsManager.Callback
    public void onDataLoaded(StorageStatsSource.AppStorageStats data, boolean cacheCleared, boolean dataCleared) {
        this.mCachedCleared = cacheCleared;
        this.mDataCleared = dataCleared;
        refreshUi();
    }
}
