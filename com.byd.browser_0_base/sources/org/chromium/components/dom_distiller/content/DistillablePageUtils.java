package org.chromium.components.dom_distiller.content;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public final class DistillablePageUtils {

    public interface PageDistillableDelegate {
        void n1(boolean z, boolean z2, boolean z3);
    }

    @CalledByNative
    public static void callOnIsPageDistillableUpdate(PageDistillableDelegate pageDistillableDelegate, boolean z, boolean z2, boolean z3) {
        if (pageDistillableDelegate != null) {
            pageDistillableDelegate.n1(z, z2, z3);
        }
    }
}
