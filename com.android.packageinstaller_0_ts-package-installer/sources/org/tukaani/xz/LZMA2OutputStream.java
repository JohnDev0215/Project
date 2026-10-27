package org.tukaani.xz;

import java.io.DataOutputStream;
import java.io.IOException;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lzma.LZMAEncoder;
import org.tukaani.xz.rangecoder.RangeEncoderToBuffer;

/* JADX INFO: loaded from: classes2.dex */
class LZMA2OutputStream extends FinishableOutputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int COMPRESSED_SIZE_MAX = 65536;
    private final ArrayCache arrayCache;
    private boolean dictResetNeeded;
    private LZEncoder lz;
    private LZMAEncoder lzma;
    private FinishableOutputStream out;
    private final DataOutputStream outData;
    private final int props;
    private RangeEncoderToBuffer rc;
    private boolean stateResetNeeded = true;
    private boolean propsNeeded = true;
    private int pendingSize = 0;
    private boolean finished = $assertionsDisabled;
    private IOException exception = null;
    private final byte[] tempBuf = new byte[1];

    private static int getExtraSizeBefore(int dictSize) {
        if (65536 > dictSize) {
            return 65536 - dictSize;
        }
        return 0;
    }

    static int getMemoryUsage(LZMA2Options options) {
        int dictSize = options.getDictSize();
        int extraSizeBefore = getExtraSizeBefore(dictSize);
        return LZMAEncoder.getMemoryUsage(options.getMode(), dictSize, extraSizeBefore, options.getMatchFinder()) + 70;
    }

    LZMA2OutputStream(FinishableOutputStream out, LZMA2Options options, ArrayCache arrayCache) {
        this.dictResetNeeded = true;
        if (out == null) {
            throw null;
        }
        this.arrayCache = arrayCache;
        this.out = out;
        this.outData = new DataOutputStream(out);
        this.rc = new RangeEncoderToBuffer(65536, arrayCache);
        int dictSize = options.getDictSize();
        int extraSizeBefore = getExtraSizeBefore(dictSize);
        LZMAEncoder lZMAEncoder = LZMAEncoder.getInstance(this.rc, options.getLc(), options.getLp(), options.getPb(), options.getMode(), dictSize, extraSizeBefore, options.getNiceLen(), options.getMatchFinder(), options.getDepthLimit(), this.arrayCache);
        this.lzma = lZMAEncoder;
        this.lz = lZMAEncoder.getLZEncoder();
        byte[] presetDict = options.getPresetDict();
        if (presetDict != null && presetDict.length > 0) {
            this.lz.setPresetDict(dictSize, presetDict);
            this.dictResetNeeded = $assertionsDisabled;
        }
        this.props = (((options.getPb() * 5) + options.getLp()) * 9) + options.getLc();
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
        while (len > 0) {
            try {
                int used = this.lz.fillWindow(buf, off, len);
                off += used;
                len -= used;
                this.pendingSize += used;
                if (this.lzma.encodeForLZMA2()) {
                    writeChunk();
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }

    private void writeChunk() throws IOException {
        int compressedSize = this.rc.finish();
        int uncompressedSize = this.lzma.getUncompressedSize();
        if (compressedSize + 2 < uncompressedSize) {
            writeLZMA(uncompressedSize, compressedSize);
        } else {
            this.lzma.reset();
            uncompressedSize = this.lzma.getUncompressedSize();
            writeUncompressed(uncompressedSize);
        }
        this.pendingSize -= uncompressedSize;
        this.lzma.resetUncompressedSize();
        this.rc.reset();
    }

    private void writeLZMA(int uncompressedSize, int compressedSize) throws IOException {
        int control;
        if (this.propsNeeded) {
            if (this.dictResetNeeded) {
                control = 224;
            } else {
                control = 192;
            }
        } else if (this.stateResetNeeded) {
            control = 160;
        } else {
            control = 128;
        }
        this.outData.writeByte(control | ((uncompressedSize - 1) >>> 16));
        this.outData.writeShort(uncompressedSize - 1);
        this.outData.writeShort(compressedSize - 1);
        if (this.propsNeeded) {
            this.outData.writeByte(this.props);
        }
        this.rc.write(this.out);
        this.propsNeeded = $assertionsDisabled;
        this.stateResetNeeded = $assertionsDisabled;
        this.dictResetNeeded = $assertionsDisabled;
    }

    private void writeUncompressed(int uncompressedSize) throws IOException {
        while (true) {
            int i = 1;
            if (uncompressedSize > 0) {
                int chunkSize = Math.min(uncompressedSize, 65536);
                DataOutputStream dataOutputStream = this.outData;
                if (!this.dictResetNeeded) {
                    i = 2;
                }
                dataOutputStream.writeByte(i);
                this.outData.writeShort(chunkSize - 1);
                this.lz.copyUncompressed(this.out, uncompressedSize, chunkSize);
                uncompressedSize -= chunkSize;
                this.dictResetNeeded = $assertionsDisabled;
            } else {
                this.stateResetNeeded = true;
                return;
            }
        }
    }

    private void writeEndMarker() throws IOException {
        IOException e = this.exception;
        if (e != null) {
            throw e;
        }
        this.lz.setFinishing();
        while (this.pendingSize > 0) {
            try {
                this.lzma.encodeForLZMA2();
                writeChunk();
            } catch (IOException e2) {
                this.exception = e2;
                throw e2;
            }
        }
        this.out.write(0);
        this.finished = true;
        this.lzma.putArraysToCache(this.arrayCache);
        this.lzma = null;
        this.lz = null;
        this.rc.putArraysToCache(this.arrayCache);
        this.rc = null;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        try {
            this.lz.setFlushing();
            while (this.pendingSize > 0) {
                this.lzma.encodeForLZMA2();
                writeChunk();
            }
            this.out.flush();
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    @Override // org.tukaani.xz.FinishableOutputStream
    public void finish() throws IOException {
        if (!this.finished) {
            writeEndMarker();
            try {
                this.out.finish();
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            if (!this.finished) {
                try {
                    writeEndMarker();
                } catch (IOException e) {
                }
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
