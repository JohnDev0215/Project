package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideCapabilitiesInfoFactory implements Factory<CapabilitiesInfo> {
    private final Provider<Context> contextProvider;
    private final FindModule module;

    public FindModule_ProvideCapabilitiesInfoFactory(FindModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public CapabilitiesInfo get() {
        return provideCapabilitiesInfo(this.module, this.contextProvider.get());
    }

    public static FindModule_ProvideCapabilitiesInfoFactory create(FindModule module, Provider<Context> contextProvider) {
        return new FindModule_ProvideCapabilitiesInfoFactory(module, contextProvider);
    }

    public static CapabilitiesInfo provideCapabilitiesInfo(FindModule instance, Context context) {
        return (CapabilitiesInfo) Preconditions.checkNotNullFromProvides(instance.provideCapabilitiesInfo(context));
    }
}
