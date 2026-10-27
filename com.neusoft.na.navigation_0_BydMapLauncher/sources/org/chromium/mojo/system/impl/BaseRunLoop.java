package org.chromium.mojo.system.impl;

import org.chromium.base.annotations.JNINamespace;
import org.chromium.mojo.system.RunLoop;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("mojo::android")
class BaseRunLoop implements RunLoop {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final CoreImpl mCore;
    private long mRunLoopID = nativeCreateBaseRunLoop();

    private native long nativeCreateBaseRunLoop();

    private native void nativeDeleteMessageLoop(long j);

    private native void nativePostDelayedTask(long j, Runnable runnable, long j2);

    private native void nativeQuit(long j);

    private native void nativeRun(long j);

    private native void nativeRunUntilIdle(long j);

    BaseRunLoop(CoreImpl coreImpl) {
        this.mCore = coreImpl;
    }

    @Override // org.chromium.mojo.system.RunLoop
    public void run() {
        nativeRun(this.mRunLoopID);
    }

    @Override // org.chromium.mojo.system.RunLoop
    public void runUntilIdle() {
        nativeRunUntilIdle(this.mRunLoopID);
    }

    @Override // org.chromium.mojo.system.RunLoop
    public void quit() {
        nativeQuit(this.mRunLoopID);
    }

    @Override // org.chromium.mojo.system.RunLoop
    public void postDelayedTask(Runnable runnable, long j) {
        nativePostDelayedTask(this.mRunLoopID, runnable, j);
    }

    @Override // org.chromium.mojo.system.RunLoop, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.mRunLoopID == 0) {
            return;
        }
        this.mCore.clearCurrentRunLoop();
        nativeDeleteMessageLoop(this.mRunLoopID);
        this.mRunLoopID = 0L;
    }

    private static void runRunnable(Runnable runnable) {
        runnable.run();
    }
}
