package com.amazon.coral.retry.strategy;

/* JADX INFO: loaded from: classes2.dex */
final class Preconditions {
    private Preconditions() {
    }

    static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }
}
