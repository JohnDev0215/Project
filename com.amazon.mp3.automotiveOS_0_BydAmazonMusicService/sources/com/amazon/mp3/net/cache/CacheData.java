package com.amazon.mp3.net.cache;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes3.dex */
public class CacheData<T> {
    private T mData;
    private long mTimeStamp;

    public CacheData(T object) {
        this(object, SystemClock.uptimeMillis());
    }

    public CacheData(T object, long timestamp) {
        this.mData = object;
        this.mTimeStamp = timestamp;
    }

    public T getData() {
        return this.mData;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }
}
