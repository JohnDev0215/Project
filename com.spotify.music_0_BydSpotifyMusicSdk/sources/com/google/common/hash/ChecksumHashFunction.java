package com.google.common.hash;

import java.io.Serializable;
import java.util.zip.Checksum;
import p.c41;
import p.k1;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
final class ChecksumHashFunction extends k1 implements Serializable {
    private static final long serialVersionUID = 0;
    private final int bits;
    private final c41 checksumSupplier;
    private final String toString;

    public ChecksumHashFunction(c41 c41Var, String str) {
        c41Var.getClass();
        this.checksumSupplier = c41Var;
        this.bits = 32;
        this.toString = str;
    }

    @Override // p.p11
    public final zu a() {
        return new a(this, (Checksum) this.checksumSupplier.get());
    }

    public final String toString() {
        return this.toString;
    }
}
