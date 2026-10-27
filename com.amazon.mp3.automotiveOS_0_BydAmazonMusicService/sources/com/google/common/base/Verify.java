package com.google.common.base;

/* JADX INFO: loaded from: classes6.dex */
public final class Verify {
    public static void verify(boolean z) {
        if (!z) {
            throw new VerifyException();
        }
    }

    private Verify() {
    }
}
