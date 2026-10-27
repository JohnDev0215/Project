package com.google.common.hash;

import java.io.Serializable;
import p.o11;

/* JADX INFO: loaded from: classes.dex */
final class HashCode$IntHashCode extends o11 implements Serializable {
    private static final long serialVersionUID = 0;
    final int hash;

    public HashCode$IntHashCode(int i) {
        this.hash = i;
    }

    @Override // p.o11
    public final byte[] a() {
        int i = this.hash;
        return new byte[]{(byte) i, (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24)};
    }

    @Override // p.o11
    public final int b() {
        return this.hash;
    }

    @Override // p.o11
    public final long c() {
        throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
    }

    @Override // p.o11
    public final int d() {
        return 32;
    }

    @Override // p.o11
    public final boolean e(o11 o11Var) {
        return this.hash == o11Var.b();
    }
}
