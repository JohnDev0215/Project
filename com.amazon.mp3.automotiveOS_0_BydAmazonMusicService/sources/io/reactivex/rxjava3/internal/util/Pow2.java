package io.reactivex.rxjava3.internal.util;

/* JADX INFO: loaded from: classes7.dex */
public final class Pow2 {
    private Pow2() {
        throw new IllegalStateException("No instances!");
    }

    public static int roundToPowerOfTwo(final int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }
}
