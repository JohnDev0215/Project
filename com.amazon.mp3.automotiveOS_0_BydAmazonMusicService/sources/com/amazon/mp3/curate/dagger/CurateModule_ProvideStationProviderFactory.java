package com.amazon.mp3.curate.dagger;

import android.content.Context;
import com.amazon.music.curate.provider.StationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideStationProviderFactory implements Factory<StationProvider> {
    public static StationProvider provideStationProvider(CurateModule instance, Context context) {
        return (StationProvider) Preconditions.checkNotNullFromProvides(instance.provideStationProvider(context));
    }
}
