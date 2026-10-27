package org.chromium.base;

import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Printer;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class TraceEvent {
    private static volatile boolean sATraceEnabled = false;
    private static volatile boolean sEnabled = false;

    private static native void nativeBegin(String str, String str2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeBeginToplevel();

    private static native void nativeEnd(String str, String str2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeEndToplevel();

    private static native void nativeFinishAsync(String str, long j);

    private static native void nativeInstant(String str, String str2);

    private static native void nativeRegisterEnabledObserver();

    private static native void nativeStartATrace();

    private static native void nativeStartAsync(String str, long j);

    private static native void nativeStopATrace();

    private static class BasicLooperMonitor implements Printer {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        private BasicLooperMonitor() {
        }

        @Override // android.util.Printer
        public void println(String str) {
            if (str.startsWith(">")) {
                beginHandling(str);
            } else {
                endHandling(str);
            }
        }

        void beginHandling(String str) {
            if (TraceEvent.sEnabled) {
                TraceEvent.nativeBeginToplevel();
            }
        }

        void endHandling(String str) {
            if (TraceEvent.sEnabled) {
                TraceEvent.nativeEndToplevel();
            }
        }
    }

    private static final class IdleTracingLooperMonitor extends BasicLooperMonitor implements MessageQueue.IdleHandler {
        private static final long FRAME_DURATION_MILLIS = 16;
        private static final String IDLE_EVENT_NAME = "Looper.queueIdle";
        private static final long MIN_INTERESTING_BURST_DURATION_MILLIS = 48;
        private static final long MIN_INTERESTING_DURATION_MILLIS = 16;
        private static final String TAG = "TraceEvent.LooperMonitor";
        private boolean mIdleMonitorAttached;
        private long mLastIdleStartedAt;
        private long mLastWorkStartedAt;
        private int mNumIdlesSeen;
        private int mNumTasksSeen;
        private int mNumTasksSinceLastIdle;

        private IdleTracingLooperMonitor() {
            super();
            this.mLastIdleStartedAt = 0L;
            this.mLastWorkStartedAt = 0L;
            this.mNumTasksSeen = 0;
            this.mNumIdlesSeen = 0;
            this.mNumTasksSinceLastIdle = 0;
            this.mIdleMonitorAttached = false;
        }

        private final void syncIdleMonitoring() {
            if (TraceEvent.sEnabled && !this.mIdleMonitorAttached) {
                this.mLastIdleStartedAt = SystemClock.elapsedRealtime();
                Looper.myQueue().addIdleHandler(this);
                this.mIdleMonitorAttached = true;
                android.util.Log.v(TAG, "attached idle handler");
                return;
            }
            if (!this.mIdleMonitorAttached || TraceEvent.sEnabled) {
                return;
            }
            Looper.myQueue().removeIdleHandler(this);
            this.mIdleMonitorAttached = false;
            android.util.Log.v(TAG, "detached idle handler");
        }

        @Override // org.chromium.base.TraceEvent.BasicLooperMonitor
        final void beginHandling(String str) {
            if (this.mNumTasksSinceLastIdle == 0) {
                TraceEvent.end(IDLE_EVENT_NAME);
            }
            this.mLastWorkStartedAt = SystemClock.elapsedRealtime();
            syncIdleMonitoring();
            super.beginHandling(str);
        }

        @Override // org.chromium.base.TraceEvent.BasicLooperMonitor
        final void endHandling(String str) {
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.mLastWorkStartedAt;
            if (jElapsedRealtime > 16) {
                traceAndLog(5, "observed a task that took " + jElapsedRealtime + "ms: " + str);
            }
            super.endHandling(str);
            syncIdleMonitoring();
            this.mNumTasksSeen++;
            this.mNumTasksSinceLastIdle++;
        }

        private static void traceAndLog(int i, String str) {
            TraceEvent.instant("TraceEvent.LooperMonitor:IdleStats", str);
            android.util.Log.println(i, TAG, str);
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (this.mLastIdleStartedAt == 0) {
                this.mLastIdleStartedAt = jElapsedRealtime;
            }
            long j = jElapsedRealtime - this.mLastIdleStartedAt;
            this.mNumIdlesSeen++;
            TraceEvent.begin(IDLE_EVENT_NAME, this.mNumTasksSinceLastIdle + " tasks since last idle.");
            if (j > MIN_INTERESTING_BURST_DURATION_MILLIS) {
                traceAndLog(3, this.mNumTasksSeen + " tasks and " + this.mNumIdlesSeen + " idles processed so far, " + this.mNumTasksSinceLastIdle + " tasks bursted and " + j + "ms elapsed since last idle");
            }
            this.mLastIdleStartedAt = jElapsedRealtime;
            this.mNumTasksSinceLastIdle = 0;
            return true;
        }
    }

    private static final class LooperMonitorHolder {
        private static final BasicLooperMonitor sInstance;

        private LooperMonitorHolder() {
        }

        static {
            sInstance = CommandLine.getInstance().hasSwitch(BaseSwitches.ENABLE_IDLE_TRACING) ? new IdleTracingLooperMonitor() : new BasicLooperMonitor();
        }
    }

    public static void registerNativeEnabledObserver() {
        nativeRegisterEnabledObserver();
    }

    public static void setEnabled(boolean z) {
        sEnabled = z;
        if (sATraceEnabled) {
            return;
        }
        ThreadUtils.getUiThreadLooper().setMessageLogging(z ? LooperMonitorHolder.sInstance : null);
    }

    public static void setATraceEnabled(boolean z) {
        if (sATraceEnabled == z) {
            return;
        }
        sATraceEnabled = z;
        if (z) {
            nativeStartATrace();
        } else {
            nativeStopATrace();
        }
    }

    public static boolean enabled() {
        return sEnabled;
    }

    public static void instant(String str) {
        if (sEnabled) {
            nativeInstant(str, null);
        }
    }

    public static void instant(String str, String str2) {
        if (sEnabled) {
            nativeInstant(str, str2);
        }
    }

    public static void startAsync(String str, long j) {
        if (sEnabled) {
            nativeStartAsync(str, j);
        }
    }

    public static void finishAsync(String str, long j) {
        if (sEnabled) {
            nativeFinishAsync(str, j);
        }
    }

    public static void begin(String str) {
        if (sEnabled) {
            nativeBegin(str, null);
        }
    }

    public static void begin(String str, String str2) {
        if (sEnabled) {
            nativeBegin(str, str2);
        }
    }

    public static void end(String str) {
        if (sEnabled) {
            nativeEnd(str, null);
        }
    }

    public static void end(String str, String str2) {
        if (sEnabled) {
            nativeEnd(str, str2);
        }
    }
}
