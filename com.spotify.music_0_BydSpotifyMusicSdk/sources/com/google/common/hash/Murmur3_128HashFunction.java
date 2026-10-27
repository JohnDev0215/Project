package com.google.common.hash;

import java.io.Serializable;
import p.k1;
import p.p11;
import p.u4;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
final class Murmur3_128HashFunction extends k1 implements Serializable {
    public static final p11 c = new Murmur3_128HashFunction(0);
    private static final long serialVersionUID = 0;
    private final int seed;

    static {
        new Murmur3_128HashFunction(d.a);
    }

    public Murmur3_128HashFunction(int i) {
        this.seed = i;
    }

    @Override // p.p11
    public final zu a() {
        return new f(this.seed);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Murmur3_128HashFunction) && this.seed == ((Murmur3_128HashFunction) obj).seed;
    }

    public final int hashCode() {
        return Murmur3_128HashFunction.class.hashCode() ^ this.seed;
    }

    public final String toString() {
        return u4.l(new StringBuilder("Hashing.murmur3_128("), this.seed, ")");
    }
}
