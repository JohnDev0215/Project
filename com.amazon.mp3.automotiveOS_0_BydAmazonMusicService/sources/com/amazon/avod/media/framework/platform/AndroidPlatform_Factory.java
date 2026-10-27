package com.amazon.avod.media.framework.platform;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidPlatform_Factory implements Factory<AndroidPlatform> {
    private static final AndroidPlatform_Factory INSTANCE = new AndroidPlatform_Factory();

    @Override // javax.inject.Provider
    public AndroidPlatform get() {
        return new AndroidPlatform();
    }

    public static Factory<AndroidPlatform> create() {
        return INSTANCE;
    }
}
