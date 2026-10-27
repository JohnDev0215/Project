package org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ThreadUtils {
    public static final Object a = new Object();
    public static boolean b;
    public static Handler c;

    public static void a() {
    }

    public static void b() {
    }

    public static Handler c() {
        Handler handler;
        synchronized (a) {
            if (c == null) {
                if (b) {
                    throw new RuntimeException("Did not yet override the UI thread");
                }
                c = new Handler(Looper.getMainLooper());
            }
            handler = c;
        }
        return handler;
    }

    public static Looper d() {
        return c().getLooper();
    }

    @Deprecated
    public static void e(Runnable runnable) {
        c().post(runnable);
    }

    @Deprecated
    public static FutureTask f(FutureTask futureTask) {
        if (j()) {
            futureTask.run();
        } else {
            c().post(futureTask);
        }
        return futureTask;
    }

    @Deprecated
    public static void g(Runnable runnable) {
        if (j()) {
            runnable.run();
        } else {
            c().post(runnable);
        }
    }

    @Deprecated
    public static void h(Runnable runnable) {
        if (j()) {
            runnable.run();
            return;
        }
        FutureTask futureTask = new FutureTask(runnable, null);
        c().post(futureTask);
        try {
            futureTask.get();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while waiting for runnable", e);
        }
    }

    @Deprecated
    public static Object i(Callable callable) {
        try {
            FutureTask futureTask = new FutureTask(callable);
            f(futureTask);
            try {
                return futureTask.get();
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted waiting for callable", e);
            }
        } catch (ExecutionException e2) {
            throw new RuntimeException("Error occurred waiting for callable", e2);
        }
    }

    public static boolean isThreadPriorityAudio(int i) {
        return Process.getThreadPriority(i) == -16;
    }

    public static boolean j() {
        return c().getLooper() == Looper.myLooper();
    }

    public static void setThreadPriorityAudio(int i) {
        Process.setThreadPriority(i, -16);
    }
}
