package android.view;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import dalvik.annotation.optimization.FastNative;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DisplayEventReceiver {
    public static final int CONFIG_CHANGED_EVENT_DISPATCH = 1;
    public static final int CONFIG_CHANGED_EVENT_SUPPRESS = 0;
    private static final String TAG = "DisplayEventReceiver";
    public static final int VSYNC_SOURCE_APP = 0;
    public static final int VSYNC_SOURCE_SURFACE_FLINGER = 1;
    private final CloseGuard mCloseGuard;
    private MessageQueue mMessageQueue;
    private long mReceiverPtr;

    private static native void nativeDispose(long j);

    private static native long nativeInit(WeakReference<DisplayEventReceiver> weakReference, MessageQueue messageQueue, int i, int i2);

    @FastNative
    private static native void nativeScheduleVsync(long j);

    public void onConfigChanged(long j, long j2, int i) {
    }

    public void onHotplug(long j, long j2, boolean z) {
    }

    public void onVsync(long j, long j2, int i) {
    }

    public DisplayEventReceiver(Looper looper) {
        this(looper, 0, 0);
    }

    public DisplayEventReceiver(Looper looper, int i, int i2) {
        CloseGuard closeGuard = CloseGuard.get();
        this.mCloseGuard = closeGuard;
        if (looper == null) {
            throw new IllegalArgumentException("looper must not be null");
        }
        this.mMessageQueue = looper.getQueue();
        this.mReceiverPtr = nativeInit(new WeakReference(this), this.mMessageQueue, i, i2);
        closeGuard.open("dispose");
    }

    protected void finalize() throws Throwable {
        try {
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    public void dispose() {
        dispose(false);
    }

    private void dispose(boolean z) {
        CloseGuard closeGuard = this.mCloseGuard;
        if (closeGuard != null) {
            if (z) {
                closeGuard.warnIfOpen();
            }
            this.mCloseGuard.close();
        }
        long j = this.mReceiverPtr;
        if (j != 0) {
            nativeDispose(j);
            this.mReceiverPtr = 0L;
        }
        this.mMessageQueue = null;
    }

    public void scheduleVsync() {
        long j = this.mReceiverPtr;
        if (j == 0) {
            Log.w(TAG, "Attempted to schedule a vertical sync pulse but the display event receiver has already been disposed.");
        } else {
            nativeScheduleVsync(j);
        }
    }

    private void dispatchVsync(long j, long j2, int i) {
        onVsync(j, j2, i);
    }

    private void dispatchHotplug(long j, long j2, boolean z) {
        onHotplug(j, j2, z);
    }

    private void dispatchConfigChanged(long j, long j2, int i) {
        onConfigChanged(j, j2, i);
    }
}
