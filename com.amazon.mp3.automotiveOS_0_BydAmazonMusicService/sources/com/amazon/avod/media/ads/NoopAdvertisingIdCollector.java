package com.amazon.avod.media.ads;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NoopAdvertisingIdCollector implements AdvertisingIdCollector {
    private static final AdvertisingIdCollector.AdvertisingInfo DEFAULT_AD_INFO = new AdvertisingIdCollector.AdvertisingInfo("DefaultAdId", Boolean.TRUE);

    @Override // com.amazon.avod.media.ads.AdvertisingIdCollector
    @Nonnull
    public AdvertisingIdCollector.AdvertisingInfo get(long j, boolean z) {
        return DEFAULT_AD_INFO;
    }
}
