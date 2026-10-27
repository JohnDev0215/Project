package com.amazon.device.crashmanager.utils;

import android.content.SharedPreferences;
import com.amazon.dp.logger.DPLogger;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultCrashDescriptorStorageUtil implements CrashDescriptorStorageUtil {
    private static final String KEY = "crashDescriptors";
    private static final String LAST_CACHE_CLEANUP_KEY = "lastCacheCleaned";
    private static final int MAX_DEDUPE_COUNT = 10;
    private static final String TAG = "CrashDescriptorStorageUtil";
    private static final DPLogger log = new DPLogger(TAG);
    private final LRUCache<String, DescriptorCount> mCache;
    private final long mCacheExpirationTimeMs;
    private final long mCheckExpirationTimeMs;
    private long mLastCacheCleanupMs;
    private final int mMaxCapacity;
    private final long mMaxDedupeTimeMs;
    private final SharedPreferences mSharedPreferences;

    public DefaultCrashDescriptorStorageUtil(SharedPreferences sharedPreferences, int i, long j) {
        if (sharedPreferences == null) {
            throw new IllegalArgumentException("context cannot be null in CrashDescriptorStorageUtil");
        }
        if (i < 1) {
            throw new IllegalArgumentException("maxCapacity cannot be 0 or less in CrashDescriptorStorageUtil");
        }
        if (j < 1) {
            throw new IllegalArgumentException("cacheExpirationTime cannot be less than 1 millisecond in CrashDescriptorStorageUtil");
        }
        this.mSharedPreferences = sharedPreferences;
        this.mMaxCapacity = i;
        this.mCacheExpirationTimeMs = j;
        this.mCache = new LRUCache<>(i);
        long j2 = j / 10;
        this.mMaxDedupeTimeMs = j2;
        this.mCheckExpirationTimeMs = j2 / 2;
        loadFromSharedPreferences();
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public List<String> getDescriptors() {
        return Arrays.asList(this.mCache.keySet().toArray(new String[0]));
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public boolean contains(String str) {
        checkCacheCleared();
        if (str == null || this.mCache.isEmpty()) {
            return false;
        }
        return this.mCache.containsKey(str);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public void push(String str) {
        if (str == null) {
            return;
        }
        DescriptorCount descriptorCount = this.mCache.get(str);
        if (descriptorCount != null) {
            descriptorCount.increment();
        } else {
            this.mCache.put(str, new DescriptorCount(this));
        }
        log.info("push", "Pushed Crash Descriptor on to the cache ", str);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public Integer getCount(String str) {
        if (str == null) {
            return 0;
        }
        DescriptorCount descriptorCount = this.mCache.get(str);
        return Integer.valueOf(descriptorCount != null ? descriptorCount.getCount() : 0);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public Integer prune(String str) {
        if (str == null) {
            return 0;
        }
        DescriptorCount descriptorCountRemove = this.mCache.remove(str);
        return Integer.valueOf(descriptorCountRemove != null ? descriptorCountRemove.getCount() : 0);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public Boolean shouldDedupe(String str) {
        if (str == null) {
            return Boolean.FALSE;
        }
        DescriptorCount descriptorCount = this.mCache.get(str);
        if (descriptorCount == null) {
            return Boolean.FALSE;
        }
        if (descriptorCount.getCount() < 10 && descriptorCount.getCreated() > System.currentTimeMillis() - this.mMaxDedupeTimeMs) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void clearPersistentStorage() {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.clear();
        editorEdit.commit();
        log.info("clearPersistentStorage", "Cleared Shared Preferences", new Object[0]);
    }

    public void pruneCache() {
        int i;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mCache != null) {
            long j = jCurrentTimeMillis - this.mCacheExpirationTimeMs;
            StringBuilder sb = log.isDebugEnabled() ? new StringBuilder() : null;
            Iterator<Map.Entry<String, DescriptorCount>> it = this.mCache.entrySet().iterator();
            i = 0;
            while (it.hasNext()) {
                Map.Entry<String, DescriptorCount> next = it.next();
                if (next.getValue().getCreated() < j) {
                    if (sb != null) {
                        sb.append(next.getKey());
                        sb.append(" ");
                    }
                    it.remove();
                    i++;
                }
            }
            DPLogger dPLogger = log;
            if (dPLogger.isDebugEnabled()) {
                dPLogger.debug("clearCache", "Pruned the following items: " + sb.toString(), new Object[0]);
                StringBuilder sb2 = new StringBuilder();
                Iterator<String> it2 = this.mCache.keySet().iterator();
                while (it2.hasNext()) {
                    sb2.append(it2.next());
                    sb2.append(" ");
                }
                log.debug("clearCache", "New key set: " + sb2.toString(), new Object[0]);
            }
        } else {
            i = 0;
        }
        this.mLastCacheCleanupMs = jCurrentTimeMillis;
        log.info("clearCache", "Pruned expired items from the LRU cache = ", Integer.valueOf(i));
    }

    public void pruneAll() {
        pruneCache();
        persistCrashDescriptors();
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public void clearAll() {
        this.mCache.clear();
        this.mLastCacheCleanupMs = System.currentTimeMillis();
        persistCrashDescriptors();
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public void persistCrashDescriptors() {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.clear();
        editorEdit.putLong(LAST_CACHE_CLEANUP_KEY, this.mLastCacheCleanupMs);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, DescriptorCount> entry : this.mCache.entrySet()) {
            log.debug("saveToSharedPreferences", "Save Key =", entry.getKey());
            sb.append(entry.getKey());
            sb.append(" ");
            if (entry.getValue() != null) {
                editorEdit.putString(entry.getKey(), entry.getValue().toString());
            }
        }
        editorEdit.putString(KEY, sb.toString());
        editorEdit.commit();
        DPLogger dPLogger = log;
        dPLogger.info("saveToSharedPreferences", "Added last cleared cache to shared preferences = ", Long.valueOf(this.mLastCacheCleanupMs));
        dPLogger.info("saveToSharedPreferences", "Number of keys stored in shared preferences = ", Integer.valueOf(this.mCache.size()));
    }

    private void loadFromSharedPreferences() {
        String string;
        long time = this.mSharedPreferences.getLong(LAST_CACHE_CLEANUP_KEY, -1L);
        if (time <= 0) {
            time = new Date().getTime();
            log.info("loadFromSharedPreferences", "lastCacheCleared is not stored in Stored Preferences...storing", Long.valueOf(time));
        }
        this.mLastCacheCleanupMs = time;
        try {
            string = this.mSharedPreferences.getString(KEY, null);
        } catch (Exception e) {
            log.warn("loadFromSharedPreferences", "Error when loading crash descriptors.", e);
            string = null;
        }
        if (string == null || string.isEmpty()) {
            log.info("loadFromSharedPreferences", "No keys retrieved from shared preferences.", new Object[0]);
            return;
        }
        String[] strArrSplit = string.split(" ");
        log.debug("loadFromSharedPreferences", "Number of keys retrieved from shared preferences =", Integer.valueOf(strArrSplit.length));
        for (String str : strArrSplit) {
            try {
                this.mCache.put(str, deserializeDescriptorCount(this.mSharedPreferences.getString(str, null)));
                log.debug("loadFromSharedPreferences", "Load Key =", str);
            } catch (Exception e2) {
                log.warn("loadFromSharedPreferences", "Error when loading crash descriptors counts.", e2);
            }
        }
    }

    private void checkCacheCleared() {
        if (shouldPrune()) {
            log.info("checkCacheCleared", "Cache check needed, cleaning...", new Object[0]);
            pruneAll();
        }
    }

    public boolean shouldPrune() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.mLastCacheCleanupMs;
        if (j >= 0) {
            return j >= this.mCheckExpirationTimeMs;
        }
        this.mLastCacheCleanupMs = jCurrentTimeMillis;
        return false;
    }

    private DescriptorCount deserializeDescriptorCount(String str) {
        if (str == null) {
            return new DescriptorCount(this);
        }
        String[] strArrSplit = str.split("|");
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = strArrSplit.length >= 1 ? Integer.parseInt(strArrSplit[0]) : 1;
        try {
            if (strArrSplit.length >= 2) {
                jCurrentTimeMillis = Long.parseLong(strArrSplit[1]);
            }
        } catch (Exception unused) {
        }
        return new DescriptorCount(i, jCurrentTimeMillis);
    }

    private class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int mMaxCapacity;

        public LRUCache(int i) {
            super(i + 1, 0.75f, true);
            this.mMaxCapacity = i;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
            return size() > this.mMaxCapacity;
        }
    }

    private class DescriptorCount {
        private int mCount;
        private long mCreatedMs;

        public DescriptorCount(DefaultCrashDescriptorStorageUtil defaultCrashDescriptorStorageUtil) {
            this(1, System.currentTimeMillis());
        }

        public DescriptorCount(int i, long j) {
            this.mCount = i;
            this.mCreatedMs = j;
        }

        public void increment() {
            this.mCount++;
        }

        public long getCreated() {
            return this.mCreatedMs;
        }

        public int getCount() {
            return this.mCount;
        }

        public String toString() {
            return Integer.toString(this.mCount) + "|" + Long.toString(this.mCreatedMs);
        }
    }
}
