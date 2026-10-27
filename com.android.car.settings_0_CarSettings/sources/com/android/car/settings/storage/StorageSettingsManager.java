package com.android.car.settings.storage;

import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.SparseArray;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.car.settings.storage.StorageAsyncLoader;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StorageSettingsManager {
    private static final int STORAGE_JOB_ID = 0;
    private static final int VOLUME_SIZE_JOB_ID = 1;
    private SparseArray<StorageAsyncLoader.AppsStorageResult> mAppsStorageResultSparseArray;
    private final Context mContext;
    private PrivateStorageInfo mPrivateStorageInfo;
    private final VolumeInfo mVolumeInfo;
    private List<VolumeListener> mVolumeListeners = new ArrayList();

    public interface VolumeListener {
        void onDataLoaded(SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray, long j, long j2);
    }

    StorageSettingsManager(Context context, VolumeInfo volume) {
        this.mContext = context;
        this.mVolumeInfo = volume;
    }

    public void registerListener(VolumeListener volumeListener) {
        if (!this.mVolumeListeners.contains(volumeListener)) {
            this.mVolumeListeners.add(volumeListener);
        }
    }

    public void unregisterlistener(VolumeListener volumeListener) {
        this.mVolumeListeners.remove(volumeListener);
    }

    public void startLoading(LoaderManager loaderManager) {
        loaderManager.restartLoader(0, Bundle.EMPTY, new AppsStorageResult());
        loaderManager.restartLoader(1, Bundle.EMPTY, new VolumeSizeCallback());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReceivedSizes() {
        PrivateStorageInfo privateStorageInfo;
        if (this.mAppsStorageResultSparseArray != null && (privateStorageInfo = this.mPrivateStorageInfo) != null) {
            long privateUsedBytes = privateStorageInfo.totalBytes - this.mPrivateStorageInfo.freeBytes;
            for (VolumeListener listener : this.mVolumeListeners) {
                listener.onDataLoaded(this.mAppsStorageResultSparseArray, privateUsedBytes, this.mPrivateStorageInfo.totalBytes);
            }
        }
    }

    private class VolumeSizeCallback implements LoaderManager.LoaderCallbacks<PrivateStorageInfo> {
        private VolumeSizeCallback() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<PrivateStorageInfo> onCreateLoader(int id, Bundle args) {
            StorageManager sm = (StorageManager) StorageSettingsManager.this.mContext.getSystemService(StorageManager.class);
            StorageManagerVolumeProvider smvp = new StorageManagerVolumeProvider(sm);
            StorageStatsManager stats = (StorageStatsManager) StorageSettingsManager.this.mContext.getSystemService(StorageStatsManager.class);
            return new VolumeSizesLoader(StorageSettingsManager.this.mContext, smvp, stats, StorageSettingsManager.this.mVolumeInfo);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<PrivateStorageInfo> loader, PrivateStorageInfo privateStorageInfo) {
            if (privateStorageInfo != null) {
                StorageSettingsManager.this.mPrivateStorageInfo = privateStorageInfo;
                StorageSettingsManager.this.onReceivedSizes();
            }
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<PrivateStorageInfo> loader) {
        }
    }

    private class AppsStorageResult implements LoaderManager.LoaderCallbacks<SparseArray<StorageAsyncLoader.AppsStorageResult>> {
        private AppsStorageResult() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> onCreateLoader(int id, Bundle args) {
            return new StorageAsyncLoader(StorageSettingsManager.this.mContext, new StorageStatsSource(StorageSettingsManager.this.mContext));
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader, SparseArray<StorageAsyncLoader.AppsStorageResult> data) {
            StorageSettingsManager.this.mAppsStorageResultSparseArray = data;
            StorageSettingsManager.this.onReceivedSizes();
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader) {
        }
    }
}
