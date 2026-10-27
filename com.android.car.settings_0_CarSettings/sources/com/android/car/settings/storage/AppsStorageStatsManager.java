package com.android.car.settings.storage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.settingslib.applications.StorageStatsSource;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AppsStorageStatsManager {
    private List<Callback> mAppsStorageStatsListeners = new ArrayList();
    private boolean mCacheCleared;
    private final Context mContext;
    private boolean mDataCleared;
    private ApplicationInfo mInfo;
    private int mUserId;

    public interface Callback {
        void onDataLoaded(StorageStatsSource.AppStorageStats appStorageStats, boolean z, boolean z2);
    }

    AppsStorageStatsManager(Context context) {
        this.mContext = context;
    }

    public void registerListener(Callback appsStorageStatsListener) {
        if (!this.mAppsStorageStatsListeners.contains(appsStorageStatsListener)) {
            this.mAppsStorageStatsListeners.add(appsStorageStatsListener);
        }
    }

    public void unregisterListener(Callback appsStorageStatsListener) {
        this.mAppsStorageStatsListeners.remove(appsStorageStatsListener);
    }

    public void startLoading(LoaderManager loaderManager, ApplicationInfo info, int userId, boolean cacheCleared, boolean dataCleared) {
        this.mInfo = info;
        this.mUserId = userId;
        this.mCacheCleared = cacheCleared;
        this.mDataCleared = dataCleared;
        loaderManager.restartLoader(1, Bundle.EMPTY, new AppsStorageStatsResult());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAppsStorageStatsLoaded(StorageStatsSource.AppStorageStats data) {
        for (Callback listener : this.mAppsStorageStatsListeners) {
            listener.onDataLoaded(data, this.mCacheCleared, this.mDataCleared);
        }
    }

    private class AppsStorageStatsResult implements LoaderManager.LoaderCallbacks<StorageStatsSource.AppStorageStats> {
        private AppsStorageStatsResult() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<StorageStatsSource.AppStorageStats> onCreateLoader(int id, Bundle args) {
            return new FetchPackageStorageAsyncLoader(AppsStorageStatsManager.this.mContext, new StorageStatsSource(AppsStorageStatsManager.this.mContext), AppsStorageStatsManager.this.mInfo, UserHandle.of(AppsStorageStatsManager.this.mUserId));
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<StorageStatsSource.AppStorageStats> loader, StorageStatsSource.AppStorageStats data) {
            AppsStorageStatsManager.this.onAppsStorageStatsLoaded(data);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<StorageStatsSource.AppStorageStats> loader) {
        }
    }
}
