package com.amazon.avod.media.service.playbackoptimizationservice;

import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class DefaultHeuristicsConfigurationSynchronizer_Factory implements Factory<DefaultHeuristicsConfigurationSynchronizer> {
    private final Provider<EventReporterFactory> eventReporterFactoryProvider;

    public DefaultHeuristicsConfigurationSynchronizer_Factory(Provider<EventReporterFactory> provider) {
        this.eventReporterFactoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public DefaultHeuristicsConfigurationSynchronizer get() {
        return new DefaultHeuristicsConfigurationSynchronizer(this.eventReporterFactoryProvider.get());
    }

    public static Factory<DefaultHeuristicsConfigurationSynchronizer> create(Provider<EventReporterFactory> provider) {
        return new DefaultHeuristicsConfigurationSynchronizer_Factory(provider);
    }
}
