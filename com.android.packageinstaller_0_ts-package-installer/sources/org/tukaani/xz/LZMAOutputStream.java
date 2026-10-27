package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lzma.LZMAEncoder;
import org.tukaani.xz.rangecoder.RangeEncoderToStream;

/* JADX INFO: loaded from: classes2.dex */
public class LZMAOutputStream extends FinishableOutputStream {
    private final ArrayCache arrayCache;
    private long currentUncompressedSize;
    private IOException exception;
    private final long expectedUncompressedSize;
    private boolean finished;
    private LZEncoder lz;
    private LZMAEncoder lzma;
    private OutputStream out;
    private final int props;
    private final RangeEncoderToStream rc;
    private final byte[] tempBuf;
    private final boolean useEndMarker;

    private LZMAOutputStream(OutputStream out, LZMA2Options options, boolean useHeader, boolean useEndMarker, long expectedUncompressedSize, ArrayCache arrayCache) throws IOException {
        int dictSize;
        this.currentUncompressedSize = 0L;
        this.finished = false;
        this.exception = null;
        this.tempBuf = new byte[1];
        if (out == null) {
            throw null;
        }
        if (expectedUncompressedSize < -1) {
            throw new IllegalArgumentException("Invalid expected input size (less than -1)");
        }
        this.useEndMarker = useEndMarker;
        this.expectedUncompressedSize = expectedUncompressedSize;
        this.arrayCache = arrayCache;
        this.out = out;
        this.rc = new RangeEncoderToStream(out);
        int dictSize2 = options.getDictSize();
        LZMAEncoder lZMAEncoder = LZMAEncoder.getInstance(this.rc, options.getLc(), options.getLp(), options.getPb(), options.getMode(), dictSize2, 0, options.getNiceLen(), options.getMatchFinder(), options.getDepthLimit(), arrayCache);
        this.lzma = lZMAEncoder;
        this.lz = lZMAEncoder.getLZEncoder();
        byte[] presetDict = options.getPresetDict();
        if (presetDict == null || presetDict.length <= 0) {
            dictSize = dictSize2;
        } else {
            if (useHeader) {
                throw new UnsupportedOptionsException("Preset dictionary cannot be used in .lzma files (try a raw LZMA stream instead)");
            }
            dictSize = dictSize2;
            this.lz.setPresetDict(dictSize, presetDict);
        }
        int pb = (((options.getPb() * 5) + options.getLp()) * 9) + options.getLc();
        this.props = pb;
        if (useHeader) {
            out.write(pb);
            int dictSize3 = dictSize;
            for (int i = 0; i < 4; i++) {
                out.write(dictSize3 & 255);
                dictSize3 >>>= 8;
            }
            for (int i2 = 0; i2 < 8; i2++) {
                out.write(((int) (expectedUncompressedSize >>> (i2 * 8))) & 255);
            }
        }
    }

    public LZMAOutputStream(OutputStream out, LZMA2Options options, long inputSize) throws IOException {
        this(out, options, inputSize, ArrayCache.getDefaultCache());
    }

    public LZMAOutputStream(OutputStream out, LZMA2Options options, long inputSize, ArrayCache arrayCache) throws IOException {
        this(out, options, true, inputSize == -1, inputSize, arrayCache);
    }

    public LZMAOutputStream(OutputStream out, LZMA2Options options, boolean useEndMarker) throws IOException {
        this(out, options, useEndMarker, ArrayCache.getDefaultCache());
    }

    public LZMAOutputStream(OutputStream out, LZMA2Options options, boolean useEndMarker, ArrayCache arrayCache) throws IOException {
        this(out, options, false, useEndMarker, -1L, arrayCache);
    }

    public int getProps() {
        return this.props;
    }

    public long getUncompressedSize() {
        return this.currentUncompressedSize;
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        byte[] bArr = this.tempBuf;
        bArr[0] = (byte) b;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] buf, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        long j = this.expectedUncompressedSize;
        if (j != -1 && j - this.currentUncompressedSize < len) {
            throw new XZIOException("Expected uncompressed input size (" + this.expectedUncompressedSize + " bytes) was exceeded");
        }
        this.currentUncompressedSize += (long) len;
        while (len > 0) {
            try {
                int used = this.lz.fillWindow(buf, off, len);
                off += used;
                len -= used;
                this.lzma.encodeForLZMA1();
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        throw new XZIOException("LZMAOutputStream does not support flushing");
    }

    @Override // org.tukaani.xz.FinishableOutputStream
    public void finish() throws IOException {
        if (!this.finished) {
            IOException e = this.exception;
            if (e != null) {
                throw e;
            }
            try {
                if (this.expectedUncompressedSize != -1 && this.expectedUncompressedSize != this.currentUncompressedSize) {
                    throw new XZIOException("Expected uncompressed size (" + this.expectedUncompressedSize + ") doesn't equal the number of bytes written to the stream (" + this.currentUncompressedSize + ")");
                }
                this.lz.setFinishing();
                this.lzma.encodeForLZMA1();
                if (this.useEndMarker) {
                    this.lzma.encodeLZMA1EndMarker();
                }
                this.rc.finish();
                this.finished = true;
                this.lzma.putArraysToCache(this.arrayCache);
                this.lzma = null;
                this.lz = null;
            } catch (IOException e2) {
                this.exception = e2;
                throw e2;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            try {
                finish();
            } catch (IOException e) {
            }
            try {
                this.out.close();
            } catch (IOException e2) {
                if (this.exception == null) {
                    this.exception = e2;
                }
            }
            this.out = null;
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
    }
}
