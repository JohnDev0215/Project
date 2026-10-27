package com.google.common.hash;

import java.security.MessageDigest;
import java.util.Arrays;
import p.o11;
import p.z0;

/* JADX INFO: loaded from: classes.dex */
public final class e extends z0 {
    public final MessageDigest j;
    public final int k;
    public boolean l;

    public e(MessageDigest messageDigest, int i) {
        this.j = messageDigest;
        this.k = i;
    }

    @Override // p.z0
    public final void H0(byte[] bArr, int i) {
        if (!(!this.l)) {
            throw new IllegalStateException("Cannot re-use a Hasher after calling hash() on it");
        }
        this.j.update(bArr, 0, i);
    }

    @Override // p.zu
    public final o11 l0() {
        if (!(!this.l)) {
            throw new IllegalStateException("Cannot re-use a Hasher after calling hash() on it");
        }
        this.l = true;
        MessageDigest messageDigest = this.j;
        int digestLength = messageDigest.getDigestLength();
        int i = this.k;
        if (i == digestLength) {
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr = o11.c;
            return new HashCode$BytesHashCode(bArrDigest);
        }
        byte[] bArrCopyOf = Arrays.copyOf(messageDigest.digest(), i);
        char[] cArr2 = o11.c;
        return new HashCode$BytesHashCode(bArrCopyOf);
    }
}
