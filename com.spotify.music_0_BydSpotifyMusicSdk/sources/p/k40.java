package p;

import android.util.Log;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class k40 implements Thread.UncaughtExceptionHandler {
    public final p01 a;
    public final com.google.firebase.crashlytics.internal.settings.a b;
    public final Thread.UncaughtExceptionHandler c;
    public final j30 d;
    public final AtomicBoolean e = new AtomicBoolean(false);

    public k40(p01 p01Var, com.google.firebase.crashlytics.internal.settings.a aVar, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, j30 j30Var) {
        this.a = p01Var;
        this.b = aVar;
        this.c = uncaughtExceptionHandler;
        this.d = j30Var;
    }

    public final boolean a(Thread thread, Throwable th) {
        if (thread == null) {
            Log.e("FirebaseCrashlytics", "Crashlytics will not record uncaught exception; null thread", null);
            return false;
        }
        if (th == null) {
            Log.e("FirebaseCrashlytics", "Crashlytics will not record uncaught exception; null throwable", null);
            return false;
        }
        if (!this.d.b()) {
            return true;
        }
        Log.isLoggable("FirebaseCrashlytics", 3);
        return false;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.c;
        AtomicBoolean atomicBoolean = this.e;
        atomicBoolean.set(true);
        try {
            try {
                if (a(thread, th)) {
                    this.a.y(this.b, thread, th);
                } else {
                    Log.isLoggable("FirebaseCrashlytics", 3);
                }
            } catch (Exception e) {
                Log.e("FirebaseCrashlytics", "An error occurred in the uncaught exception handler", e);
            }
            Log.isLoggable("FirebaseCrashlytics", 3);
            uncaughtExceptionHandler.uncaughtException(thread, th);
            atomicBoolean.set(false);
        } catch (Throwable th2) {
            Log.isLoggable("FirebaseCrashlytics", 3);
            uncaughtExceptionHandler.uncaughtException(thread, th);
            atomicBoolean.set(false);
            throw th2;
        }
    }
}
