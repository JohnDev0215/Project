package com.google.common.primitives;

/* JADX INFO: loaded from: classes6.dex */
public final class Longs {
    public static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    public static int hashCode(long j) {
        return (int) (j ^ (j >>> 32));
    }

    private Longs() {
    }
}
