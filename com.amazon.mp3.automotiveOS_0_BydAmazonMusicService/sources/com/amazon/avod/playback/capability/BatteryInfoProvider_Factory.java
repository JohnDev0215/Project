package com.amazon.avod.playback.capability;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class BatteryInfoProvider_Factory implements Factory<BatteryInfoProvider> {
    private final Provider<Context> contextProvider;

    public BatteryInfoProvider_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public BatteryInfoProvider get() {
        return new BatteryInfoProvider(this.contextProvider.get());
    }

    public static Factory<BatteryInfoProvider> create(Provider<Context> provider) {
        return new BatteryInfoProvider_Factory(provider);
    }
}
