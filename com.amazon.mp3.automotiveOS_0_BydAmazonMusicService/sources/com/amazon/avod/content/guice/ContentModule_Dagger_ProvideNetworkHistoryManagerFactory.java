package com.amazon.avod.content.guice;

import com.amazon.avod.media.framework.network.MultiNetworkHistoryManager;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvideNetworkHistoryManagerFactory implements Factory<NetworkHistoryManager> {
    private final Provider<MultiNetworkHistoryManager> managerProvider;
    private final ContentModule_Dagger module;

    public ContentModule_Dagger_ProvideNetworkHistoryManagerFactory(ContentModule_Dagger contentModule_Dagger, Provider<MultiNetworkHistoryManager> provider) {
        this.module = contentModule_Dagger;
        this.managerProvider = provider;
    }

    @Override // javax.inject.Provider
    public NetworkHistoryManager get() {
        return (NetworkHistoryManager) Preconditions.checkNotNull(this.module.provideNetworkHistoryManager(this.managerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<NetworkHistoryManager> create(ContentModule_Dagger contentModule_Dagger, Provider<MultiNetworkHistoryManager> provider) {
        return new ContentModule_Dagger_ProvideNetworkHistoryManagerFactory(contentModule_Dagger, provider);
    }
}
