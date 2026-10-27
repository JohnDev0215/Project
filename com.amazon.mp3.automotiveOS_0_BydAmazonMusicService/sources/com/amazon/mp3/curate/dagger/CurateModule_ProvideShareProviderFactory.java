package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.provider.ShareProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideShareProviderFactory implements Factory<ShareProvider> {
    public static ShareProvider provideShareProvider(CurateModule instance) {
        return (ShareProvider) Preconditions.checkNotNullFromProvides(instance.provideShareProvider());
    }
}
