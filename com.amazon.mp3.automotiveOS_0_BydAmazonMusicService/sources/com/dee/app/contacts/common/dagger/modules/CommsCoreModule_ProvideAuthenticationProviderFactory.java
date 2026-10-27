package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes5.dex */
public final class CommsCoreModule_ProvideAuthenticationProviderFactory implements Factory<AuthenticationProvider> {
    private final Provider<AlexaCommsCoreRemoteConfigurationService> configurationServiceProvider;
    private final Provider<AlexaCommsCoreIdentityService> identityServiceProvider;
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceProvider;
    private final CommsCoreModule module;

    public CommsCoreModule_ProvideAuthenticationProviderFactory(CommsCoreModule commsCoreModule, Provider<AlexaCommsCoreIdentityService> provider, Provider<AlexaCommsCoreRemoteConfigurationService> provider2, Provider<AlexaCommsCoreMetricsService> provider3) {
        this.module = commsCoreModule;
        this.identityServiceProvider = provider;
        this.configurationServiceProvider = provider2;
        this.metricsServiceProvider = provider3;
    }

    @Override // javax.inject.Provider
    public AuthenticationProvider get() {
        return (AuthenticationProvider) Preconditions.checkNotNull(this.module.provideAuthenticationProvider(this.identityServiceProvider.get(), this.configurationServiceProvider.get(), this.metricsServiceProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AuthenticationProvider> create(CommsCoreModule commsCoreModule, Provider<AlexaCommsCoreIdentityService> provider, Provider<AlexaCommsCoreRemoteConfigurationService> provider2, Provider<AlexaCommsCoreMetricsService> provider3) {
        return new CommsCoreModule_ProvideAuthenticationProviderFactory(commsCoreModule, provider, provider2, provider3);
    }
}
