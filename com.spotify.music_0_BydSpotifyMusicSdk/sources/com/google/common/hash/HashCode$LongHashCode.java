package com.google.common.hash;

import java.io.Serializable;
import p.o11;

/* JADX INFO: loaded from: classes.dex */
final class HashCode$LongHashCode extends o11 implements Serializable {
    private static final long serialVersionUID = 0;
    final long hash;

    public HashCode$LongHashCode(long j) {
        this.hash = j;
    }

    @Override // p.o11
    public final byte[] a() {
        return new byte[]{(byte) this.hash, (byte) (r2 >> 8), (byte) (r2 >> 16), (byte) (r2 >> 24), (byte) (r2 >> 32), (byte) (r2 >> 40), (byte) (r2 >> 48), (byte) (r2 >> 56)};
    }

    @Override // p.o11
    public final int b() {
        return (int) this.hash;
    }

    @Override // p.o11
    public final long c() {
        return this.hash;
    }

    @Override // p.o11
    public final int d() {
        return 64;
    }

    @Override // p.o11
    public final boolean e(o11 o11Var) {
        return this.hash == o11Var.c();
    }
}
