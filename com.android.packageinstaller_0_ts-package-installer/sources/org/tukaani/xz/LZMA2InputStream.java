package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.lzma.LZMADecoder;
import org.tukaani.xz.rangecoder.RangeDecoderFromBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class LZMA2InputStream extends InputStream {
    private static final int COMPRESSED_SIZE_MAX = 65536;
    public static final int DICT_SIZE_MAX = 2147483632;
    public static final int DICT_SIZE_MIN = 4096;
    private final ArrayCache arrayCache;
    private boolean endReached;
    private IOException exception;
    private DataInputStream in;
    private boolean isLZMAChunk;
    private LZDecoder lz;
    private LZMADecoder lzma;
    private boolean needDictReset;
    private boolean needProps;
    private RangeDecoderFromBuffer rc;
    private final byte[] tempBuf;
    private int uncompressedSize;

    public static int getMemoryUsage(int dictSize) {
        return (getDictSize(dictSize) / 1024) + 104;
    }

    private static int getDictSize(int dictSize) {
        if (dictSize < 4096 || dictSize > 2147483632) {
            throw new IllegalArgumentException("Unsupported dictionary size " + dictSize);
        }
        return (dictSize + 15) & (-16);
    }

    public LZMA2InputStream(InputStream in, int dictSize) {
        this(in, dictSize, null);
    }

    public LZMA2InputStream(InputStream in, int dictSize, byte[] presetDict) {
        this(in, dictSize, presetDict, ArrayCache.getDefaultCache());
    }

    LZMA2InputStream(InputStream in, int dictSize, byte[] presetDict, ArrayCache arrayCache) {
        this.uncompressedSize = 0;
        this.isLZMAChunk = false;
        this.needDictReset = true;
        this.needProps = true;
        this.endReached = false;
        this.exception = null;
        this.tempBuf = new byte[1];
        if (in == null) {
            throw null;
        }
        this.arrayCache = arrayCache;
        this.in = new DataInputStream(in);
        this.rc = new RangeDecoderFromBuffer(65536, arrayCache);
        this.lz = new LZDecoder(getDictSize(dictSize), presetDict, arrayCache);
        if (presetDict != null && presetDict.length > 0) {
            this.needDictReset = false;
        }
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
            try {
                if (this.uncompressedSize == 0) {
                    decodeChunkHeader();
                    if (this.endReached) {
                        if (size == 0) {
                            return -1;
                        }
                        return size;
                    }
                }
                int copySizeMax = Math.min(this.uncompressedSize, len);
                if (!this.isLZMAChunk) {
                    this.lz.copyUncompressed(this.in, copySizeMax);
                } else {
                    this.lz.setLimit(copySizeMax);
                    this.lzma.decode();
                }
                int copiedSize = this.lz.flush(buf, off);
                off += copiedSize;
                len -= copiedSize;
                size += copiedSize;
                int i = this.uncompressedSize - copiedSize;
                this.uncompressedSize = i;
                if (i == 0 && (!this.rc.isFinished() || this.lz.hasPending())) {
                    throw new CorruptedInputException();
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
        return size;
    }

    private void decodeChunkHeader() throws IOException {
        int control = this.in.readUnsignedByte();
        if (control == 0) {
            this.endReached = true;
            putArraysToCache();
            return;
        }
        if (control >= 224 || control == 1) {
            this.needProps = true;
            this.needDictReset = false;
            this.lz.reset();
        } else if (this.needDictReset) {
            throw new CorruptedInputException();
        }
        if (control >= 128) {
            this.isLZMAChunk = true;
            int i = (control & 31) << 16;
            this.uncompressedSize = i;
            this.uncompressedSize = i + this.in.readUnsignedShort() + 1;
            int compressedSize = this.in.readUnsignedShort() + 1;
            if (control >= 192) {
                this.needProps = false;
                decodeProps();
            } else {
                if (this.needProps) {
                    throw new CorruptedInputException();
                }
                if (control >= 160) {
                    this.lzma.reset();
                }
            }
            this.rc.prepareInputBuffer(this.in, compressedSize);
            return;
        }
        if (control > 2) {
            throw new CorruptedInputException();
        }
        this.isLZMAChunk = false;
        this.uncompressedSize = this.in.readUnsignedShort() + 1;
    }

    private void decodeProps() throws IOException {
        int props = this.in.readUnsignedByte();
        if (props > 224) {
            throw new CorruptedInputException();
        }
        int pb = props / 45;
        int props2 = props - ((pb * 9) * 5);
        int lp = props2 / 9;
        int lc = props2 - (lp * 9);
        if (lc + lp > 4) {
            throw new CorruptedInputException();
        }
        this.lzma = new LZMADecoder(this.lz, this.rc, lc, lp, pb);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        DataInputStream dataInputStream = this.in;
        if (dataInputStream == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException == null) {
            return this.isLZMAChunk ? this.uncompressedSize : Math.min(this.uncompressedSize, dataInputStream.available());
        }
        throw iOException;
    }

    private void putArraysToCache() {
        LZDecoder lZDecoder = this.lz;
        if (lZDecoder != null) {
            lZDecoder.putArraysToCache(this.arrayCache);
            this.lz = null;
            this.rc.putArraysToCache(this.arrayCache);
            this.rc = null;
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
