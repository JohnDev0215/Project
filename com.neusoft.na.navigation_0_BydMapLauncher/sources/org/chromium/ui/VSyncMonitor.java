package org.chromium.ui;

import android.content.Context;
import android.os.Handler;
import android.view.Choreographer;
import android.view.WindowManager;
import org.chromium.base.TraceEvent;

/* JADX INFO: loaded from: classes3.dex */
public class VSyncMonitor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long NANOSECONDS_PER_MICROSECOND = 1000;
    private static final long NANOSECONDS_PER_SECOND = 1000000000;
    private final Choreographer mChoreographer;
    private long mGoodStartingPointNano;
    private boolean mHaveRequestInFlight;
    private Listener mListener;
    private long mRefreshPeriodNano;
    private final Choreographer.FrameCallback mVSyncFrameCallback;
    private boolean mInsideVSync = false;
    private boolean mConsecutiveVSync = false;
    private final Handler mHandler = new Handler();

    public interface Listener {
        void onVSync(VSyncMonitor vSyncMonitor, long j);
    }

    static /* synthetic */ long access$214(VSyncMonitor vSyncMonitor, long j) {
        long j2 = vSyncMonitor.mRefreshPeriodNano + j;
        vSyncMonitor.mRefreshPeriodNano = j2;
        return j2;
    }

    public VSyncMonitor(Context context, Listener listener) {
        this.mListener = listener;
        float refreshRate = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
        final boolean z = refreshRate < 30.0f;
        this.mRefreshPeriodNano = (long) (1.0E9f / (refreshRate <= 0.0f ? 60.0f : refreshRate));
        this.mChoreographer = Choreographer.getInstance();
        this.mVSyncFrameCallback = new Choreographer.FrameCallback() { // from class: org.chromium.ui.VSyncMonitor.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                TraceEvent.begin("VSync");
                if (z && VSyncMonitor.this.mConsecutiveVSync) {
                    long j2 = j - VSyncMonitor.this.mGoodStartingPointNano;
                    VSyncMonitor.access$214(VSyncMonitor.this, (long) ((j2 - r3.mRefreshPeriodNano) * 0.1f));
                }
                VSyncMonitor.this.mGoodStartingPointNano = j;
                VSyncMonitor vSyncMonitor = VSyncMonitor.this;
                vSyncMonitor.onVSyncCallback(j, vSyncMonitor.getCurrentNanoTime());
                TraceEvent.end("VSync");
            }
        };
        this.mGoodStartingPointNano = getCurrentNanoTime();
    }

    public long getVSyncPeriodInMicroseconds() {
        return this.mRefreshPeriodNano / 1000;
    }

    public void requestUpdate() {
        postCallback();
    }

    public boolean isInsideVSync() {
        return this.mInsideVSync;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getCurrentNanoTime() {
        return System.nanoTime();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVSyncCallback(long j, long j2) {
        this.mInsideVSync = true;
        this.mHaveRequestInFlight = false;
        try {
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onVSync(this, j / 1000);
            }
        } finally {
            this.mInsideVSync = false;
        }
    }

    private void postCallback() {
        if (this.mHaveRequestInFlight) {
            return;
        }
        this.mHaveRequestInFlight = true;
        this.mConsecutiveVSync = this.mInsideVSync;
        this.mChoreographer.postFrameCallback(this.mVSyncFrameCallback);
    }
}
