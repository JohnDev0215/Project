package com.amazon.avod.media.framework.storage;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes2.dex */
public class FileLockManager {
    private LoadingCache<String, ReadWriteLock> mLockCache = CacheBuilder.newBuilder().maximumSize(50).build(new CacheLoader<String, ReadWriteLock>() { // from class: com.amazon.avod.media.framework.storage.FileLockManager.1
        @Override // com.google.common.cache.CacheLoader
        public ReadWriteLock load(String str) {
            return new ReentrantReadWriteLock();
        }
    });

    public Lock getReadLock(String str) {
        this.mLockCache.cleanUp();
        return this.mLockCache.getUnchecked(str).readLock();
    }

    public Lock getWriteLock(String str) {
        this.mLockCache.cleanUp();
        return this.mLockCache.getUnchecked(str).writeLock();
    }
}
