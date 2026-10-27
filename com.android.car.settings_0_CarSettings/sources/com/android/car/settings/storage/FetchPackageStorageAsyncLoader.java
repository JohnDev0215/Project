package com.android.car.settings.storage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.Log;
import com.android.car.settings.common.AsyncLoader;
import com.android.internal.util.Preconditions;
import com.android.settingslib.applications.StorageStatsSource;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class FetchPackageStorageAsyncLoader extends AsyncLoader<StorageStatsSource.AppStorageStats> {
    private static final String TAG = "FetchPackageStorage";
    private final ApplicationInfo mInfo;
    private final StorageStatsSource mSource;
    private final UserHandle mUser;

    public FetchPackageStorageAsyncLoader(Context context, StorageStatsSource source, ApplicationInfo info, UserHandle user) {
        super(context);
        this.mSource = (StorageStatsSource) Preconditions.checkNotNull(source);
        this.mInfo = info;
        this.mUser = user;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public StorageStatsSource.AppStorageStats loadInBackground() {
        try {
            StorageStatsSource.AppStorageStats result = this.mSource.getStatsForPackage(this.mInfo.volumeUuid, this.mInfo.packageName, this.mUser);
            return result;
        } catch (PackageManager.NameNotFoundException | IOException e) {
            Log.w(TAG, "Package may have been removed during query, failing gracefully", e);
            return null;
        }
    }
}
