package com.amazon.avod.smoothstream;

import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.smoothstream.dash.DashNativeLibrary;

/* JADX INFO: loaded from: classes2.dex */
public class DashModule_Dagger {
    LoadableNativeLibrary provideDashNativeLibrary() {
        return new DashNativeLibrary();
    }
}
