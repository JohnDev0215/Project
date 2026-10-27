package org.chromium.content.app;

import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ContentMain {
    private static native int nativeStart();

    public static int start() {
        return nativeStart();
    }
}
