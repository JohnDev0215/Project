package io.reactivex.internal.util;

/* JADX INFO: compiled from: Pow2.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static int a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
