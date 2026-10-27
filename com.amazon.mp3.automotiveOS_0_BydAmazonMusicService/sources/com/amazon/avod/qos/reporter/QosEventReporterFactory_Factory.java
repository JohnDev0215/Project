package com.amazon.avod.qos.reporter;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.playback.capability.BatteryInfoProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class QosEventReporterFactory_Factory implements Factory<QosEventReporterFactory> {
    private final Provider<BatteryInfoProvider> batteryInfoProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public QosEventReporterFactory_Factory(Provider<MediaSystemSharedContext> provider, Provider<BatteryInfoProvider> provider2) {
        this.sharedContextProvider = provider;
        this.batteryInfoProvider = provider2;
    }

    @Override // javax.inject.Provider
    public QosEventReporterFactory get() {
        return new QosEventReporterFactory(this.sharedContextProvider.get(), this.batteryInfoProvider.get());
    }

    public static Factory<QosEventReporterFactory> create(Provider<MediaSystemSharedContext> provider, Provider<BatteryInfoProvider> provider2) {
        return new QosEventReporterFactory_Factory(provider, provider2);
    }
}
