package com.amazon.avod.media.playback.reporting.aloysius.dagger;

import android.content.Context;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDeviceReporter;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AloysiusReporterModule_Dagger_ProvideAloysiusDeviceReporterFactory implements Factory<AloysiusDeviceReporter> {
    private final Provider<Context> contextProvider;
    private final Provider<MediaEventQueue> mediaEventQueueProvider;
    private final AloysiusReporterModule_Dagger module;
    private final Provider<RendererSchemeController> rendererSchemeControllerProvider;

    public AloysiusReporterModule_Dagger_ProvideAloysiusDeviceReporterFactory(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger, Provider<MediaEventQueue> provider, Provider<Context> provider2, Provider<RendererSchemeController> provider3) {
        this.module = aloysiusReporterModule_Dagger;
        this.mediaEventQueueProvider = provider;
        this.contextProvider = provider2;
        this.rendererSchemeControllerProvider = provider3;
    }

    @Override // javax.inject.Provider
    public AloysiusDeviceReporter get() {
        return (AloysiusDeviceReporter) Preconditions.checkNotNull(this.module.provideAloysiusDeviceReporter(this.mediaEventQueueProvider.get(), this.contextProvider.get(), this.rendererSchemeControllerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AloysiusDeviceReporter> create(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger, Provider<MediaEventQueue> provider, Provider<Context> provider2, Provider<RendererSchemeController> provider3) {
        return new AloysiusReporterModule_Dagger_ProvideAloysiusDeviceReporterFactory(aloysiusReporterModule_Dagger, provider, provider2, provider3);
    }
}
