package com.amazon.avod.media.contentcache.internal;

import com.amazon.avod.util.DataUnit;

/* JADX INFO: loaded from: classes2.dex */
public class ClientCacheConfig {
    private long mMaxCacheSizeInKb;

    public void setMaxCacheSizeInKb(long j) {
        this.mMaxCacheSizeInKb = (long) DataUnit.BYTES.toKiloBytes(j);
    }

    public long getMaxCacheSizeInKb() {
        return this.mMaxCacheSizeInKb;
    }
}
