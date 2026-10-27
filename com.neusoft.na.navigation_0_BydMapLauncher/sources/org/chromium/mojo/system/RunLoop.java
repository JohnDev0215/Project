package org.chromium.mojo.system;

import java.io.Closeable;

/* JADX INFO: loaded from: classes3.dex */
public interface RunLoop extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void postDelayedTask(Runnable runnable, long j);

    void quit();

    void run();

    void runUntilIdle();
}
