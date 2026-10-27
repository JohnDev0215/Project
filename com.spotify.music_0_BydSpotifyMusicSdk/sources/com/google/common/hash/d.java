package com.google.common.hash;

import p.p11;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static final int a = (int) System.currentTimeMillis();

    public static p11 a() {
        return Hashing$ChecksumType.c.hashFunction;
    }

    public static p11 b() {
        return Murmur3_128HashFunction.c;
    }
}
