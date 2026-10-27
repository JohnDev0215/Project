package com.amazon.avod.media.framework.network;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MultiNetworkHistoryManager_Factory implements Factory<MultiNetworkHistoryManager> {
    private final Provider<Context> contextProvider;

    public MultiNetworkHistoryManager_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public MultiNetworkHistoryManager get() {
        return new MultiNetworkHistoryManager(this.contextProvider.get());
    }

    public static Factory<MultiNetworkHistoryManager> create(Provider<Context> provider) {
        return new MultiNetworkHistoryManager_Factory(provider);
    }
}
