package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
abstract class AbstractHashFunction implements HashFunction {
    public abstract HashCode hashBytes(byte[] bArr, int i, int i2);

    AbstractHashFunction() {
    }

    public HashCode hashBytes(byte[] bArr) {
        return hashBytes(bArr, 0, bArr.length);
    }
}
