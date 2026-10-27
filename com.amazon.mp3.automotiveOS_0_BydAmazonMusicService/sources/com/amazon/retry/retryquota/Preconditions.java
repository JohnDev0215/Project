package com.amazon.retry.retryquota;

/* JADX INFO: loaded from: classes5.dex */
final class Preconditions {
    private Preconditions() {
    }

    static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }
}
