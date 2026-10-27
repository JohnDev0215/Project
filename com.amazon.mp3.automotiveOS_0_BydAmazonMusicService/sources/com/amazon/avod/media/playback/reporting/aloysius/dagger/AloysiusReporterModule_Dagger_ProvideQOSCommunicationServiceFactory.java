package com.amazon.avod.media.playback.reporting.aloysius.dagger;

import com.amazon.avod.media.playback.QOSCommunicationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class AloysiusReporterModule_Dagger_ProvideQOSCommunicationServiceFactory implements Factory<QOSCommunicationService> {
    private final AloysiusReporterModule_Dagger module;

    public AloysiusReporterModule_Dagger_ProvideQOSCommunicationServiceFactory(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger) {
        this.module = aloysiusReporterModule_Dagger;
    }

    @Override // javax.inject.Provider
    public QOSCommunicationService get() {
        return (QOSCommunicationService) Preconditions.checkNotNull(this.module.provideQOSCommunicationService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<QOSCommunicationService> create(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger) {
        return new AloysiusReporterModule_Dagger_ProvideQOSCommunicationServiceFactory(aloysiusReporterModule_Dagger);
    }
}
