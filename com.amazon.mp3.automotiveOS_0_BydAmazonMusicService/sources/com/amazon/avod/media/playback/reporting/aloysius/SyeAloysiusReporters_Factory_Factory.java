package com.amazon.avod.media.playback.reporting.aloysius;

import android.content.Context;
import com.amazon.avod.media.events.AloysiusBootstrapLoader;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.PeriodicMediaEventScheduler;
import com.amazon.avod.media.playback.reporting.aloysius.SyeAloysiusReporters;
import dagger.internal.Factory;
import java.util.Set;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class SyeAloysiusReporters_Factory_Factory implements Factory<SyeAloysiusReporters.Factory> {
    private final Provider<AloysiusBootstrapLoader> aloysiusBootstrapLoaderProvider;
    private final Provider<AloysiusDeviceReporter> aloysiusDeviceReporterProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MediaEventQueue> eventQueueProvider;
    private final Provider<MediaEventDispatcher> mediaEventDispatcherProvider;
    private final Provider<PeriodicMediaEventScheduler> periodicSchedulerProvider;
    private final Provider<Set<MediaEventProducer>> producersProvider;

    public SyeAloysiusReporters_Factory_Factory(Provider<MediaEventQueue> provider, Provider<MediaEventDispatcher> provider2, Provider<PeriodicMediaEventScheduler> provider3, Provider<Set<MediaEventProducer>> provider4, Provider<AloysiusBootstrapLoader> provider5, Provider<Context> provider6, Provider<AloysiusDeviceReporter> provider7) {
        this.eventQueueProvider = provider;
        this.mediaEventDispatcherProvider = provider2;
        this.periodicSchedulerProvider = provider3;
        this.producersProvider = provider4;
        this.aloysiusBootstrapLoaderProvider = provider5;
        this.contextProvider = provider6;
        this.aloysiusDeviceReporterProvider = provider7;
    }

    @Override // javax.inject.Provider
    public SyeAloysiusReporters.Factory get() {
        return new SyeAloysiusReporters.Factory(this.eventQueueProvider.get(), this.mediaEventDispatcherProvider.get(), this.periodicSchedulerProvider, this.producersProvider.get(), this.aloysiusBootstrapLoaderProvider.get(), this.contextProvider.get(), this.aloysiusDeviceReporterProvider.get());
    }

    public static Factory<SyeAloysiusReporters.Factory> create(Provider<MediaEventQueue> provider, Provider<MediaEventDispatcher> provider2, Provider<PeriodicMediaEventScheduler> provider3, Provider<Set<MediaEventProducer>> provider4, Provider<AloysiusBootstrapLoader> provider5, Provider<Context> provider6, Provider<AloysiusDeviceReporter> provider7) {
        return new SyeAloysiusReporters_Factory_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
