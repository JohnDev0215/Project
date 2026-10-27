package org.chromium.ui.gl;

import android.view.SurfaceControl;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class ScopedJavaSurfaceControl {
    @CalledByNative
    public static void releaseSurfaceControl(SurfaceControl surfaceControl) {
        surfaceControl.release();
    }
}
