package com.amazon.avod.media.playback.image;

import android.content.Context;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ImageVideoPresentationFactory_Factory implements Factory<ImageVideoPresentationFactory> {
    private final Provider<Context> contextProvider;
    private final Provider<ImageVideoPlayerDiagnosticsController> diagControllerProvider;
    private final Provider<ImageVideoPlayer> playerProvider;
    private final Provider<VideoPresentationEventReporter> presentationReporterProvider;

    public ImageVideoPresentationFactory_Factory(Provider<ImageVideoPlayer> provider, Provider<VideoPresentationEventReporter> provider2, Provider<ImageVideoPlayerDiagnosticsController> provider3, Provider<Context> provider4) {
        this.playerProvider = provider;
        this.presentationReporterProvider = provider2;
        this.diagControllerProvider = provider3;
        this.contextProvider = provider4;
    }

    @Override // javax.inject.Provider
    public ImageVideoPresentationFactory get() {
        return new ImageVideoPresentationFactory(this.playerProvider, this.presentationReporterProvider, this.diagControllerProvider, this.contextProvider.get());
    }

    public static Factory<ImageVideoPresentationFactory> create(Provider<ImageVideoPlayer> provider, Provider<VideoPresentationEventReporter> provider2, Provider<ImageVideoPlayerDiagnosticsController> provider3, Provider<Context> provider4) {
        return new ImageVideoPresentationFactory_Factory(provider, provider2, provider3, provider4);
    }
}
