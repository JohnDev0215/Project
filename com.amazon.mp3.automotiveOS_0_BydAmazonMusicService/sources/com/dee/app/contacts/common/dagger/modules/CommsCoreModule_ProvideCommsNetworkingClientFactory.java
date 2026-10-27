package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes5.dex */
public final class CommsCoreModule_ProvideCommsNetworkingClientFactory implements Factory<INetworkingClient> {
    private final Provider<String> acmsHostUrlProvider;
    private final Provider<AuthenticationProvider> authenticationProvider;
    private final CommsCoreModule module;

    public CommsCoreModule_ProvideCommsNetworkingClientFactory(CommsCoreModule commsCoreModule, Provider<AuthenticationProvider> provider, Provider<String> provider2) {
        this.module = commsCoreModule;
        this.authenticationProvider = provider;
        this.acmsHostUrlProvider = provider2;
    }

    @Override // javax.inject.Provider
    public INetworkingClient get() {
        return (INetworkingClient) Preconditions.checkNotNull(this.module.provideCommsNetworkingClient(this.authenticationProvider.get(), this.acmsHostUrlProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<INetworkingClient> create(CommsCoreModule commsCoreModule, Provider<AuthenticationProvider> provider, Provider<String> provider2) {
        return new CommsCoreModule_ProvideCommsNetworkingClientFactory(commsCoreModule, provider, provider2);
    }
}
