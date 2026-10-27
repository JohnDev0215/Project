package com.google.common.hash;

import java.util.zip.Checksum;
import p.o11;
import p.z0;

/* JADX INFO: loaded from: classes.dex */
public final class a extends z0 {
    public final Checksum j;
    public final /* synthetic */ ChecksumHashFunction k;

    public a(ChecksumHashFunction checksumHashFunction, Checksum checksum) {
        this.k = checksumHashFunction;
        checksum.getClass();
        this.j = checksum;
    }

    @Override // p.z0
    public final void H0(byte[] bArr, int i) {
        this.j.update(bArr, 0, i);
    }

    @Override // p.zu
    public final o11 l0() {
        long value = this.j.getValue();
        if (this.k.bits == 32) {
            char[] cArr = o11.c;
            return new HashCode$IntHashCode((int) value);
        }
        char[] cArr2 = o11.c;
        return new HashCode$LongHashCode(value);
    }
}
