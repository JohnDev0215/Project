package com.google.common.util.concurrent;

/* JADX INFO: loaded from: classes.dex */
final class Platform {
    static boolean isInstanceOfThrowableClass(Throwable t, Class<? extends Throwable> expectedClass) {
        return expectedClass.isInstance(t);
    }

    private Platform() {
    }
}
