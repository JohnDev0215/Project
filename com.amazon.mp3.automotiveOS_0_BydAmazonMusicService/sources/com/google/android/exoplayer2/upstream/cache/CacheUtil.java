package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Iterator;
import java.util.NavigableSet;

/* JADX INFO: loaded from: classes6.dex */
public final class CacheUtil {

    public static class CachingCounters {
        public volatile long alreadyCachedBytes;
        public volatile long contentLength = -1;
        public volatile long newlyCachedBytes;

        public long totalCachedBytes() {
            return this.alreadyCachedBytes + this.newlyCachedBytes;
        }
    }

    public static String generateKey(Uri uri) {
        return uri.toString();
    }

    public static String getKey(DataSpec dataSpec) {
        String str = dataSpec.key;
        return str != null ? str : generateKey(dataSpec.uri);
    }

    public static void getCached(DataSpec dataSpec, Cache cache, CachingCounters cachingCounters) {
        String key = getKey(dataSpec);
        long j = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length;
        if (contentLength == -1) {
            contentLength = cache.getContentLength(key);
        }
        cachingCounters.contentLength = contentLength;
        cachingCounters.alreadyCachedBytes = 0L;
        cachingCounters.newlyCachedBytes = 0L;
        long j2 = j;
        long j3 = contentLength;
        while (j3 != 0) {
            long cachedBytes = cache.getCachedBytes(key, j2, j3 != -1 ? j3 : Long.MAX_VALUE);
            if (cachedBytes > 0) {
                cachingCounters.alreadyCachedBytes += cachedBytes;
            } else {
                cachedBytes = -cachedBytes;
                if (cachedBytes == Long.MAX_VALUE) {
                    return;
                }
            }
            j2 += cachedBytes;
            if (j3 == -1) {
                cachedBytes = 0;
            }
            j3 -= cachedBytes;
        }
    }

    public static void cache(DataSpec dataSpec, Cache cache, DataSource dataSource, CachingCounters cachingCounters) throws InterruptedException, IOException {
        cache(dataSpec, cache, new CacheDataSource(cache, dataSource), new byte[131072], null, 0, cachingCounters, false);
    }

    public static void cache(DataSpec dataSpec, Cache cache, CacheDataSource cacheDataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, CachingCounters cachingCounters, boolean z) throws InterruptedException, IOException {
        CachingCounters cachingCounters2 = cachingCounters;
        Assertions.checkNotNull(cacheDataSource);
        Assertions.checkNotNull(bArr);
        if (cachingCounters2 != null) {
            getCached(dataSpec, cache, cachingCounters2);
        } else {
            cachingCounters2 = new CachingCounters();
        }
        CachingCounters cachingCounters3 = cachingCounters2;
        String key = getKey(dataSpec);
        long j = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length;
        if (contentLength == -1) {
            contentLength = cache.getContentLength(key);
        }
        long j2 = j;
        long j3 = contentLength;
        while (true) {
            long j4 = 0;
            if (j3 == 0) {
                return;
            }
            long cachedBytes = cache.getCachedBytes(key, j2, j3 != -1 ? j3 : Long.MAX_VALUE);
            if (cachedBytes <= 0) {
                long j5 = -cachedBytes;
                if (readAndDiscard(dataSpec, j2, j5, cacheDataSource, bArr, priorityTaskManager, i, cachingCounters3) < j5) {
                    if (z && j3 != -1) {
                        throw new EOFException();
                    }
                    return;
                }
                cachedBytes = j5;
            }
            j2 += cachedBytes;
            if (j3 != -1) {
                j4 = cachedBytes;
            }
            j3 -= j4;
        }
    }

    private static long readAndDiscard(DataSpec dataSpec, long j, long j2, DataSource dataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, CachingCounters cachingCounters) throws InterruptedException, IOException {
        if (priorityTaskManager != null) {
            priorityTaskManager.proceed(i);
        }
        try {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            DataSpec dataSpec2 = new DataSpec(dataSpec.uri, dataSpec.postBody, j, (dataSpec.position + j) - dataSpec.absoluteStreamPosition, -1L, dataSpec.key, dataSpec.flags | 2);
            long jOpen = dataSource.open(dataSpec2);
            if (cachingCounters.contentLength == -1 && jOpen != -1) {
                cachingCounters.contentLength = dataSpec2.absoluteStreamPosition + jOpen;
            }
            long j3 = 0;
            while (true) {
                if (j3 == j2) {
                    break;
                }
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                int i2 = dataSource.read(bArr, 0, j2 != -1 ? (int) Math.min(bArr.length, j2 - j3) : bArr.length);
                if (i2 != -1) {
                    long j4 = i2;
                    j3 += j4;
                    cachingCounters.newlyCachedBytes += j4;
                } else if (cachingCounters.contentLength == -1) {
                    cachingCounters.contentLength = dataSpec2.absoluteStreamPosition + j3;
                }
            }
            return j3;
        } finally {
            Util.closeQuietly(dataSource);
        }
    }

    public static void remove(Cache cache, String str) {
        NavigableSet<CacheSpan> cachedSpans = cache.getCachedSpans(str);
        if (cachedSpans == null) {
            return;
        }
        Iterator<CacheSpan> it = cachedSpans.iterator();
        while (it.hasNext()) {
            try {
                cache.removeSpan(it.next());
            } catch (Cache.CacheException unused) {
            }
        }
    }

    private CacheUtil() {
    }
}
