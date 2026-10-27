package org.chromium.base;

import defpackage.a9;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
@FunctionalInterface
public interface Callback<T> {

    public static abstract class Helper {
        @CalledByNative
        public static void onBooleanResultFromNative(Callback callback, boolean z) {
            callback.onResult(Boolean.valueOf(z));
        }

        @CalledByNative
        public static void onIntResultFromNative(Callback callback, int i) {
            callback.onResult(Integer.valueOf(i));
        }

        @CalledByNative
        public static void onLongResultFromNative(Callback callback, long j) {
            callback.onResult(Long.valueOf(j));
        }

        @CalledByNative
        public static void onObjectResultFromNative(Callback callback, Object obj) {
            callback.onResult(obj);
        }

        @CalledByNative
        public static void onTimeResultFromNative(Callback callback, long j) {
            callback.onResult(Long.valueOf(j));
        }

        @CalledByNative
        public static void runRunnable(Runnable runnable) {
            runnable.run();
        }
    }

    void onResult(T t);

    default a9 p1(Object obj) {
        return new a9(this, 3, obj);
    }
}
