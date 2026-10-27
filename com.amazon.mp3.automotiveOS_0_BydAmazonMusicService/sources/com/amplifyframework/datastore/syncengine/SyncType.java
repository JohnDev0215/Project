package com.amplifyframework.datastore.syncengine;

import com.amplifyframework.util.Time;

/* JADX INFO: loaded from: classes5.dex */
public enum SyncType {
    BASE,
    DELTA;

    public static SyncType fromSyncTimeAndThreshold(SyncTime syncTime, long j) {
        if (SyncTime.never().equals(syncTime)) {
            return BASE;
        }
        return Time.now() - syncTime.toLong() > j ? BASE : DELTA;
    }
}
