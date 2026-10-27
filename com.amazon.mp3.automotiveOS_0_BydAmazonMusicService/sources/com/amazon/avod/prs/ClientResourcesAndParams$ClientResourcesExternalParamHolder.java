package com.amazon.avod.prs;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.google.common.base.Joiner;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ClientResourcesAndParams$ClientResourcesExternalParamHolder {
    private static AdvertisingIdCollector mAdsIdCollector;
    private static long mAdsIdCollectorTimeoutMillis;
    private static final Joiner mJoiner = Joiner.on(",").useForNull("");
    private static final CountDownLatch mInitializationLatch = new CountDownLatch(1);
    private static final CountDownLatch mAdsInitializationLatch = new CountDownLatch(1);

    public static void adsInitialize(@Nonnull AdvertisingIdCollector advertisingIdCollector, long j) {
        mAdsIdCollector = advertisingIdCollector;
        mAdsIdCollectorTimeoutMillis = j;
        mAdsInitializationLatch.countDown();
    }
}
