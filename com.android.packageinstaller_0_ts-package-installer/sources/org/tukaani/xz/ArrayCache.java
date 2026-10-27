package org.tukaani.xz;

/* JADX INFO: loaded from: classes2.dex */
public class ArrayCache {
    private static volatile ArrayCache defaultCache;
    private static final ArrayCache dummyCache;

    static {
        ArrayCache arrayCache = new ArrayCache();
        dummyCache = arrayCache;
        defaultCache = arrayCache;
    }

    public static ArrayCache getDummyCache() {
        return dummyCache;
    }

    public static ArrayCache getDefaultCache() {
        return defaultCache;
    }

    public static void setDefaultCache(ArrayCache arrayCache) {
        if (arrayCache == null) {
            throw null;
        }
        defaultCache = arrayCache;
    }

    public byte[] getByteArray(int size, boolean fillWithZeros) {
        return new byte[size];
    }

    public void putArray(byte[] array) {
    }

    public int[] getIntArray(int size, boolean fillWithZeros) {
        return new int[size];
    }

    public void putArray(int[] array) {
    }
}
