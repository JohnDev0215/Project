package org.chromium.media;

import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
class PhotoCapabilities {
    public final int currentZoom;
    public final int maxZoom;
    public final int minZoom;

    PhotoCapabilities(int i, int i2, int i3) {
        this.maxZoom = i;
        this.minZoom = i2;
        this.currentZoom = i3;
    }

    public int getMinZoom() {
        return this.minZoom;
    }

    public int getMaxZoom() {
        return this.maxZoom;
    }

    public int getCurrentZoom() {
        return this.currentZoom;
    }
}
