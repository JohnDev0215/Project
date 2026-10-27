package org.tukaani.xz;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ResettableArrayCache extends ArrayCache {
    private final ArrayCache arrayCache;
    private final List<byte[]> byteArrays;
    private final List<int[]> intArrays;

    public ResettableArrayCache(ArrayCache arrayCache) {
        this.arrayCache = arrayCache;
        if (arrayCache == ArrayCache.getDummyCache()) {
            this.byteArrays = null;
            this.intArrays = null;
        } else {
            this.byteArrays = new ArrayList();
            this.intArrays = new ArrayList();
        }
    }

    @Override // org.tukaani.xz.ArrayCache
    public byte[] getByteArray(int size, boolean fillWithZeros) {
        byte[] array = this.arrayCache.getByteArray(size, fillWithZeros);
        List<byte[]> list = this.byteArrays;
        if (list != null) {
            synchronized (list) {
                this.byteArrays.add(array);
            }
        }
        return array;
    }

    @Override // org.tukaani.xz.ArrayCache
    public void putArray(byte[] array) {
        List<byte[]> list = this.byteArrays;
        if (list != null) {
            synchronized (list) {
                int i = this.byteArrays.lastIndexOf(array);
                if (i != -1) {
                    this.byteArrays.remove(i);
                }
            }
            this.arrayCache.putArray(array);
        }
    }

    @Override // org.tukaani.xz.ArrayCache
    public int[] getIntArray(int size, boolean fillWithZeros) {
        int[] array = this.arrayCache.getIntArray(size, fillWithZeros);
        List<int[]> list = this.intArrays;
        if (list != null) {
            synchronized (list) {
                this.intArrays.add(array);
            }
        }
        return array;
    }

    @Override // org.tukaani.xz.ArrayCache
    public void putArray(int[] array) {
        List<int[]> list = this.intArrays;
        if (list != null) {
            synchronized (list) {
                int i = this.intArrays.lastIndexOf(array);
                if (i != -1) {
                    this.intArrays.remove(i);
                }
            }
            this.arrayCache.putArray(array);
        }
    }

    public void reset() {
        List<byte[]> list = this.byteArrays;
        if (list != null) {
            synchronized (list) {
                for (int i = this.byteArrays.size() - 1; i >= 0; i--) {
                    this.arrayCache.putArray(this.byteArrays.get(i));
                }
                this.byteArrays.clear();
            }
            synchronized (this.intArrays) {
                for (int i2 = this.intArrays.size() - 1; i2 >= 0; i2--) {
                    this.arrayCache.putArray(this.intArrays.get(i2));
                }
                this.intArrays.clear();
            }
        }
    }
}
