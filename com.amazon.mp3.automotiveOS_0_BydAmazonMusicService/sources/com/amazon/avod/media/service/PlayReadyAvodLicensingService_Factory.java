package com.amazon.avod.media.service;

import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class PlayReadyAvodLicensingService_Factory implements Factory<PlayReadyAvodLicensingService> {
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;
    private final Provider<PlaybackSupportEvaluator> supportEvaluatorProvider;

    public PlayReadyAvodLicensingService_Factory(Provider<PlaybackSupportEvaluator> provider, Provider<QOSCommunicationService> provider2) {
        this.supportEvaluatorProvider = provider;
        this.qosCommunicationServiceProvider = provider2;
    }

    @Override // javax.inject.Provider
    public PlayReadyAvodLicensingService get() {
        return new PlayReadyAvodLicensingService(this.supportEvaluatorProvider.get(), this.qosCommunicationServiceProvider.get());
    }

    public static Factory<PlayReadyAvodLicensingService> create(Provider<PlaybackSupportEvaluator> provider, Provider<QOSCommunicationService> provider2) {
        return new PlayReadyAvodLicensingService_Factory(provider, provider2);
    }
}
