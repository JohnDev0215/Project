package android.graphics;

import android.os.Handler;
import com.android.internal.util.VirtualRefBasePtr;

/* JADX INFO: loaded from: classes.dex */
public class HardwareRendererObserver {
    private final long[] mFrameMetrics;
    private final Handler mHandler;
    private final OnFrameMetricsAvailableListener mListener;
    private VirtualRefBasePtr mNativePtr;

    public interface OnFrameMetricsAvailableListener {
        void onFrameMetricsAvailable(int i);
    }

    private native long nCreateObserver();

    private static native int nGetNextBuffer(long j, long[] jArr);

    public HardwareRendererObserver(OnFrameMetricsAvailableListener onFrameMetricsAvailableListener, long[] jArr, Handler handler) {
        if (handler == null || handler.getLooper() == null) {
            throw new NullPointerException("handler and its looper cannot be null");
        }
        if (handler.getLooper().getQueue() == null) {
            throw new IllegalStateException("invalid looper, null message queue\n");
        }
        this.mFrameMetrics = jArr;
        this.mHandler = handler;
        this.mListener = onFrameMetricsAvailableListener;
        this.mNativePtr = new VirtualRefBasePtr(nCreateObserver());
    }

    long getNativeInstance() {
        return this.mNativePtr.get();
    }

    private void notifyDataAvailable() {
        this.mHandler.post(new Runnable() { // from class: android.graphics.-$$Lambda$HardwareRendererObserver$HC6rflRJ2qu_W4LkfrTA7x3XNVs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$notifyDataAvailable$0$HardwareRendererObserver();
            }
        });
    }

    public /* synthetic */ void lambda$notifyDataAvailable$0$HardwareRendererObserver() {
        boolean z = true;
        while (z) {
            int iNGetNextBuffer = nGetNextBuffer(this.mNativePtr.get(), this.mFrameMetrics);
            if (iNGetNextBuffer >= 0) {
                this.mListener.onFrameMetricsAvailable(iNGetNextBuffer);
            } else {
                z = false;
            }
        }
    }
}
