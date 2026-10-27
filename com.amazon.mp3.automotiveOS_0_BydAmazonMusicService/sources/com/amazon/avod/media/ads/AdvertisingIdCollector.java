package com.amazon.avod.media.ads;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AdvertisingIdCollector {
    @Nonnull
    AdvertisingInfo get(long j, boolean z);

    public static class AdvertisingInfo {
        private final String mAdId;
        private final boolean mIsOptOut;

        public AdvertisingInfo(@Nonnull String str, Boolean bool) {
            this.mAdId = (String) Preconditions.checkNotNull(str, "adId");
            this.mIsOptOut = bool.booleanValue();
        }

        @Nullable
        public String getAdId() {
            return this.mAdId;
        }

        public boolean isOptOut() {
            return this.mIsOptOut;
        }
    }
}
