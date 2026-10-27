package com.amazon.avod.util;

/* JADX INFO: loaded from: classes2.dex */
public enum DataUnit {
    BITS(1),
    BYTES(8),
    KILOBITS(1000),
    KILOBYTES(8192),
    MEGABYTES(((long) Math.pow(1024.0d, 2.0d)) * 8),
    GIGABYTES(((long) Math.pow(1024.0d, 3.0d)) * 8);

    long mBits;

    DataUnit(long j) {
        this.mBits = j;
    }

    public long toBits(float f) {
        return (long) (f * this.mBits);
    }

    public long toBytes(float f) {
        return toBits(f) / BYTES.mBits;
    }

    public float toKiloBits(float f) {
        return toBits(f) / KILOBITS.mBits;
    }

    public float toKiloBytes(float f) {
        return toBits(f) / KILOBYTES.mBits;
    }

    public float toMegaBytes(float f) {
        return toBits(f) / MEGABYTES.mBits;
    }

    public float toGigaBytes(float f) {
        return toBits(f) / GIGABYTES.mBits;
    }
}
