package android.os;

import android.app.ActivityThread;
import android.provider.Telephony;
import android.util.Log;
import android.util.Printer;
import android.util.Slog;
import android.util.proto.ProtoOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class Looper {
    private static final String TAG = "Looper";
    private static final String TAG_MAIN_THREAD_MONITOR = "MainThreadMonitor";
    private static Looper sMainLooper;
    private static Observer sObserver;
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    private boolean mInLoop;
    private Printer mLogging;
    final MessageQueue mQueue;
    private long mSlowDeliveryThresholdMs;
    private long mSlowDispatchThresholdMs;
    final Thread mThread = Thread.currentThread();
    private long mTraceTag;

    public interface Observer {
        void dispatchingThrewException(Object obj, Message message, Exception exc);

        Object messageDispatchStarting();

        void messageDispatched(Object obj, Message message);
    }

    public static void prepare() {
        prepare(true);
    }

    private static void prepare(boolean z) {
        ThreadLocal<Looper> threadLocal = sThreadLocal;
        if (threadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        threadLocal.set(new Looper(z));
    }

    @Deprecated
    public static void prepareMainLooper() {
        prepare(false);
        synchronized (Looper.class) {
            if (sMainLooper != null) {
                throw new IllegalStateException("The main Looper has already been prepared.");
            }
            sMainLooper = myLooper();
        }
    }

    public static Looper getMainLooper() {
        Looper looper;
        synchronized (Looper.class) {
            looper = sMainLooper;
        }
        return looper;
    }

    public static void setObserver(Observer observer) {
        sObserver = observer;
    }

    public static void loop() {
        Printer printer;
        String str;
        Message message;
        String str2;
        Looper looperMyLooper = myLooper();
        if (looperMyLooper == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        boolean z = looperMyLooper.mInLoop;
        String str3 = TAG;
        if (z) {
            Slog.w(TAG, "Loop again would have the queued messages be executed before this one completed.");
        }
        looperMyLooper.mInLoop = true;
        MessageQueue messageQueue = looperMyLooper.mQueue;
        Binder.clearCallingIdentity();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        int i = SystemProperties.getInt("log.looper." + Process.myUid() + "." + Thread.currentThread().getName() + ".slow", 0);
        boolean z2 = false;
        while (true) {
            Message next = messageQueue.next();
            if (next == null) {
                return;
            }
            Printer printer2 = looperMyLooper.mLogging;
            if (printer2 != null) {
                printer2.println(">>>>> Dispatching to " + next.target + " " + next.callback + ": " + next.what);
            }
            Observer observer = sObserver;
            long j = looperMyLooper.mTraceTag;
            String str4 = str3;
            long j2 = looperMyLooper.mSlowDeliveryThresholdMs;
            if (i > 0) {
                j2 = i;
            }
            boolean z3 = z2;
            boolean z4 = j2 > 0 && next.when > 0;
            if (j != 0 && Trace.isTagEnabled(j)) {
                Trace.traceBegin(j, next.target.getTraceName(next));
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            Object objMessageDispatchStarting = observer != null ? observer.messageDispatchStarting() : null;
            Looper looper = looperMyLooper;
            long uid = ThreadLocalWorkSource.setUid(next.workSourceUid);
            try {
                try {
                    next.target.dispatchMessage(next);
                    if (observer != null) {
                        observer.messageDispatched(objMessageDispatchStarting, next);
                    }
                    long jUptimeMillis2 = SystemClock.uptimeMillis();
                    if (!z4) {
                        printer = printer2;
                        str = " ";
                        message = next;
                        str2 = str4;
                    } else if (!z3) {
                        str2 = str4;
                        long j3 = j2;
                        printer = printer2;
                        str = " ";
                        message = next;
                        if (showSlowLog(j3, next.when, jUptimeMillis, Telephony.RcsColumns.RcsMessageDeliveryColumns.DELIVERY_URI_PART, message)) {
                            z3 = true;
                        }
                    } else if (jUptimeMillis - next.when <= 10) {
                        str2 = str4;
                        Slog.w(str2, "Drained");
                        printer = printer2;
                        str = " ";
                        message = next;
                        z3 = false;
                    } else {
                        str2 = str4;
                        printer = printer2;
                        str = " ";
                        message = next;
                    }
                    showSlowLog(1000L, jUptimeMillis, jUptimeMillis2, "dispatch", message);
                    if (printer != null) {
                        printer.println("<<<<< Finished to " + message.target + str + message.callback);
                    }
                    long jClearCallingIdentity2 = Binder.clearCallingIdentity();
                    if (jClearCallingIdentity != jClearCallingIdentity2) {
                        Log.wtf(str2, "Thread identity changed from 0x" + Long.toHexString(jClearCallingIdentity) + " to 0x" + Long.toHexString(jClearCallingIdentity2) + " while dispatching to " + message.target.getClass().getName() + str + message.callback + " what=" + message.what);
                    }
                    message.recycleUnchecked();
                    str3 = str2;
                    z2 = z3;
                    looperMyLooper = looper;
                } catch (Exception e) {
                    if (observer != null) {
                        observer.dispatchingThrewException(objMessageDispatchStarting, next, e);
                    }
                    throw e;
                }
            } finally {
                ThreadLocalWorkSource.restore(uid);
                if (j != 0) {
                    Trace.traceEnd(j);
                }
            }
        }
    }

    private static boolean showSlowLog(long j, long j2, long j3, String str, Message message) {
        long j4 = j3 - j2;
        if (j4 < j) {
            return false;
        }
        if (Thread.currentThread() == getMainLooper().getThread()) {
            Slog.e(TAG_MAIN_THREAD_MONITOR, "Dispatch took " + j4 + "ms on " + Thread.currentThread().getName() + " h=" + message.target.getClass().getName() + " cb=" + message.callback + " msg=" + message.what + " processName=" + ActivityThread.currentProcessName());
        }
        return true;
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static MessageQueue myQueue() {
        return myLooper().mQueue;
    }

    private Looper(boolean z) {
        this.mQueue = new MessageQueue(z);
    }

    public boolean isCurrentThread() {
        return Thread.currentThread() == this.mThread;
    }

    public void setMessageLogging(Printer printer) {
        this.mLogging = printer;
    }

    public void setTraceTag(long j) {
        this.mTraceTag = j;
    }

    public void setSlowLogThresholdMs(long j, long j2) {
        this.mSlowDispatchThresholdMs = j;
        this.mSlowDeliveryThresholdMs = j2;
    }

    public void quit() {
        this.mQueue.quit(false);
    }

    public void quitSafely() {
        this.mQueue.quit(true);
    }

    public Thread getThread() {
        return this.mThread;
    }

    public MessageQueue getQueue() {
        return this.mQueue;
    }

    public void dump(Printer printer, String str) {
        printer.println(str + toString());
        this.mQueue.dump(printer, str + "  ", null);
    }

    public void dump(Printer printer, String str, Handler handler) {
        printer.println(str + toString());
        this.mQueue.dump(printer, str + "  ", handler);
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1138166333441L, this.mThread.getName());
        protoOutputStream.write(1112396529666L, this.mThread.getId());
        MessageQueue messageQueue = this.mQueue;
        if (messageQueue != null) {
            messageQueue.dumpDebug(protoOutputStream, 1146756268035L);
        }
        protoOutputStream.end(jStart);
    }

    public String toString() {
        return "Looper (" + this.mThread.getName() + ", tid " + this.mThread.getId() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
    }
}
