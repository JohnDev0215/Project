package com.amazon.avod.media.contentcache.internal;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class LiveCache {
    private final CacheConfig mCacheConfig;
    private final LinkedHashMap<String, ContentSession> mCachedSessions;
    private final ScheduledExecutorService mExecutor;
    private final boolean mIsTimeToLiveEvictionEnabled;
    private final Object mMutex;
    private final Map<String, ScheduledFuture> mPendingEvictionTasks;
    private String mPreparedTitleId;
    private final TimeSpan mTimeToLive;

    public static LiveCache getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final LiveCache INSTANCE = new LiveCache();

        private SingletonHolder() {
        }
    }

    private LiveCache() {
        this(CacheConfig.INSTANCE, ScheduledExecutorBuilder.newBuilderFor(LiveCache.class, new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.VERBOSE).withFixedThreadPoolSize(1).build());
    }

    LiveCache(@Nonnull CacheConfig cacheConfig, @Nonnull ScheduledExecutorService scheduledExecutorService) {
        this.mPreparedTitleId = null;
        CacheConfig cacheConfig2 = (CacheConfig) Preconditions.checkNotNull(cacheConfig, "cacheConfig");
        this.mCacheConfig = cacheConfig2;
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "executor");
        this.mPendingEvictionTasks = new HashMap();
        this.mIsTimeToLiveEvictionEnabled = cacheConfig2.isTimeToLiveEvictionEnabled();
        this.mTimeToLive = cacheConfig2.getLiveSessionTimeToLive();
        this.mCachedSessions = new LinkedHashMap<>();
        this.mMutex = new Object();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logLiveCacheOperation(@Nonnull String str, @Nullable String str2) {
        DLog.logf("LiveCache: %s titleId: %s CacheSize: %s", str, str2, Integer.valueOf(this.mCachedSessions.size()));
    }

    public void notifyPlaybackPrepared(@Nonnull String str) {
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(str, "preparedTitleId");
            logLiveCacheOperation("notifyPlaybackPrepared", str);
            this.mPreparedTitleId = str;
        }
    }

    public void notifyPlaybackTerminated() {
        synchronized (this.mMutex) {
            logLiveCacheOperation("notifyPlaybackTerminated", this.mPreparedTitleId);
            this.mPreparedTitleId = null;
        }
    }

    public boolean addToCache(@Nonnull String str, @Nonnull ContentSession contentSession) {
        Map.Entry<String, ContentSession> next;
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(str, "titleId");
            Preconditions.checkNotNull(contentSession, "newSession");
            if (str.equals(this.mPreparedTitleId)) {
                logLiveCacheOperation("addToCache rejecting new session as it's already prepared/playing", str);
                return false;
            }
            if (this.mCachedSessions.get(str) != null) {
                logLiveCacheOperation("addToCache rejecting new session as a cached session already exists", str);
                return false;
            }
            if (this.mCachedSessions.size() >= this.mCacheConfig.getMaxCachedLiveContentSessionCount() && (next = this.mCachedSessions.entrySet().iterator().next()) != null) {
                logLiveCacheOperation("addToCache max size reached terminating oldest cached session", next.getKey());
                next.getValue().end(false);
                this.mCachedSessions.remove(next.getKey());
                cancelPendingEvictionTask(str);
            }
            this.mCachedSessions.put(str, contentSession);
            logLiveCacheOperation("addToCache success", str);
            if (this.mIsTimeToLiveEvictionEnabled) {
                this.mPendingEvictionTasks.put(str, this.mExecutor.schedule(new EvictionTask(str), this.mTimeToLive.getTotalSeconds(), TimeUnit.SECONDS));
            }
            return true;
        }
    }

    private final class EvictionTask implements Runnable {
        private final String mTitleId;

        private EvictionTask(@Nonnull String str) {
            this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (LiveCache.this.mMutex) {
                LiveCache.this.logLiveCacheOperation("timeToLive expired executing eviction task", this.mTitleId);
                ContentSession contentSession = (ContentSession) LiveCache.this.mCachedSessions.get(this.mTitleId);
                if (contentSession != null) {
                    LiveCache.this.mCachedSessions.remove(this.mTitleId);
                    LiveCache.this.logLiveCacheOperation("popFromCache success", this.mTitleId);
                }
                if (contentSession != null) {
                    contentSession.end(false);
                }
                LiveCache.this.mPendingEvictionTasks.remove(this.mTitleId);
            }
        }
    }

    @Nullable
    public ContentSession popFromCache(@Nonnull String str) {
        ContentSession contentSession;
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(str, "titleId");
            logLiveCacheOperation("popFromCache requested", str);
            contentSession = this.mCachedSessions.get(str);
            if (contentSession != null) {
                logLiveCacheOperation("popFromCache success", str);
                this.mCachedSessions.remove(str);
            }
            cancelPendingEvictionTask(str);
        }
        return contentSession;
    }

    private void cancelPendingEvictionTask(@Nonnull String str) {
        ScheduledFuture scheduledFutureRemove;
        Preconditions.checkNotNull(str, "titleId");
        Preconditions.checkState(Thread.holdsLock(this.mMutex));
        if (!this.mIsTimeToLiveEvictionEnabled || (scheduledFutureRemove = this.mPendingEvictionTasks.remove(str)) == null) {
            return;
        }
        scheduledFutureRemove.cancel(true);
    }

    public boolean isTitleCachedOrPrepared(@Nonnull String str) {
        boolean z;
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(str, "titleId");
            boolean zContainsKey = this.mCachedSessions.containsKey(str);
            boolean zEquals = str.equals(this.mPreparedTitleId);
            logLiveCacheOperation(String.format(Locale.US, "isTitleCachedOrPrepared cached:%s, prepared:%s", Boolean.valueOf(zContainsKey), Boolean.valueOf(zEquals)), str);
            z = zContainsKey || zEquals;
        }
        return z;
    }

    public int getCurrentCacheSize() {
        int size;
        synchronized (this.mMutex) {
            size = this.mCachedSessions.size();
        }
        return size;
    }
}
