package org.chromium.mojo.system.impl;

import java.io.Closeable;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class BaseRunLoop implements Closeable {
    @CalledByNative
    public static void runRunnable(Runnable runnable) {
        runnable.run();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
