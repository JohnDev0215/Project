package com.amazon.avod.drm.playready;

import com.amazon.avod.drm.widevine.WidevineLicensingService;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class DrmSystemImpl_Factory implements Factory<DrmSystemImpl> {
    private final Provider<EventReporterFactory> eventReporterFactoryProvider;
    private final Provider<ExecutorService> executorServiceProvider;
    private final Provider<MediaProfiler> mediaProfilerProvider;
    private final Provider<PlayReadyLicensingService> playReadyLicensingServiceProvider;
    private final Provider<RendererSchemeController> rendererSchemeControllerProvider;
    private final Provider<WidevineLicensingService> widevineLicensingServiceProvider;

    public DrmSystemImpl_Factory(Provider<RendererSchemeController> provider, Provider<EventReporterFactory> provider2, Provider<ExecutorService> provider3, Provider<PlayReadyLicensingService> provider4, Provider<WidevineLicensingService> provider5, Provider<MediaProfiler> provider6) {
        this.rendererSchemeControllerProvider = provider;
        this.eventReporterFactoryProvider = provider2;
        this.executorServiceProvider = provider3;
        this.playReadyLicensingServiceProvider = provider4;
        this.widevineLicensingServiceProvider = provider5;
        this.mediaProfilerProvider = provider6;
    }

    @Override // javax.inject.Provider
    public DrmSystemImpl get() {
        return new DrmSystemImpl(this.rendererSchemeControllerProvider.get(), this.eventReporterFactoryProvider.get(), this.executorServiceProvider.get(), this.playReadyLicensingServiceProvider.get(), this.widevineLicensingServiceProvider.get(), this.mediaProfilerProvider.get());
    }

    public static Factory<DrmSystemImpl> create(Provider<RendererSchemeController> provider, Provider<EventReporterFactory> provider2, Provider<ExecutorService> provider3, Provider<PlayReadyLicensingService> provider4, Provider<WidevineLicensingService> provider5, Provider<MediaProfiler> provider6) {
        return new DrmSystemImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
