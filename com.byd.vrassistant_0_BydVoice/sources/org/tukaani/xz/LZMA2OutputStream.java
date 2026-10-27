package org.tukaani.xz;

import java.io.IOException;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lzma.LZMAEncoder;
import org.tukaani.xz.rangecoder.RangeEncoderToBuffer;

/* JADX INFO: loaded from: classes4.dex */
class LZMA2OutputStream extends FinishableOutputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int COMPRESSED_SIZE_MAX = 65536;
    private final ArrayCache arrayCache;
    private boolean dictResetNeeded;
    private LZEncoder lz;
    private LZMAEncoder lzma;
    private FinishableOutputStream out;
    private final int props;
    private RangeEncoderToBuffer rc;
    private boolean stateResetNeeded = true;
    private boolean propsNeeded = true;
    private int pendingSize = 0;
    private boolean finished = false;
    private IOException exception = null;
    private final byte[] chunkHeader = new byte[6];
    private final byte[] tempBuf = new byte[1];

    LZMA2OutputStream(FinishableOutputStream finishableOutputStream, LZMA2Options lZMA2Options, ArrayCache arrayCache) {
        this.dictResetNeeded = true;
        finishableOutputStream.getClass();
        this.arrayCache = arrayCache;
        this.out = finishableOutputStream;
        this.rc = new RangeEncoderToBuffer(65536, arrayCache);
        int dictSize = lZMA2Options.getDictSize();
        LZMAEncoder lZMAEncoder = LZMAEncoder.getInstance(this.rc, lZMA2Options.getLc(), lZMA2Options.getLp(), lZMA2Options.getPb(), lZMA2Options.getMode(), dictSize, getExtraSizeBefore(dictSize), lZMA2Options.getNiceLen(), lZMA2Options.getMatchFinder(), lZMA2Options.getDepthLimit(), arrayCache);
        this.lzma = lZMAEncoder;
        this.lz = lZMAEncoder.getLZEncoder();
        byte[] presetDict = lZMA2Options.getPresetDict();
        if (presetDict != null && presetDict.length > 0) {
            this.lz.setPresetDict(dictSize, presetDict);
            this.dictResetNeeded = false;
        }
        this.props = (((lZMA2Options.getPb() * 5) + lZMA2Options.getLp()) * 9) + lZMA2Options.getLc();
    }

    private static int getExtraSizeBefore(int i) {
        if (65536 > i) {
            return 65536 - i;
        }
        return 0;
    }

    static int getMemoryUsage(LZMA2Options lZMA2Options) {
        int dictSize = lZMA2Options.getDictSize();
        return LZMAEncoder.getMemoryUsage(lZMA2Options.getMode(), dictSize, getExtraSizeBefore(dictSize), lZMA2Options.getMatchFinder()) + 70;
    }

    private void writeChunk() throws IOException {
        int iFinish = this.rc.finish();
        int uncompressedSize = this.lzma.getUncompressedSize();
        if (iFinish + 2 < uncompressedSize) {
            writeLZMA(uncompressedSize, iFinish);
        } else {
            this.lzma.reset();
            uncompressedSize = this.lzma.getUncompressedSize();
            writeUncompressed(uncompressedSize);
        }
        this.pendingSize -= uncompressedSize;
        this.lzma.resetUncompressedSize();
        this.rc.reset();
    }

    private void writeEndMarker() throws IOException {
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        this.lz.setFinishing();
        while (this.pendingSize > 0) {
            try {
                this.lzma.encodeForLZMA2();
                writeChunk();
            } catch (IOException e) {
                this.exception = e;
                throw e;
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

    private void writeLZMA(int i, int i2) throws IOException {
        boolean z = this.propsNeeded;
        int i3 = z ? this.dictResetNeeded ? 224 : 192 : this.stateResetNeeded ? 160 : 128;
        int i4 = i - 1;
        byte[] bArr = this.chunkHeader;
        bArr[0] = (byte) (i3 | (i4 >>> 16));
        bArr[1] = (byte) (i4 >>> 8);
        bArr[2] = (byte) i4;
        int i5 = i2 - 1;
        bArr[3] = (byte) (i5 >>> 8);
        bArr[4] = (byte) i5;
        if (z) {
            bArr[5] = (byte) this.props;
            this.out.write(bArr, 0, 6);
        } else {
            this.out.write(bArr, 0, 5);
        }
        this.rc.write(this.out);
        this.propsNeeded = false;
        this.stateResetNeeded = false;
        this.dictResetNeeded = false;
    }

    private void writeUncompressed(int i) throws IOException {
        while (i > 0) {
            int iMin = Math.min(i, 65536);
            byte[] bArr = this.chunkHeader;
            bArr[0] = (byte) (this.dictResetNeeded ? 1 : 2);
            int i2 = iMin - 1;
            bArr[1] = (byte) (i2 >>> 8);
            bArr[2] = (byte) i2;
            this.out.write(bArr, 0, 3);
            this.lz.copyUncompressed(this.out, i, iMin);
            i -= iMin;
            this.dictResetNeeded = false;
        }
        this.stateResetNeeded = true;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            if (!this.finished) {
                try {
                    writeEndMarker();
                } catch (IOException unused) {
                }
            }
            try {
                this.out.close();
            } catch (IOException e) {
                if (this.exception == null) {
                    this.exception = e;
                }
            }
            this.out = null;
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
    }

    @Override // org.tukaani.xz.FinishableOutputStream
    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        writeEndMarker();
        try {
            this.out.finish();
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
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

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.tempBuf;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        while (i2 > 0) {
            try {
                int iFillWindow = this.lz.fillWindow(bArr, i, i2);
                i += iFillWindow;
                i2 -= iFillWindow;
                this.pendingSize += iFillWindow;
                if (this.lzma.encodeForLZMA2()) {
                    writeChunk();
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }
}
