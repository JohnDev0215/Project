package org.chromium.mojo.system.impl;

import android.util.Log;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.UntypedHandle;

/* JADX INFO: loaded from: classes3.dex */
abstract class HandleBase implements Handle {
    private static final String TAG = "HandleImpl";
    protected CoreImpl mCore;
    private int mMojoHandle;

    HandleBase(CoreImpl coreImpl, int i) {
        this.mCore = coreImpl;
        this.mMojoHandle = i;
    }

    protected HandleBase(HandleBase handleBase) {
        this.mCore = handleBase.mCore;
        int i = handleBase.mMojoHandle;
        handleBase.mMojoHandle = 0;
        this.mMojoHandle = i;
    }

    @Override // org.chromium.mojo.system.Handle, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int i = this.mMojoHandle;
        if (i != 0) {
            this.mMojoHandle = 0;
            this.mCore.close(i);
        }
    }

    @Override // org.chromium.mojo.system.Handle
    public Core.WaitResult wait(Core.HandleSignals handleSignals, long j) {
        return this.mCore.wait(this, handleSignals, j);
    }

    @Override // org.chromium.mojo.system.Handle
    public boolean isValid() {
        return this.mMojoHandle != 0;
    }

    @Override // org.chromium.mojo.system.Handle
    public UntypedHandle toUntypedHandle() {
        return new UntypedHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.Handle
    public Core getCore() {
        return this.mCore;
    }

    @Override // org.chromium.mojo.system.Handle
    public int releaseNativeHandle() {
        int i = this.mMojoHandle;
        this.mMojoHandle = 0;
        return i;
    }

    int getMojoHandle() {
        return this.mMojoHandle;
    }

    void invalidateHandle() {
        this.mMojoHandle = 0;
    }

    protected final void finalize() throws Throwable {
        if (isValid()) {
            Log.w(TAG, "Handle was not closed.");
            this.mCore.closeWithResult(this.mMojoHandle);
        }
        super.finalize();
    }
}
