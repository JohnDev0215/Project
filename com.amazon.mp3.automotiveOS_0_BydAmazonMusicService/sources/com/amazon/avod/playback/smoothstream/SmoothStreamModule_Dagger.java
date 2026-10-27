package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamModule_Dagger {
    LoadableNativeLibrary provideSmoothStreamNativeLibrary() {
        return new SmoothStreamNativeLibrary();
    }
}
