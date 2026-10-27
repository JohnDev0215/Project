package com.amazon.avod.media.playback.android;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidVideoPlayerDiagnosticsController_Factory implements Factory<AndroidVideoPlayerDiagnosticsController> {
    private static final AndroidVideoPlayerDiagnosticsController_Factory INSTANCE = new AndroidVideoPlayerDiagnosticsController_Factory();

    @Override // javax.inject.Provider
    public AndroidVideoPlayerDiagnosticsController get() {
        return new AndroidVideoPlayerDiagnosticsController();
    }

    public static Factory<AndroidVideoPlayerDiagnosticsController> create() {
        return INSTANCE;
    }
}
