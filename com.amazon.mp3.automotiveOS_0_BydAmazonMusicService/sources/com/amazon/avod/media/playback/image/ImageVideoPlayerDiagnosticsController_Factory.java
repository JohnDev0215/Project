package com.amazon.avod.media.playback.image;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes2.dex */
public final class ImageVideoPlayerDiagnosticsController_Factory implements Factory<ImageVideoPlayerDiagnosticsController> {
    private static final ImageVideoPlayerDiagnosticsController_Factory INSTANCE = new ImageVideoPlayerDiagnosticsController_Factory();

    @Override // javax.inject.Provider
    public ImageVideoPlayerDiagnosticsController get() {
        return new ImageVideoPlayerDiagnosticsController();
    }

    public static Factory<ImageVideoPlayerDiagnosticsController> create() {
        return INSTANCE;
    }
}
