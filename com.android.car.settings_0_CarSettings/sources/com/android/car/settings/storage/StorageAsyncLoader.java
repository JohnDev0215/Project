package com.android.car.settings.storage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.SparseArray;
import com.android.car.settings.common.AsyncLoader;
import com.android.car.settings.common.Logger;
import com.android.car.settings.users.UserHelper;
import com.android.car.ui.utils.RotaryConstants;
import com.android.settingslib.applications.StorageStatsSource;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StorageAsyncLoader extends AsyncLoader<SparseArray<AppsStorageResult>> {
    private static final Logger LOG = new Logger((Class<?>) StorageAsyncLoader.class);
    private final PackageManager mPackageManager;
    private final StorageStatsSource mStatsManager;
    private final UserHelper mUserHelper;

    public StorageAsyncLoader(Context context, StorageStatsSource source) {
        super(context);
        this.mStatsManager = source;
        this.mPackageManager = context.getPackageManager();
        this.mUserHelper = UserHelper.getInstance(context);
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public SparseArray<AppsStorageResult> loadInBackground() {
        ArraySet<String> seenPackages = new ArraySet<>();
        SparseArray<AppsStorageResult> result = new SparseArray<>();
        List<UserInfo> infos = this.mUserHelper.getAllUsers();
        int userCount = infos.size();
        for (int i = 0; i < userCount; i++) {
            UserInfo info = infos.get(i);
            result.put(info.id, getStorageResultForUser(info.id, seenPackages));
        }
        return result;
    }

    private AppsStorageResult getStorageResultForUser(int userId, ArraySet<String> seenPackages) {
        ArraySet<String> arraySet = seenPackages;
        LOG.d("Loading apps");
        List<ApplicationInfo> applicationInfos = this.mPackageManager.getInstalledApplicationsAsUser(0, userId);
        UserHandle myUser = UserHandle.of(userId);
        int size = applicationInfos.size();
        long gameAppSize = 0;
        long musicAppsSize = 0;
        long videoAppsSize = 0;
        long photosAppsSize = 0;
        long otherAppsSize = 0;
        int i = 0;
        while (i < size) {
            ApplicationInfo app = applicationInfos.get(i);
            try {
                StorageStatsSource.AppStorageStats stats = this.mStatsManager.getStatsForPackage(null, app.packageName, myUser);
                long dataSize = stats.getDataBytes();
                long cacheQuota = this.mStatsManager.getCacheQuotaBytes(null, app.uid);
                long cacheBytes = stats.getCacheBytes();
                long blamedSize = dataSize;
                if (cacheQuota < cacheBytes) {
                    blamedSize = (blamedSize - cacheBytes) + cacheQuota;
                }
                if (!arraySet.contains(app.packageName)) {
                    blamedSize += stats.getCodeBytes();
                    arraySet.add(app.packageName);
                }
                int i2 = app.category;
                if (i2 == 0) {
                    gameAppSize += blamedSize;
                } else if (i2 == 1) {
                    musicAppsSize += blamedSize;
                } else if (i2 == 2) {
                    videoAppsSize += blamedSize;
                } else if (i2 == 3) {
                    photosAppsSize += blamedSize;
                } else if ((app.flags & RotaryConstants.ACTION_NUDGE_TO_ANOTHER_FOCUS_AREA) != 0) {
                    gameAppSize += blamedSize;
                } else {
                    otherAppsSize += blamedSize;
                }
            } catch (PackageManager.NameNotFoundException | IOException e) {
                LOG.w("App unexpectedly not found", e);
            }
            i++;
            arraySet = seenPackages;
        }
        AppsStorageResult result = new AppsStorageResult(gameAppSize, musicAppsSize, photosAppsSize, videoAppsSize, otherAppsSize);
        LOG.d("Loading external stats");
        try {
            result.mStorageStats = this.mStatsManager.getExternalStorageStats(null, UserHandle.of(userId));
        } catch (IOException e2) {
            LOG.w("External stats not loaded" + e2);
        }
        LOG.d("Obtaining result completed");
        return result;
    }

    public static class AppsStorageResult {
        private long mCacheSize;
        private final long mGamesSize;
        private final long mMusicAppsSize;
        private final long mOtherAppsSize;
        private final long mPhotosAppsSize;
        private StorageStatsSource.ExternalStorageStats mStorageStats;
        private final long mVideoAppsSize;

        AppsStorageResult(long gamesSize, long musicAppsSize, long photosAppsSize, long videoAppsSize, long otherAppsSize) {
            this.mGamesSize = gamesSize;
            this.mMusicAppsSize = musicAppsSize;
            this.mPhotosAppsSize = photosAppsSize;
            this.mVideoAppsSize = videoAppsSize;
            this.mOtherAppsSize = otherAppsSize;
        }

        public long getGamesSize() {
            return this.mGamesSize;
        }

        public long getMusicAppsSize() {
            return this.mMusicAppsSize;
        }

        public long getPhotosAppsSize() {
            return this.mPhotosAppsSize;
        }

        public long getVideoAppsSize() {
            return this.mVideoAppsSize;
        }

        public long getOtherAppsSize() {
            return this.mOtherAppsSize;
        }

        public long getCacheSize() {
            return this.mCacheSize;
        }

        public void setCacheSize(long cacheSize) {
            this.mCacheSize = cacheSize;
        }

        public StorageStatsSource.ExternalStorageStats getExternalStats() {
            return this.mStorageStats;
        }

        public void setExternalStats(StorageStatsSource.ExternalStorageStats externalStats) {
            this.mStorageStats = externalStats;
        }
    }
}
