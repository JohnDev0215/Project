package com.google.common.hash;

import java.io.Serializable;
import p.o11;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class HashCode$BytesHashCode extends o11 implements Serializable {
    private static final long serialVersionUID = 0;
    final byte[] bytes;

    public HashCode$BytesHashCode(byte[] bArr) {
        bArr.getClass();
        this.bytes = bArr;
    }

    @Override // p.o11
    public final byte[] a() {
        return (byte[]) this.bytes.clone();
    }

    @Override // p.o11
    public final int b() {
        byte[] bArr = this.bytes;
        boolean z = bArr.length >= 4;
        int length = bArr.length;
        if (z) {
            return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
        }
        throw new IllegalStateException(v03.z("HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", Integer.valueOf(length)));
    }

    @Override // p.o11
    public final long c() {
        byte[] bArr = this.bytes;
        boolean z = bArr.length >= 8;
        int length = bArr.length;
        if (!z) {
            throw new IllegalStateException(v03.z("HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", Integer.valueOf(length)));
        }
        long j = bArr[0] & 255;
        for (int i = 1; i < Math.min(this.bytes.length, 8); i++) {
            j |= (((long) this.bytes[i]) & 255) << (i * 8);
        }
        return j;
    }

    @Override // p.o11
    public final int d() {
        return this.bytes.length * 8;
    }

    @Override // p.o11
    public final boolean e(o11 o11Var) {
        if (this.bytes.length != o11Var.f().length) {
            return false;
        }
        boolean z = true;
        int i = 0;
        while (true) {
            byte[] bArr = this.bytes;
            if (i >= bArr.length) {
                return z;
            }
            z &= bArr[i] == o11Var.f()[i];
            i++;
        }
    }

    @Override // p.o11
    public final byte[] f() {
        return this.bytes;
    }
}
