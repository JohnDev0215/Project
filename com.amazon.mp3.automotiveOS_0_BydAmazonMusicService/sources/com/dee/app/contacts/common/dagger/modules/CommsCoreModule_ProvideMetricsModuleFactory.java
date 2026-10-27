package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public final class CommsCoreModule_ProvideMetricsModuleFactory implements Factory<AlexaCommsCoreMetricsService> {
    private final CommsCoreModule module;

    public CommsCoreModule_ProvideMetricsModuleFactory(CommsCoreModule commsCoreModule) {
        this.module = commsCoreModule;
    }

    @Override // javax.inject.Provider
    public AlexaCommsCoreMetricsService get() {
        return (AlexaCommsCoreMetricsService) Preconditions.checkNotNull(this.module.provideMetricsModule(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AlexaCommsCoreMetricsService> create(CommsCoreModule commsCoreModule) {
        return new CommsCoreModule_ProvideMetricsModuleFactory(commsCoreModule);
    }
}
