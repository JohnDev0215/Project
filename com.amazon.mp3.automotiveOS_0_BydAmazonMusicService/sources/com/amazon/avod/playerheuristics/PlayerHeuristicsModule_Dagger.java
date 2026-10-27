package com.amazon.avod.playerheuristics;

import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerHeuristicsModule_Dagger {
    LoadableNativeLibrary providePlayerHeuristicsNativeLibrary() {
        return new PlayerHeuristicsNativeLibrary();
    }
}
