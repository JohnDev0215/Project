package android.view;

import android.os.IBinder;

/* JADX INFO: loaded from: classes3.dex */
public final class InputApplicationHandle {
    public final long dispatchingTimeoutNanos;
    public final String name;
    private long ptr;
    public final IBinder token;

    private native void nativeDispose();

    public InputApplicationHandle(IBinder iBinder, String str, long j) {
        this.token = iBinder;
        this.name = str;
        this.dispatchingTimeoutNanos = j;
    }

    public InputApplicationHandle(InputApplicationHandle inputApplicationHandle) {
        this.token = inputApplicationHandle.token;
        this.dispatchingTimeoutNanos = inputApplicationHandle.dispatchingTimeoutNanos;
        this.name = inputApplicationHandle.name;
    }

    protected void finalize() throws Throwable {
        try {
            nativeDispose();
        } finally {
            super.finalize();
        }
    }
}
