package com.amazon.avod.media.ads.internal;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CacheStatus {
    private final float mPercentCached;
    public static final CacheStatus UNCACHED = new CacheStatus(0.0f);
    public static final CacheStatus FULLY_CACHED = new CacheStatus(100.0f);

    public CacheStatus(float f) {
        Preconditions.checkState(f >= 0.0f && f <= 100.0f, String.format(Locale.US, "Calculated percent cache %f; not >= 0 and <= 100", Float.valueOf(f)));
        this.mPercentCached = f;
    }

    public float getPercentCached() {
        return this.mPercentCached;
    }

    public boolean isUncached() {
        return this.mPercentCached == UNCACHED.getPercentCached();
    }

    public boolean isFullyCached() {
        return this.mPercentCached == FULLY_CACHED.getPercentCached();
    }

    @Nonnull
    public String toString() {
        float f = this.mPercentCached;
        return String.format(Locale.ENGLISH, "%s:%.2f", f == UNCACHED.mPercentCached ? "UNCACHED" : f == FULLY_CACHED.mPercentCached ? "FULLY_CACHED" : "PARTIALLY_CACHED", Float.valueOf(f));
    }
}
