package com.amplifyframework.datastore.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class ErrorInspector {
    private ErrorInspector() {
    }

    public static boolean contains(@Nullable Throwable th, @NonNull Class<? extends Throwable> cls) {
        Objects.requireNonNull(cls);
        if (th == null) {
            return false;
        }
        try {
            if (!cls.isInstance(th)) {
                if (!contains(th.getCause(), cls)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean contains(@Nullable Throwable th, @NonNull List<Class<? extends Throwable>> list) {
        Objects.requireNonNull(list);
        if (th == null) {
            return false;
        }
        try {
            Iterator<Class<? extends Throwable>> it = list.iterator();
            while (it.hasNext()) {
                if (contains(th, it.next())) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }
}
