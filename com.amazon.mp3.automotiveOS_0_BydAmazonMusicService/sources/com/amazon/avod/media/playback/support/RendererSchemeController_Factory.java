package com.amazon.avod.media.playback.support;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes2.dex */
public final class RendererSchemeController_Factory implements Factory<RendererSchemeController> {
    private static final RendererSchemeController_Factory INSTANCE = new RendererSchemeController_Factory();

    @Override // javax.inject.Provider
    public RendererSchemeController get() {
        return new RendererSchemeController();
    }

    public static Factory<RendererSchemeController> create() {
        return INSTANCE;
    }
}
