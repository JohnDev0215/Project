package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.provider.NetworkProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideNetworkProviderFactory implements Factory<NetworkProvider> {
    public static NetworkProvider provideNetworkProvider(CurateModule instance) {
        return (NetworkProvider) Preconditions.checkNotNullFromProvides(instance.provideNetworkProvider());
    }
}
