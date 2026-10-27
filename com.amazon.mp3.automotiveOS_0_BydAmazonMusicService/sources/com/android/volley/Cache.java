package com.android.volley;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public interface Cache {
    void clear();

    Entry get(String str);

    void initialize();

    void invalidate(String str, boolean z);

    void put(String str, Entry entry);

    public static class Entry {
        public byte[] data;
        public String etag;
        public long lastModified;
        public Map<String, String> responseHeaders = Collections.emptyMap();
        public long serverDate;
        public long softTtl;
        public long ttl;

        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }

        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        }

        public long timeToExpire() {
            return this.ttl - System.currentTimeMillis();
        }
    }
}
