package org.tukaani.xz.lz;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.ArrayCache;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LZEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MF_BT4 = 20;
    public static final int MF_HC4 = 4;
    final byte[] buf;
    final int bufSize;
    private final int keepSizeAfter;
    private final int keepSizeBefore;
    final int matchLenMax;
    final int niceLen;
    int readPos = -1;
    private int readLimit = -1;
    private boolean finishing = $assertionsDisabled;
    private int writePos = 0;
    private int pendingSize = 0;

    public abstract Matches getMatches();

    public abstract void skip(int i);

    static void normalize(int[] positions, int positionsCount, int normalizationOffset) {
        for (int i = 0; i < positionsCount; i++) {
            if (positions[i] <= normalizationOffset) {
                positions[i] = 0;
            } else {
                positions[i] = positions[i] - normalizationOffset;
            }
        }
    }

    private static int getBufSize(int dictSize, int extraSizeBefore, int extraSizeAfter, int matchLenMax) {
        int keepSizeBefore = extraSizeBefore + dictSize;
        int keepSizeAfter = extraSizeAfter + matchLenMax;
        int reserveSize = Math.min((dictSize / 2) + 262144, 536870912);
        return keepSizeBefore + keepSizeAfter + reserveSize;
    }

    public static int getMemoryUsage(int dictSize, int extraSizeBefore, int extraSizeAfter, int matchLenMax, int mf) {
        int m = (getBufSize(dictSize, extraSizeBefore, extraSizeAfter, matchLenMax) / 1024) + 10;
        if (mf == 4) {
            return m + HC4.getMemoryUsage(dictSize);
        }
        if (mf == 20) {
            return m + BT4.getMemoryUsage(dictSize);
        }
        throw new IllegalArgumentException();
    }

    public static LZEncoder getInstance(int dictSize, int extraSizeBefore, int extraSizeAfter, int niceLen, int matchLenMax, int mf, int depthLimit, ArrayCache arrayCache) {
        if (mf != 4) {
            if (mf == 20) {
                return new BT4(dictSize, extraSizeBefore, extraSizeAfter, niceLen, matchLenMax, depthLimit, arrayCache);
            }
            throw new IllegalArgumentException();
        }
        return new HC4(dictSize, extraSizeBefore, extraSizeAfter, niceLen, matchLenMax, depthLimit, arrayCache);
    }

    LZEncoder(int dictSize, int extraSizeBefore, int extraSizeAfter, int niceLen, int matchLenMax, ArrayCache arrayCache) {
        int bufSize = getBufSize(dictSize, extraSizeBefore, extraSizeAfter, matchLenMax);
        this.bufSize = bufSize;
        this.buf = arrayCache.getByteArray(bufSize, $assertionsDisabled);
        this.keepSizeBefore = extraSizeBefore + dictSize;
        this.keepSizeAfter = extraSizeAfter + matchLenMax;
        this.matchLenMax = matchLenMax;
        this.niceLen = niceLen;
    }

    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.buf);
    }

    public void setPresetDict(int dictSize, byte[] presetDict) {
        if (presetDict != null) {
            int copySize = Math.min(presetDict.length, dictSize);
            int offset = presetDict.length - copySize;
            System.arraycopy(presetDict, offset, this.buf, 0, copySize);
            this.writePos += copySize;
            skip(copySize);
        }
    }

    private void moveWindow() {
        int moveOffset = ((this.readPos + 1) - this.keepSizeBefore) & (-16);
        int moveSize = this.writePos - moveOffset;
        byte[] bArr = this.buf;
        System.arraycopy(bArr, moveOffset, bArr, 0, moveSize);
        this.readPos -= moveOffset;
        this.readLimit -= moveOffset;
        this.writePos -= moveOffset;
    }

    public int fillWindow(byte[] in, int off, int len) {
        if (this.readPos >= this.bufSize - this.keepSizeAfter) {
            moveWindow();
        }
        int i = this.bufSize;
        int i2 = this.writePos;
        if (len > i - i2) {
            len = i - i2;
        }
        System.arraycopy(in, off, this.buf, this.writePos, len);
        int i3 = this.writePos + len;
        this.writePos = i3;
        int i4 = this.keepSizeAfter;
        if (i3 >= i4) {
            this.readLimit = i3 - i4;
        }
        processPendingBytes();
        return len;
    }

    private void processPendingBytes() {
        int i;
        int i2 = this.pendingSize;
        if (i2 > 0 && (i = this.readPos) < this.readLimit) {
            this.readPos = i - i2;
            int oldPendingSize = this.pendingSize;
            this.pendingSize = 0;
            skip(oldPendingSize);
        }
    }

    public boolean isStarted() {
        if (this.readPos != -1) {
            return true;
        }
        return $assertionsDisabled;
    }

    public void setFlushing() {
        this.readLimit = this.writePos - 1;
        processPendingBytes();
    }

    public void setFinishing() {
        this.readLimit = this.writePos - 1;
        this.finishing = true;
        processPendingBytes();
    }

    public boolean hasEnoughData(int alreadyReadLen) {
        if (this.readPos - alreadyReadLen < this.readLimit) {
            return true;
        }
        return $assertionsDisabled;
    }

    public void copyUncompressed(OutputStream out, int backward, int len) throws IOException {
        out.write(this.buf, (this.readPos + 1) - backward, len);
    }

    public int getAvail() {
        return this.writePos - this.readPos;
    }

    public int getPos() {
        return this.readPos;
    }

    public int getByte(int backward) {
        return this.buf[this.readPos - backward] & 255;
    }

    public int getByte(int forward, int backward) {
        return this.buf[(this.readPos + forward) - backward] & 255;
    }

    public int getMatchLen(int dist, int lenLimit) {
        int backPos = (this.readPos - dist) - 1;
        int len = 0;
        while (len < lenLimit) {
            byte[] bArr = this.buf;
            if (bArr[this.readPos + len] != bArr[backPos + len]) {
                break;
            }
            len++;
        }
        return len;
    }

    public int getMatchLen(int forward, int dist, int lenLimit) {
        int curPos = this.readPos + forward;
        int backPos = (curPos - dist) - 1;
        int len = 0;
        while (len < lenLimit) {
            byte[] bArr = this.buf;
            if (bArr[curPos + len] != bArr[backPos + len]) {
                break;
            }
            len++;
        }
        return len;
    }

    public boolean verifyMatches(Matches matches) {
        int lenLimit = Math.min(getAvail(), this.matchLenMax);
        for (int i = 0; i < matches.count; i++) {
            if (getMatchLen(matches.dist[i], lenLimit) != matches.len[i]) {
                return $assertionsDisabled;
            }
        }
        return true;
    }

    int movePos(int requiredForFlushing, int requiredForFinishing) {
        int i = this.readPos + 1;
        this.readPos = i;
        int avail = this.writePos - i;
        if (avail < requiredForFlushing) {
            if (avail < requiredForFinishing || !this.finishing) {
                this.pendingSize++;
                return 0;
            }
            return avail;
        }
        return avail;
    }
}
