package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TeeDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public final class CacheDataSource implements DataSource {
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private DataSource currentDataSource;
    private boolean currentRequestIgnoresCache;
    private boolean currentRequestUnbounded;
    private final EventListener eventListener;
    private int flags;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private String key;
    private CacheSpan lockedSpan;
    private long readPosition;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    private Uri uri;

    public interface EventListener {
        void onCachedBytesRead(long j, long j2);
    }

    public CacheDataSource(Cache cache, DataSource dataSource) {
        this(cache, dataSource, 0, 2097152L);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, int i, long j) {
        this(cache, dataSource, new FileDataSource(), new CacheDataSink(cache, j), i, null);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, EventListener eventListener) {
        this.cache = cache;
        this.cacheReadDataSource = dataSource2;
        this.blockOnCache = (i & 1) != 0;
        this.ignoreCacheOnError = (i & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i & 4) != 0;
        this.upstreamDataSource = dataSource;
        if (dataSink != null) {
            this.cacheWriteDataSource = new TeeDataSource(dataSource, dataSink);
        } else {
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.uri = dataSpec.uri;
            this.flags = dataSpec.flags;
            String key = CacheUtil.getKey(dataSpec);
            this.key = key;
            this.readPosition = dataSpec.position;
            boolean z = (this.ignoreCacheOnError && this.seenCacheError) || (dataSpec.length == -1 && this.ignoreCacheForUnsetLengthRequests);
            this.currentRequestIgnoresCache = z;
            long j = dataSpec.length;
            if (j != -1 || z) {
                this.bytesRemaining = j;
            } else {
                long contentLength = this.cache.getContentLength(key);
                this.bytesRemaining = contentLength;
                if (contentLength != -1) {
                    long j2 = contentLength - dataSpec.position;
                    this.bytesRemaining = j2;
                    if (j2 <= 0) {
                        throw new DataSourceException(0);
                    }
                }
            }
            openNextSource(true);
            return this.bytesRemaining;
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int i3 = this.currentDataSource.read(bArr, i, i2);
            if (i3 >= 0) {
                if (this.currentDataSource == this.cacheReadDataSource) {
                    this.totalCachedBytesRead += (long) i3;
                }
                long j = i3;
                this.readPosition += j;
                long j2 = this.bytesRemaining;
                if (j2 != -1) {
                    this.bytesRemaining = j2 - j;
                }
            } else {
                if (this.currentRequestUnbounded) {
                    setContentLength(this.readPosition);
                    this.bytesRemaining = 0L;
                }
                closeCurrentSource();
                long j3 = this.bytesRemaining;
                if ((j3 > 0 || j3 == -1) && openNextSource(false)) {
                    return read(bArr, i, i2);
                }
            }
            return i3;
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        DataSource dataSource = this.currentDataSource;
        return dataSource == this.upstreamDataSource ? dataSource.getUri() : this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        this.uri = null;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean openNextSource(boolean r17) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CacheDataSource.openNextSource(boolean):boolean");
    }

    private void setContentLength(long j) throws IOException {
        if (this.currentDataSource == this.cacheWriteDataSource) {
            this.cache.setContentLength(this.key, j);
        }
    }

    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource == null) {
            return;
        }
        try {
            dataSource.close();
            this.currentDataSource = null;
            this.currentRequestUnbounded = false;
        } finally {
            CacheSpan cacheSpan = this.lockedSpan;
            if (cacheSpan != null) {
                this.cache.releaseHoleSpan(cacheSpan);
                this.lockedSpan = null;
            }
        }
    }

    private void handleBeforeThrow(IOException iOException) {
        if (this.currentDataSource == this.cacheReadDataSource || (iOException instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    private void notifyBytesRead() {
        EventListener eventListener = this.eventListener;
        if (eventListener == null || this.totalCachedBytesRead <= 0) {
            return;
        }
        eventListener.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
        this.totalCachedBytesRead = 0L;
    }
}
