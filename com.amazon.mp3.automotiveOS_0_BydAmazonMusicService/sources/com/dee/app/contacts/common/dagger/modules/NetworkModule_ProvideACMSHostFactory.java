package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes5.dex */
public final class NetworkModule_ProvideACMSHostFactory implements Factory<String> {
    private final Provider<AlexaCommsCoreIdentityService> identityServiceProvider;
    private final NetworkModule module;
    private final Provider<AlexaCommsCoreRemoteConfigurationService> remoteConfigurationServiceProvider;

    public NetworkModule_ProvideACMSHostFactory(NetworkModule networkModule, Provider<AlexaCommsCoreRemoteConfigurationService> provider, Provider<AlexaCommsCoreIdentityService> provider2) {
        this.module = networkModule;
        this.remoteConfigurationServiceProvider = provider;
        this.identityServiceProvider = provider2;
    }

    @Override // javax.inject.Provider
    public String get() {
        return (String) Preconditions.checkNotNull(this.module.provideACMSHost(this.remoteConfigurationServiceProvider.get(), this.identityServiceProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> create(NetworkModule networkModule, Provider<AlexaCommsCoreRemoteConfigurationService> provider, Provider<AlexaCommsCoreIdentityService> provider2) {
        return new NetworkModule_ProvideACMSHostFactory(networkModule, provider, provider2);
    }
}
