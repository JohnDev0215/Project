package com.amazon.avod.media.service;

import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class WidevineAvodLicensingService_Factory implements Factory<WidevineAvodLicensingService> {
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;
    private final Provider<PlaybackSupportEvaluator> supportEvaluatorProvider;

    public WidevineAvodLicensingService_Factory(Provider<PlaybackSupportEvaluator> provider, Provider<QOSCommunicationService> provider2) {
        this.supportEvaluatorProvider = provider;
        this.qosCommunicationServiceProvider = provider2;
    }

    @Override // javax.inject.Provider
    public WidevineAvodLicensingService get() {
        return new WidevineAvodLicensingService(this.supportEvaluatorProvider.get(), this.qosCommunicationServiceProvider.get());
    }

    public static Factory<WidevineAvodLicensingService> create(Provider<PlaybackSupportEvaluator> provider, Provider<QOSCommunicationService> provider2) {
        return new WidevineAvodLicensingService_Factory(provider, provider2);
    }
}
