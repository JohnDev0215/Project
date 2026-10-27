package com.amazon.avod.media.contentcache.internal.admittance;

import com.amazon.avod.media.contentcache.internal.CacheConfig;
import com.amazon.avod.media.contentcache.internal.CachedContentPersistence;
import com.amazon.avod.media.contentcache.internal.ClientCacheConfig;
import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DiskSpaceMonitoringAdmittancePolicy implements AdmittancePolicy {
    private final ClientCacheConfig mClientCacheConfig;
    private final CacheConfig mConfig;
    private final FileSystem mFileSystem;
    private final CachedContentPersistence mPersistence;

    public DiskSpaceMonitoringAdmittancePolicy(@Nonnull CacheConfig cacheConfig, @Nonnull CachedContentPersistence cachedContentPersistence, @Nonnull FileSystem fileSystem, @Nonnull ClientCacheConfig clientCacheConfig) {
        this.mConfig = (CacheConfig) Preconditions.checkNotNull(cacheConfig, "config");
        this.mPersistence = (CachedContentPersistence) Preconditions.checkNotNull(cachedContentPersistence, "persistence");
        this.mFileSystem = (FileSystem) Preconditions.checkNotNull(fileSystem, "fileSystem");
        this.mClientCacheConfig = (ClientCacheConfig) Preconditions.checkNotNull(clientCacheConfig, "ClientCacheConfig");
    }

    @Override // com.amazon.avod.media.contentcache.internal.admittance.AdmittancePolicy
    @Nonnull
    public AdmittancePolicyResult canAdmit(@Nonnull WritableCacheRecord writableCacheRecord) {
        long maxCacheSizeInKb;
        long totalCacheFilesizeKb = this.mPersistence.getTotalCacheFilesizeKb();
        if (this.mClientCacheConfig.getMaxCacheSizeInKb() > 0) {
            maxCacheSizeInKb = this.mClientCacheConfig.getMaxCacheSizeInKb();
        } else {
            maxCacheSizeInKb = this.mConfig.getMaxCacheSizeInKb();
        }
        if (totalCacheFilesizeKb > maxCacheSizeInKb) {
            DLog.logf("Cannot admit; cache is at %s/%s KB", Long.valueOf(totalCacheFilesizeKb), Long.valueOf(maxCacheSizeInKb));
            return AdmittancePolicyResult.DENIED_CACHE_FULL;
        }
        long remainingDiskSpaceInBytes = this.mFileSystem.getRemainingDiskSpaceInBytes();
        long minimalFreeSpaceInBytes = this.mConfig.getMinimalFreeSpaceInBytes();
        if (minimalFreeSpaceInBytes > remainingDiskSpaceInBytes) {
            DLog.logf("Cannot admit; disk free space is at %s/%s bytes", Long.valueOf(remainingDiskSpaceInBytes), Long.valueOf(minimalFreeSpaceInBytes));
            return AdmittancePolicyResult.DENIED_DISK_FULL;
        }
        return AdmittancePolicyResult.ALLOWED;
    }
}
