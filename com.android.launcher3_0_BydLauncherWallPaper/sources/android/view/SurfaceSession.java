package android.view;

/* JADX INFO: loaded from: classes3.dex */
public final class SurfaceSession {
    private long mNativeClient = nativeCreate();

    private static native long nativeCreate();

    private static native void nativeDestroy(long j);

    private static native void nativeKill(long j);

    protected void finalize() throws Throwable {
        try {
            long j = this.mNativeClient;
            if (j != 0) {
                nativeDestroy(j);
            }
        } finally {
            super.finalize();
        }
    }

    public void kill() {
        nativeKill(this.mNativeClient);
    }
}
