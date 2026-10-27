package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.lzma.LZMADecoder;
import org.tukaani.xz.rangecoder.RangeDecoderFromStream;

/* JADX INFO: loaded from: classes2.dex */
public class LZMAInputStream extends InputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DICT_SIZE_MAX = 2147483632;
    private ArrayCache arrayCache;
    private boolean endReached;
    private IOException exception;
    private InputStream in;
    private LZDecoder lz;
    private LZMADecoder lzma;
    private RangeDecoderFromStream rc;
    private long remainingSize;
    private final byte[] tempBuf;

    public static int getMemoryUsage(int dictSize, byte propsByte) throws CorruptedInputException, UnsupportedOptionsException {
        if (dictSize < 0 || dictSize > 2147483632) {
            throw new UnsupportedOptionsException("LZMA dictionary is too big for this implementation");
        }
        int props = propsByte & 255;
        if (props > 224) {
            throw new CorruptedInputException("Invalid LZMA properties byte");
        }
        int props2 = props % 45;
        int lp = props2 / 9;
        int lc = props2 - (lp * 9);
        return getMemoryUsage(dictSize, lc, lp);
    }

    public static int getMemoryUsage(int dictSize, int lc, int lp) {
        if (lc < 0 || lc > 8 || lp < 0 || lp > 4) {
            throw new IllegalArgumentException("Invalid lc or lp");
        }
        return (getDictSize(dictSize) / 1024) + 10 + ((1536 << (lc + lp)) / 1024);
    }

    private static int getDictSize(int dictSize) {
        if (dictSize < 0 || dictSize > 2147483632) {
            throw new IllegalArgumentException("LZMA dictionary is too big for this implementation");
        }
        if (dictSize < 4096) {
            dictSize = 4096;
        }
        return (dictSize + 15) & (-16);
    }

    public LZMAInputStream(InputStream in) throws IOException {
        this(in, -1);
    }

    public LZMAInputStream(InputStream in, ArrayCache arrayCache) throws IOException {
        this(in, -1, arrayCache);
    }

    public LZMAInputStream(InputStream in, int memoryLimit) throws IOException {
        this(in, memoryLimit, ArrayCache.getDefaultCache());
    }

    public LZMAInputStream(InputStream in, int memoryLimit, ArrayCache arrayCache) throws IOException {
        this.endReached = $assertionsDisabled;
        this.tempBuf = new byte[1];
        this.exception = null;
        DataInputStream inData = new DataInputStream(in);
        byte propsByte = inData.readByte();
        int dictSize = 0;
        for (int i = 0; i < 4; i++) {
            dictSize |= inData.readUnsignedByte() << (i * 8);
        }
        long uncompSize = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            uncompSize |= ((long) inData.readUnsignedByte()) << (i2 * 8);
        }
        int memoryNeeded = getMemoryUsage(dictSize, propsByte);
        if (memoryLimit != -1 && memoryNeeded > memoryLimit) {
            throw new MemoryLimitException(memoryNeeded, memoryLimit);
        }
        initialize(in, uncompSize, propsByte, dictSize, null, arrayCache);
    }

    public LZMAInputStream(InputStream in, long uncompSize, byte propsByte, int dictSize) throws IOException {
        this.endReached = $assertionsDisabled;
        this.tempBuf = new byte[1];
        this.exception = null;
        initialize(in, uncompSize, propsByte, dictSize, null, ArrayCache.getDefaultCache());
    }

    public LZMAInputStream(InputStream in, long uncompSize, byte propsByte, int dictSize, byte[] presetDict) throws IOException {
        this.endReached = $assertionsDisabled;
        this.tempBuf = new byte[1];
        this.exception = null;
        initialize(in, uncompSize, propsByte, dictSize, presetDict, ArrayCache.getDefaultCache());
    }

    public LZMAInputStream(InputStream in, long uncompSize, byte propsByte, int dictSize, byte[] presetDict, ArrayCache arrayCache) throws IOException {
        this.endReached = $assertionsDisabled;
        this.tempBuf = new byte[1];
        this.exception = null;
        initialize(in, uncompSize, propsByte, dictSize, presetDict, arrayCache);
    }

    public LZMAInputStream(InputStream in, long uncompSize, int lc, int lp, int pb, int dictSize, byte[] presetDict) throws IOException {
        this.endReached = $assertionsDisabled;
        this.tempBuf = new byte[1];
        this.exception = null;
        initialize(in, uncompSize, lc, lp, pb, dictSize, presetDict, ArrayCache.getDefaultCache());
    }

    public LZMAInputStream(InputStream in, long uncompSize, int lc, int lp, int pb, int dictSize, byte[] presetDict, ArrayCache arrayCache) throws IOException {
        this.endReached = $assertionsDisabled;
        this.tempBuf = new byte[1];
        this.exception = null;
        initialize(in, uncompSize, lc, lp, pb, dictSize, presetDict, arrayCache);
    }

    private void initialize(InputStream in, long uncompSize, byte propsByte, int dictSize, byte[] presetDict, ArrayCache arrayCache) throws IOException {
        if (uncompSize < -1) {
            throw new UnsupportedOptionsException("Uncompressed size is too big");
        }
        int props = propsByte & 255;
        if (props > 224) {
            throw new CorruptedInputException("Invalid LZMA properties byte");
        }
        int pb = props / 45;
        int props2 = props - ((pb * 9) * 5);
        int lp = props2 / 9;
        int lc = props2 - (lp * 9);
        if (dictSize < 0 || dictSize > 2147483632) {
            throw new UnsupportedOptionsException("LZMA dictionary is too big for this implementation");
        }
        initialize(in, uncompSize, lc, lp, pb, dictSize, presetDict, arrayCache);
    }

    private void initialize(InputStream in, long uncompSize, int lc, int lp, int pb, int dictSize, byte[] presetDict, ArrayCache arrayCache) throws IOException {
        int dictSize2;
        if (uncompSize < -1 || lc < 0 || lc > 8 || lp < 0 || lp > 4 || pb < 0 || pb > 4) {
            throw new IllegalArgumentException();
        }
        this.in = in;
        this.arrayCache = arrayCache;
        int dictSize3 = getDictSize(dictSize);
        if (uncompSize >= 0 && dictSize3 > uncompSize) {
            int dictSize4 = getDictSize((int) uncompSize);
            dictSize2 = dictSize4;
        } else {
            dictSize2 = dictSize3;
        }
        this.lz = new LZDecoder(getDictSize(dictSize2), presetDict, arrayCache);
        RangeDecoderFromStream rangeDecoderFromStream = new RangeDecoderFromStream(in);
        this.rc = rangeDecoderFromStream;
        this.lzma = new LZMADecoder(this.lz, rangeDecoderFromStream, lc, lp, pb);
        this.remainingSize = uncompSize;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.tempBuf, 0, 1) == -1) {
            return -1;
        }
        return this.tempBuf[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.endReached) {
            return -1;
        }
        int size = 0;
        while (len > 0) {
            int copySizeMax = len;
            try {
                if (this.remainingSize >= 0 && this.remainingSize < len) {
                    copySizeMax = (int) this.remainingSize;
                }
                this.lz.setLimit(copySizeMax);
                try {
                    this.lzma.decode();
                } catch (CorruptedInputException e) {
                    if (this.remainingSize != -1 || !this.lzma.endMarkerDetected()) {
                        throw e;
                    }
                    this.endReached = true;
                    this.rc.normalize();
                }
                int copiedSize = this.lz.flush(buf, off);
                off += copiedSize;
                len -= copiedSize;
                size += copiedSize;
                if (this.remainingSize >= 0) {
                    long j = this.remainingSize - ((long) copiedSize);
                    this.remainingSize = j;
                    if (j == 0) {
                        this.endReached = true;
                    }
                }
                if (this.endReached) {
                    if (!this.rc.isFinished() || this.lz.hasPending()) {
                        throw new CorruptedInputException();
                    }
                    putArraysToCache();
                    if (size == 0) {
                        return -1;
                    }
                    return size;
                }
            } catch (IOException e2) {
                this.exception = e2;
                throw e2;
            }
        }
        return size;
    }

    private void putArraysToCache() {
        LZDecoder lZDecoder = this.lz;
        if (lZDecoder != null) {
            lZDecoder.putArraysToCache(this.arrayCache);
            this.lz = null;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.in != null) {
            putArraysToCache();
            try {
                this.in.close();
            } finally {
                this.in = null;
            }
        }
    }
}
