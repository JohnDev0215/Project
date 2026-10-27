package com.google.common.hash;

/* JADX INFO: loaded from: classes6.dex */
public final class Hashing {
    static final int GOOD_FAST_HASH_SEED = (int) System.currentTimeMillis();

    @Deprecated
    public static HashFunction md5() {
        return Md5Holder.MD5;
    }

    private static class Md5Holder {
        static final HashFunction MD5 = new MessageDigestHashFunction("MD5", "Hashing.md5()");

        private Md5Holder() {
        }
    }

    public static HashFunction sha256() {
        return Sha256Holder.SHA_256;
    }

    private static class Sha256Holder {
        static final HashFunction SHA_256 = new MessageDigestHashFunction("SHA-256", "Hashing.sha256()");

        private Sha256Holder() {
        }
    }

    private Hashing() {
    }
}
