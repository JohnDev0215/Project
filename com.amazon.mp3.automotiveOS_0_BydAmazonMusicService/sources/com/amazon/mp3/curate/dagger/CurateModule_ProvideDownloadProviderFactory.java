package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.provider.DownloadProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideDownloadProviderFactory implements Factory<DownloadProvider> {
    public static DownloadProvider provideDownloadProvider(CurateModule instance) {
        return (DownloadProvider) Preconditions.checkNotNullFromProvides(instance.provideDownloadProvider());
    }
}
