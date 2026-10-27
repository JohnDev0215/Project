package com.amplifyframework.datastore.syncengine;

import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.util.Time;

/* JADX INFO: loaded from: classes5.dex */
final class SyncTime {
    private final Long time;

    private SyncTime(Long l) {
        this.time = l;
    }

    static SyncTime at(long j) {
        return new SyncTime(Long.valueOf(j));
    }

    static SyncTime never() {
        return new SyncTime(null);
    }

    static SyncTime from(@Nullable Long l) {
        return new SyncTime(l);
    }

    public static SyncTime now() {
        return new SyncTime(Long.valueOf(Time.now()));
    }

    boolean exists() {
        return this.time != null;
    }

    long toLong() {
        Long l = this.time;
        if (l == null) {
            throw new IllegalStateException("No last sync time!");
        }
        return l.longValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SyncTime.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.time, ((SyncTime) obj).time);
    }

    public int hashCode() {
        Long l = this.time;
        if (l != null) {
            return l.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "LastSyncTime{time=" + this.time + '}';
    }
}
