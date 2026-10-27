package com.amazon.avod.playback.sye;

import android.content.Context;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.playback.reporting.aloysius.SyeAloysiusReporters;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class SyeVideoPresentationFactory_Factory implements Factory<SyeVideoPresentationFactory> {
    private final Provider<AdvertisingIdCollector> advertisingIdCollectorProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventReporterFactory> qosReporterFactoryProvider;
    private final Provider<SyeAloysiusReporters.Factory> reporterFactoryProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public SyeVideoPresentationFactory_Factory(Provider<SyeAloysiusReporters.Factory> provider, Provider<Context> provider2, Provider<EventReporterFactory> provider3, Provider<AdvertisingIdCollector> provider4, Provider<MediaSystemSharedContext> provider5) {
        this.reporterFactoryProvider = provider;
        this.contextProvider = provider2;
        this.qosReporterFactoryProvider = provider3;
        this.advertisingIdCollectorProvider = provider4;
        this.sharedContextProvider = provider5;
    }

    @Override // javax.inject.Provider
    public SyeVideoPresentationFactory get() {
        return new SyeVideoPresentationFactory(this.reporterFactoryProvider.get(), this.contextProvider.get(), this.qosReporterFactoryProvider.get(), this.advertisingIdCollectorProvider.get(), this.sharedContextProvider.get());
    }

    public static Factory<SyeVideoPresentationFactory> create(Provider<SyeAloysiusReporters.Factory> provider, Provider<Context> provider2, Provider<EventReporterFactory> provider3, Provider<AdvertisingIdCollector> provider4, Provider<MediaSystemSharedContext> provider5) {
        return new SyeVideoPresentationFactory_Factory(provider, provider2, provider3, provider4, provider5);
    }
}
