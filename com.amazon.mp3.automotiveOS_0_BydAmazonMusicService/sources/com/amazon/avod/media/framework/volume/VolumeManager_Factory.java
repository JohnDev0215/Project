package com.amazon.avod.media.framework.volume;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeManager_Factory implements Factory<VolumeManager> {
    private final Provider<Context> contextProvider;

    public VolumeManager_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public VolumeManager get() {
        return new VolumeManager(this.contextProvider.get());
    }

    public static Factory<VolumeManager> create(Provider<Context> provider) {
        return new VolumeManager_Factory(provider);
    }
}
