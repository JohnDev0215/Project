package com.google.android.projection.common;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BufferPool {
    static final int LARGE_BUFFER_SIZE = 262144;
    private static final int MAX_CACHE_LARGE_BUFFERS = 10;
    private static final int MAX_CACHE_MEDIUM_BUFFERS = 20;
    private static final int MAX_CACHE_SMALL_BUFFERS = 120;
    static final int MEDIUM_BUFFER_SIZE = 16384;
    static final int SMALL_BUFFER_SIZE = 512;
    private static final BufferPool instance = new BufferPool();
    private final List<ByteBuffer> largeBufferPool = new ArrayList(10);
    private final List<ByteBuffer> mediumBufferPool = new ArrayList(20);
    private final List<ByteBuffer> smallBufferPool = new ArrayList(120);

    BufferPool() {
        for (int i = 0; i < 5; i++) {
            this.largeBufferPool.add(ByteBuffer.allocateDirect(262144));
        }
        for (int i2 = 0; i2 < 10; i2++) {
            this.mediumBufferPool.add(ByteBuffer.allocateDirect(16384));
        }
        for (int i3 = 0; i3 < 60; i3++) {
            this.smallBufferPool.add(ByteBuffer.allocateDirect(512));
        }
    }

    public static ByteBuffer getBuffer(int size) {
        return instance.checkoutBuffer(size);
    }

    public static void returnBuffer(ByteBuffer buffer) {
        instance.returnByteBuffer(buffer);
    }

    List<ByteBuffer> getLargePool() {
        return this.largeBufferPool;
    }

    List<ByteBuffer> getMediumPool() {
        return this.mediumBufferPool;
    }

    List<ByteBuffer> getSmallPool() {
        return this.smallBufferPool;
    }

    ByteBuffer checkoutBuffer(int size) {
        List<ByteBuffer> bufferPool = getPoolForBufferSize(size);
        ByteBuffer res = null;
        synchronized (bufferPool) {
            if (!bufferPool.isEmpty()) {
                int last = bufferPool.size() - 1;
                ByteBuffer lastElt = bufferPool.remove(last);
                if (lastElt.capacity() >= size) {
                    res = lastElt;
                } else {
                    int i = last - 1;
                    while (true) {
                        if (i < 0) {
                            break;
                        }
                        if (bufferPool.get(i).capacity() < size) {
                            i--;
                        } else {
                            res = bufferPool.get(i);
                            bufferPool.set(i, lastElt);
                            break;
                        }
                    }
                    if (res == null) {
                        bufferPool.add(lastElt);
                    }
                }
            }
            if (res == null) {
                res = ByteBuffer.allocateDirect(size);
            }
        }
        res.limit(size);
        return res;
    }

    void returnByteBuffer(ByteBuffer buffer) {
        int length = buffer.capacity();
        buffer.rewind();
        List<ByteBuffer> bufferPool = getPoolForBufferSize(length);
        int maxCacheSize = getCacheSizeForBufferSize(length);
        synchronized (bufferPool) {
            if (bufferPool.size() < maxCacheSize) {
                bufferPool.add(buffer);
            } else {
                int systemHash = Math.abs(System.identityHashCode(buffer));
                bufferPool.set(systemHash % maxCacheSize, buffer);
            }
        }
    }

    private List<ByteBuffer> getPoolForBufferSize(int bufferSize) {
        if (bufferSize <= 512) {
            return this.smallBufferPool;
        }
        if (bufferSize <= 16384) {
            return this.mediumBufferPool;
        }
        return this.largeBufferPool;
    }

    private static int getCacheSizeForBufferSize(int bufferSize) {
        if (bufferSize <= 512) {
            return 120;
        }
        if (bufferSize <= 16384) {
            return 20;
        }
        return 10;
    }
}
