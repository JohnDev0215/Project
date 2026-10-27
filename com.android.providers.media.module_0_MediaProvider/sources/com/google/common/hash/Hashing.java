package com.google.common.hash;

/* JADX INFO: loaded from: classes.dex */
public final class Hashing {
    static {
        System.currentTimeMillis();
    }

    public static HashFunction farmHashFingerprint64() {
        return FarmHashFingerprint64.FARMHASH_FINGERPRINT_64;
    }
}
