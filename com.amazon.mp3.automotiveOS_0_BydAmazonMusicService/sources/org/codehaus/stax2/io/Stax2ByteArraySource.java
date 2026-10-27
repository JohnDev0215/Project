package org.codehaus.stax2.io;

/* JADX INFO: loaded from: classes7.dex */
public class Stax2ByteArraySource extends Stax2BlockSource {
    final byte[] mBuffer;
    final int mLength;
    final int mStart;

    public Stax2ByteArraySource(byte[] bArr, int i, int i2) {
        this.mBuffer = bArr;
        this.mStart = i;
        this.mLength = i2;
    }

    public int getBufferStart() {
        return this.mStart;
    }

    public int getBufferLength() {
        return this.mLength;
    }

    public int getBufferEnd() {
        int i = this.mStart;
        int i2 = this.mLength;
        return i2 > 0 ? i + i2 : i;
    }
}
