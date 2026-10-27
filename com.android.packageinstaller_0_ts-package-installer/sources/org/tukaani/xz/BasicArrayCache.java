package org.tukaani.xz;

import androidx.core.app.FrameMetricsAggregator;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class BasicArrayCache extends ArrayCache {
    private static final int CACHEABLE_SIZE_MIN = 32768;
    private static final int ELEMENTS_PER_STACK = 512;
    private static final int STACKS_MAX = 32;
    private final CacheMap<byte[]> byteArrayCache = new CacheMap<>();
    private final CacheMap<int[]> intArrayCache = new CacheMap<>();

    private static class CyclicStack<T> {
        private final T[] elements;
        private int pos;

        private CyclicStack() {
            this.elements = (T[]) new Object[512];
            this.pos = 0;
        }

        public synchronized T pop() {
            T e;
            e = this.elements[this.pos];
            this.elements[this.pos] = null;
            this.pos = (this.pos - 1) & FrameMetricsAggregator.EVERY_DURATION;
            return e;
        }

        public synchronized void push(T e) {
            int i = (this.pos + 1) & FrameMetricsAggregator.EVERY_DURATION;
            this.pos = i;
            this.elements[i] = e;
        }
    }

    private static class CacheMap<T> extends LinkedHashMap<Integer, CyclicStack<Reference<T>>> {
        private static final long serialVersionUID = 1;

        public CacheMap() {
            super(64, 0.75f, true);
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<Integer, CyclicStack<Reference<T>>> eldest) {
            return size() > 32;
        }
    }

    private static final class LazyHolder {
        static final BasicArrayCache INSTANCE = new BasicArrayCache();

        private LazyHolder() {
        }
    }

    public static BasicArrayCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static <T> T getArray(CacheMap<T> cache, int size) {
        CyclicStack<Reference<T>> stack;
        T array;
        if (size < 32768) {
            return null;
        }
        synchronized (cache) {
            stack = cache.get(Integer.valueOf(size));
        }
        if (stack == null) {
            return null;
        }
        do {
            Reference<T> r = stack.pop();
            if (r == null) {
                return null;
            }
            array = r.get();
        } while (array == null);
        return array;
    }

    private static <T> void putArray(CacheMap<T> cache, T array, int size) {
        CyclicStack<Reference<T>> stack;
        if (size < 32768) {
            return;
        }
        synchronized (cache) {
            stack = cache.get(Integer.valueOf(size));
            if (stack == null) {
                stack = new CyclicStack<>();
                cache.put(Integer.valueOf(size), stack);
            }
        }
        stack.push(new SoftReference(array));
    }

    @Override // org.tukaani.xz.ArrayCache
    public byte[] getByteArray(int size, boolean fillWithZeros) {
        byte[] array = (byte[]) getArray(this.byteArrayCache, size);
        if (array == null) {
            return new byte[size];
        }
        if (fillWithZeros) {
            Arrays.fill(array, (byte) 0);
            return array;
        }
        return array;
    }

    @Override // org.tukaani.xz.ArrayCache
    public void putArray(byte[] array) {
        putArray(this.byteArrayCache, array, array.length);
    }

    @Override // org.tukaani.xz.ArrayCache
    public int[] getIntArray(int size, boolean fillWithZeros) {
        int[] array = (int[]) getArray(this.intArrayCache, size);
        if (array == null) {
            return new int[size];
        }
        if (fillWithZeros) {
            Arrays.fill(array, 0);
            return array;
        }
        return array;
    }

    @Override // org.tukaani.xz.ArrayCache
    public void putArray(int[] array) {
        putArray(this.intArrayCache, array, array.length);
    }
}
