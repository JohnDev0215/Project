package org.chromium.chrome.browser.layouts.scene_layer;

import J.N;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class SceneLayer {
    public long e;

    public SceneLayer() {
        d();
    }

    public void d() {
        if (this.e == 0) {
            this.e = N.Mw0UUAvK(this);
        }
    }

    @CalledByNative
    public final long getNativePtr() {
        return this.e;
    }

    @CalledByNative
    public final void setNativePtr(long j) {
        this.e = j;
    }
}
