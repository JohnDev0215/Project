package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes5.dex */
public final class CommsCoreModule_ProvideAlexaCommsCoreRemoteConfigurationServiceFactory implements Factory<AlexaCommsCoreRemoteConfigurationService> {
    private final CommsCoreModule module;

    public CommsCoreModule_ProvideAlexaCommsCoreRemoteConfigurationServiceFactory(CommsCoreModule commsCoreModule) {
        this.module = commsCoreModule;
    }

    @Override // javax.inject.Provider
    public AlexaCommsCoreRemoteConfigurationService get() {
        return this.module.provideAlexaCommsCoreRemoteConfigurationService();
    }

    public static Factory<AlexaCommsCoreRemoteConfigurationService> create(CommsCoreModule commsCoreModule) {
        return new CommsCoreModule_ProvideAlexaCommsCoreRemoteConfigurationServiceFactory(commsCoreModule);
    }
}
