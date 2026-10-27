package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.content.CompositeContentSessionFactory;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.contentcache.internal.ClientCacheConfig;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.qos.SimpleVideoPresentationEventReporter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdaptiveStreamingModule_Dagger {
    private final AdvertisingIdCollector mAdvertisingIdCollector;

    ContentSessionFactory provideContentSessionFactory(CompositeContentSessionFactory compositeContentSessionFactory) {
        return compositeContentSessionFactory;
    }

    VideoPresentationEventReporter provideVideoPresentationEventReporter(SimpleVideoPresentationEventReporter simpleVideoPresentationEventReporter) {
        return simpleVideoPresentationEventReporter;
    }

    public AdaptiveStreamingModule_Dagger(@Nonnull AdvertisingIdCollector advertisingIdCollector) {
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
    }

    AdvertisingIdCollector provideAdIdProvider() {
        return this.mAdvertisingIdCollector;
    }

    ClientCacheConfig providePlaybackClientsConfig() {
        return new ClientCacheConfig();
    }
}
