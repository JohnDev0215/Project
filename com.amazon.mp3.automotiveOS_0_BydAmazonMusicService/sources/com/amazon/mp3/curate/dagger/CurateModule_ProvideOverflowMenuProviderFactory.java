package com.amazon.mp3.curate.dagger;

import android.content.Context;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvideOverflowMenuProviderFactory implements Factory<OverflowMenuProvider> {
    public static OverflowMenuProvider provideOverflowMenuProvider(CurateModule instance, Context context) {
        return (OverflowMenuProvider) Preconditions.checkNotNullFromProvides(instance.provideOverflowMenuProvider(context));
    }
}
