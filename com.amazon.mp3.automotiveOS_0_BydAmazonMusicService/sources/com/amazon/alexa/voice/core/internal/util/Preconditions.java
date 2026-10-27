package com.amazon.alexa.voice.core.internal.util;

import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
public final class Preconditions {
    private static volatile Thread mainThread;

    private Preconditions() {
        throw new IllegalStateException("No instances!");
    }

    public static void mainThread(String str) {
        Thread thread = mainThread;
        if (thread == null) {
            thread = Looper.getMainLooper().getThread();
        }
        check(Thread.currentThread() == thread, new IllegalStateException(str));
    }

    public static void instance(Object obj, Class<?> cls, String str) {
        check(cls.isInstance(obj), new IllegalArgumentException(str));
    }

    public static void notNull(Object obj, String str) {
        check(obj != null, new IllegalArgumentException(str));
    }

    public static void range(int i, int i2, int i3, String str) {
        check(i >= i2 && i <= i3, new IllegalArgumentException(str));
    }

    public static void range(float f, float f2, float f3, String str) {
        check(f >= f2 && f < f3, new IllegalArgumentException(str));
    }

    public static void min(long j, long j2, String str) {
        check(j >= j2, new IllegalArgumentException(str));
    }

    private static void check(boolean z, RuntimeException runtimeException) {
        if (!z) {
            throw runtimeException;
        }
    }
}
