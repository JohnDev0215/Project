package org.chromium.mojo.system.impl;

import java.io.Closeable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BaseRunLoop implements Closeable {
    public long s;

    public static void runRunnable(Runnable runnable) {
        runnable.run();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.s != 0) {
            throw null;
        }
    }
}
