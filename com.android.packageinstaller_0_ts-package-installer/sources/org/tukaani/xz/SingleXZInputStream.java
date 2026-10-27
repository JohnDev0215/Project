package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.IndexHash;

/* JADX INFO: loaded from: classes2.dex */
public class SingleXZInputStream extends InputStream {
    private final ArrayCache arrayCache;
    private BlockInputStream blockDecoder;
    private final Check check;
    private boolean endReached;
    private IOException exception;
    private InputStream in;
    private final IndexHash indexHash;
    private final int memoryLimit;
    private final StreamFlags streamHeaderFlags;
    private final byte[] tempBuf;
    private final boolean verifyCheck;

    private static byte[] readStreamHeader(InputStream in) throws IOException {
        byte[] streamHeader = new byte[12];
        new DataInputStream(in).readFully(streamHeader);
        return streamHeader;
    }

    public SingleXZInputStream(InputStream in) throws IOException {
        this(in, -1);
    }

    public SingleXZInputStream(InputStream in, ArrayCache arrayCache) throws IOException {
        this(in, -1, arrayCache);
    }

    public SingleXZInputStream(InputStream in, int memoryLimit) throws IOException {
        this(in, memoryLimit, true);
    }

    public SingleXZInputStream(InputStream in, int memoryLimit, ArrayCache arrayCache) throws IOException {
        this(in, memoryLimit, true, arrayCache);
    }

    public SingleXZInputStream(InputStream in, int memoryLimit, boolean verifyCheck) throws IOException {
        this(in, memoryLimit, verifyCheck, ArrayCache.getDefaultCache());
    }

    public SingleXZInputStream(InputStream in, int memoryLimit, boolean verifyCheck, ArrayCache arrayCache) throws IOException {
        this(in, memoryLimit, verifyCheck, readStreamHeader(in), arrayCache);
    }

    SingleXZInputStream(InputStream in, int memoryLimit, boolean verifyCheck, byte[] streamHeader, ArrayCache arrayCache) throws IOException {
        this.blockDecoder = null;
        this.indexHash = new IndexHash();
        this.endReached = false;
        this.exception = null;
        this.tempBuf = new byte[1];
        this.arrayCache = arrayCache;
        this.in = in;
        this.memoryLimit = memoryLimit;
        this.verifyCheck = verifyCheck;
        StreamFlags streamFlagsDecodeStreamHeader = DecoderUtil.decodeStreamHeader(streamHeader);
        this.streamHeaderFlags = streamFlagsDecodeStreamHeader;
        this.check = Check.getInstance(streamFlagsDecodeStreamHeader.checkType);
    }

    public int getCheckType() {
        return this.streamHeaderFlags.checkType;
    }

    public String getCheckName() {
        return this.check.getName();
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
        int off2 = off;
        int len2 = len;
        int size = 0;
        while (len2 > 0) {
            try {
                if (this.blockDecoder == null) {
                    try {
                        this.blockDecoder = new BlockInputStream(this.in, this.check, this.verifyCheck, this.memoryLimit, -1L, -1L, this.arrayCache);
                    } catch (IndexIndicatorException e) {
                        this.indexHash.validate(this.in);
                        validateStreamFooter();
                        this.endReached = true;
                        if (size > 0) {
                            return size;
                        }
                        return -1;
                    }
                }
                int ret = this.blockDecoder.read(buf, off2, len2);
                if (ret > 0) {
                    size += ret;
                    off2 += ret;
                    len2 -= ret;
                } else if (ret == -1) {
                    this.indexHash.add(this.blockDecoder.getUnpaddedSize(), this.blockDecoder.getUncompressedSize());
                    this.blockDecoder = null;
                }
            } catch (IOException e2) {
                this.exception = e2;
                if (size == 0) {
                    throw e2;
                }
            }
        }
        return size;
    }

    private void validateStreamFooter() throws IOException {
        byte[] buf = new byte[12];
        new DataInputStream(this.in).readFully(buf);
        StreamFlags streamFooterFlags = DecoderUtil.decodeStreamFooter(buf);
        if (!DecoderUtil.areStreamFlagsEqual(this.streamHeaderFlags, streamFooterFlags) || this.indexHash.getIndexSize() != streamFooterFlags.backwardSize) {
            throw new CorruptedInputException("XZ Stream Footer does not match Stream Header");
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        BlockInputStream blockInputStream = this.blockDecoder;
        if (blockInputStream == null) {
            return 0;
        }
        return blockInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        close(true);
    }

    public void close(boolean closeInput) throws IOException {
        if (this.in != null) {
            BlockInputStream blockInputStream = this.blockDecoder;
            if (blockInputStream != null) {
                blockInputStream.close();
                this.blockDecoder = null;
            }
            if (closeInput) {
                try {
                    this.in.close();
                } finally {
                    this.in = null;
                }
            }
        }
    }
}
