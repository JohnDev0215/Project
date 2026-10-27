package com.google.android.exoplayer2.upstream.cache;

import java.io.File;
import java.io.IOException;
import java.util.NavigableSet;

/* JADX INFO: loaded from: classes6.dex */
public interface Cache {

    public interface Listener {
        void onSpanAdded(Cache cache, CacheSpan cacheSpan);

        void onSpanRemoved(Cache cache, CacheSpan cacheSpan);

        void onSpanTouched(Cache cache, CacheSpan cacheSpan, CacheSpan cacheSpan2);
    }

    void commitFile(File file) throws CacheException;

    long getCacheSpace();

    long getCachedBytes(String str, long j, long j2);

    NavigableSet<CacheSpan> getCachedSpans(String str);

    long getContentLength(String str);

    void releaseHoleSpan(CacheSpan cacheSpan);

    void removeSpan(CacheSpan cacheSpan) throws CacheException;

    void setContentLength(String str, long j) throws CacheException;

    File startFile(String str, long j, long j2) throws CacheException;

    CacheSpan startReadWrite(String str, long j) throws InterruptedException, CacheException;

    CacheSpan startReadWriteNonBlocking(String str, long j) throws CacheException;

    public static class CacheException extends IOException {
        public CacheException(String str) {
            super(str);
        }

        public CacheException(IOException iOException) {
            super(iOException);
        }
    }
}
