package com.android.car.ui;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
class RotaryCache {
    static final int CACHE_TYPE_DISABLED = 1;
    static final int CACHE_TYPE_EXPIRED_AFTER_SOME_TIME = 2;
    static final int CACHE_TYPE_NEVER_EXPIRE = 3;
    private final FocusAreaCache mFocusAreaCache;
    private final FocusCache mFocusCache;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheType {
    }

    private static class FocusHistory {
        final View mFocusedView;
        final long mTimestamp;

        FocusHistory(View focusedView, long timestamp) {
            this.mFocusedView = focusedView;
            this.mTimestamp = timestamp;
        }
    }

    static class FocusCache {
        final int mCacheType;
        long mExpirationPeriodMs;
        FocusHistory mFocusHistory;

        FocusCache(int cacheType, long expirationPeriodMs) {
            this.mCacheType = cacheType;
            this.mExpirationPeriodMs = expirationPeriodMs;
            if (cacheType == 2 && expirationPeriodMs <= 0) {
                throw new IllegalArgumentException("Expiration time must be positive if CacheType is CACHE_TYPE_EXPIRED_AFTER_SOME_TIME");
            }
        }

        View getFocusedView(long elapsedRealtime) {
            if (isValidHistory(elapsedRealtime)) {
                return this.mFocusHistory.mFocusedView;
            }
            return null;
        }

        void setFocusedView(View focusedView, long elapsedRealtime) {
            FocusHistory focusHistory;
            if (this.mCacheType == 1) {
                return;
            }
            if (focusedView != null) {
                focusHistory = new FocusHistory(focusedView, elapsedRealtime);
            } else {
                focusHistory = null;
            }
            this.mFocusHistory = focusHistory;
        }

        boolean isValidHistory(long elapsedRealtime) {
            FocusHistory focusHistory = this.mFocusHistory;
            if (focusHistory == null) {
                return false;
            }
            int i = this.mCacheType;
            return i != 2 ? i == 3 : elapsedRealtime < focusHistory.mTimestamp + this.mExpirationPeriodMs;
        }
    }

    private static class FocusAreaHistory {
        final FocusArea mFocusArea;
        final long mTimestamp;

        FocusAreaHistory(FocusArea focusArea, long timestamp) {
            this.mFocusArea = focusArea;
            this.mTimestamp = timestamp;
        }
    }

    private static class FocusAreaCache extends HashMap<Integer, FocusAreaHistory> {
        private final int mCacheType;
        private final int mExpirationPeriodMs;

        FocusAreaCache(int cacheType, int expirationPeriodMs) {
            this.mCacheType = cacheType;
            this.mExpirationPeriodMs = expirationPeriodMs;
            if (cacheType == 2 && expirationPeriodMs <= 0) {
                throw new IllegalArgumentException("Expiration time must be positive if CacheType is CACHE_TYPE_EXPIRED_AFTER_SOME_TIME");
            }
        }

        void put(int direction, FocusArea targetFocusArea, long elapsedRealtime) {
            if (this.mCacheType == 1) {
                return;
            }
            put(Integer.valueOf(direction), new FocusAreaHistory(targetFocusArea, elapsedRealtime));
        }

        FocusArea get(int direction, long elapsedRealtime) {
            FocusAreaHistory history = get(Integer.valueOf(direction));
            if (isValidHistory(history, elapsedRealtime)) {
                return history.mFocusArea;
            }
            return null;
        }

        boolean isValidHistory(FocusAreaHistory history, long elapsedRealtime) {
            if (history == null) {
                return false;
            }
            int i = this.mCacheType;
            return i != 2 ? i == 3 : elapsedRealtime - history.mTimestamp < ((long) this.mExpirationPeriodMs);
        }
    }

    RotaryCache(int focusHistoryCacheType, int focusHistoryExpirationPeriodMs, int focusAreaHistoryCacheType, int focusAreaHistoryExpirationPeriodMs) {
        this.mFocusCache = new FocusCache(focusHistoryCacheType, focusHistoryExpirationPeriodMs);
        this.mFocusAreaCache = new FocusAreaCache(focusAreaHistoryCacheType, focusAreaHistoryExpirationPeriodMs);
    }

    View getFocusedView(long elapsedRealtime) {
        return this.mFocusCache.getFocusedView(elapsedRealtime);
    }

    void saveFocusedView(View view, long elapsedRealtime) {
        this.mFocusCache.setFocusedView(view, elapsedRealtime);
    }

    FocusArea getCachedFocusArea(int direction, long elapsedRealtime) {
        return this.mFocusAreaCache.get(direction, elapsedRealtime);
    }

    void saveFocusArea(int direction, FocusArea targetFocusArea, long elapsedRealtime) {
        this.mFocusAreaCache.put(direction, targetFocusArea, elapsedRealtime);
    }

    void clearFocusAreaHistory() {
        this.mFocusAreaCache.clear();
    }
}
