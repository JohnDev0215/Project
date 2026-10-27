package com.amazon.avod.media.framework.platform.power;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidLockFactory_Factory implements Factory<AndroidLockFactory> {
    private final Provider<Context> contextProvider;

    public AndroidLockFactory_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public AndroidLockFactory get() {
        return new AndroidLockFactory(this.contextProvider.get());
    }

    public static Factory<AndroidLockFactory> create(Provider<Context> provider) {
        return new AndroidLockFactory_Factory(provider);
    }
}
