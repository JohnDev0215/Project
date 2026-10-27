package com.amazon.avod.media.framework.uriproxy;

import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class UriCacheMap {
    private final Object mLock = new Object();
    private long mTotalSizeInBytes = 0;
    private final LinkedHashMap<URI, Long> mCacheMap = new LinkedHashMap<>();

    public long getTotalSizeInBytes() {
        return this.mTotalSizeInBytes;
    }

    public int size() {
        return this.mCacheMap.size();
    }

    public boolean contains(URI uri) {
        return this.mCacheMap.containsKey(uri);
    }

    public boolean containsThenUpdates(URI uri) {
        Preconditions.checkNotNull(uri);
        synchronized (this.mLock) {
            Long lRemove = this.mCacheMap.remove(uri);
            if (lRemove == null) {
                return false;
            }
            this.mCacheMap.put(uri, lRemove);
            return true;
        }
    }

    public void add(@Nonnull URI uri, long j) {
        Preconditions.checkNotNull(uri);
        synchronized (this.mLock) {
            this.mCacheMap.put(uri, Long.valueOf(j));
            this.mTotalSizeInBytes += j;
        }
    }

    public void clear() {
        synchronized (this.mLock) {
            this.mCacheMap.clear();
            this.mTotalSizeInBytes = 0L;
        }
    }

    public List<URI> trimTo(long j) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mLock) {
            Iterator<Map.Entry<URI, Long>> it = this.mCacheMap.entrySet().iterator();
            while (this.mTotalSizeInBytes > j && it.hasNext()) {
                Map.Entry<URI, Long> next = it.next();
                this.mTotalSizeInBytes -= next.getValue().longValue();
                arrayList.add(next.getKey());
                it.remove();
            }
        }
        return arrayList;
    }

    public void trimIfNotIn(List<URI> list) {
        synchronized (this.mLock) {
            if (this.mCacheMap.size() > list.size()) {
                Iterator<Map.Entry<URI, Long>> it = this.mCacheMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<URI, Long> next = it.next();
                    if (!list.contains(next.getKey())) {
                        this.mTotalSizeInBytes -= next.getValue().longValue();
                        it.remove();
                        if (this.mCacheMap.size() == list.size()) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
