package com.amazon.mp3.playback.service.player.factory;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.net.dmls.ContentId;
import com.amazon.mp3.net.dmls.ContentResponse;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingResponseCache {
    private static StreamingResponseCache sInstance;
    private final int mCacheExpirationThreshold;
    private final ExecutorService mPrefetchRequests = Executors.newSingleThreadExecutor();
    private Map<ContentId, ContentResponse> mCache = new ConcurrentHashMap();

    public static synchronized StreamingResponseCache getCache() {
        if (sInstance == null) {
            sInstance = new StreamingResponseCache(1200000);
        }
        return sInstance;
    }

    private StreamingResponseCache(int cacheExpiryThreshold) {
        this.mCacheExpirationThreshold = cacheExpiryThreshold;
    }

    public void putResponse(ContentId id, ContentResponse response) {
        this.mCache.put(id, response);
    }

    public ContentResponse getResponse(ContentId id) {
        ContentResponse contentResponse = this.mCache.get(id);
        if (contentResponse == null || validEntry(contentResponse)) {
            return contentResponse;
        }
        this.mCache.remove(id);
        return null;
    }

    private boolean validEntry(ContentResponse response) {
        return withinThreshold(response) && bitrateValid(response);
    }

    private boolean withinThreshold(ContentResponse response) {
        return System.currentTimeMillis() <= response.getExpiration() - ((long) this.mCacheExpirationThreshold);
    }

    private boolean bitrateValid(ContentResponse response) {
        return response.getBitrate().equals(StreamingBitrate.fromEntryValueForDMLS(SettingsUtil.getMultiBitrateStreamingPreferenceForDMLS(AmazonApplication.getContext())));
    }
}
