package org.chromium.ui;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public interface OverscrollRefreshHandler {
    @CalledByNative
    void pull(float f, float f2);

    @CalledByNative
    void release(boolean z);

    @CalledByNative
    void reset();

    @CalledByNative
    boolean start(int i, float f, float f2, boolean z);
}
