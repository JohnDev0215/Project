package com.amazon.avod.content;

import com.amazon.avod.media.service.playbackoptimizationservice.DefaultHeuristicsConfigurationSynchronizer;
import com.amazon.avod.smoothstream.DashContentSessionFactory;
import com.amazon.avod.smoothstream.DashDownloadContentSessionFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class CompositeContentSessionFactory_Factory implements Factory<CompositeContentSessionFactory> {
    private final Provider<DashContentSessionFactory> dashContentSessionFactoryProvider;
    private final Provider<DashDownloadContentSessionFactory> dashDownloadContentSessionFactoryProvider;
    private final Provider<DashMiyagiContentSessionFactory> dashMiyagiContentSessionFactoryProvider;
    private final Provider<DefaultHeuristicsConfigurationSynchronizer> defaultHeuristicsConfigurationSynchronizerProvider;
    private final Provider<SmoothStreamingDashHeuristicsContentSesionFactory> smoothStreamingDashHeuristicsContentSessionFactoryProvider;

    public CompositeContentSessionFactory_Factory(Provider<DashContentSessionFactory> provider, Provider<DashMiyagiContentSessionFactory> provider2, Provider<SmoothStreamingDashHeuristicsContentSesionFactory> provider3, Provider<DashDownloadContentSessionFactory> provider4, Provider<DefaultHeuristicsConfigurationSynchronizer> provider5) {
        this.dashContentSessionFactoryProvider = provider;
        this.dashMiyagiContentSessionFactoryProvider = provider2;
        this.smoothStreamingDashHeuristicsContentSessionFactoryProvider = provider3;
        this.dashDownloadContentSessionFactoryProvider = provider4;
        this.defaultHeuristicsConfigurationSynchronizerProvider = provider5;
    }

    @Override // javax.inject.Provider
    public CompositeContentSessionFactory get() {
        return new CompositeContentSessionFactory(this.dashContentSessionFactoryProvider.get(), this.dashMiyagiContentSessionFactoryProvider.get(), this.smoothStreamingDashHeuristicsContentSessionFactoryProvider.get(), this.dashDownloadContentSessionFactoryProvider.get(), this.defaultHeuristicsConfigurationSynchronizerProvider.get());
    }

    public static Factory<CompositeContentSessionFactory> create(Provider<DashContentSessionFactory> provider, Provider<DashMiyagiContentSessionFactory> provider2, Provider<SmoothStreamingDashHeuristicsContentSesionFactory> provider3, Provider<DashDownloadContentSessionFactory> provider4, Provider<DefaultHeuristicsConfigurationSynchronizer> provider5) {
        return new CompositeContentSessionFactory_Factory(provider, provider2, provider3, provider4, provider5);
    }
}
