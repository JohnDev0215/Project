package org.chromium.content.browser;

import J.N;
import defpackage.n3;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class HostZoomMapImpl {
    @CalledByNative
    public static double getAdjustedZoomLevel(double d, double d2) {
        float f = n3.l;
        if (!N.Mudil8Bg("AccessibilityPageZoom")) {
            f = 1.0f;
        }
        return n3.m(d, f, (float) d2);
    }
}
