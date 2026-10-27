package com.amazon.avod.media.framework;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaSystemSharedContext_Factory implements Factory<MediaSystemSharedContext> {
    private static final MediaSystemSharedContext_Factory INSTANCE = new MediaSystemSharedContext_Factory();

    @Override // javax.inject.Provider
    public MediaSystemSharedContext get() {
        return new MediaSystemSharedContext();
    }

    public static Factory<MediaSystemSharedContext> create() {
        return INSTANCE;
    }
}
