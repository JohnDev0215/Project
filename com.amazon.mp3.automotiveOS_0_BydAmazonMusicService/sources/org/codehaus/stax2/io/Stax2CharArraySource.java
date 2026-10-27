package org.codehaus.stax2.io;

/* JADX INFO: loaded from: classes7.dex */
public class Stax2CharArraySource extends Stax2BlockSource {
    final char[] mBuffer;
    final int mLength;
    final int mStart;

    public Stax2CharArraySource(char[] cArr, int i, int i2) {
        this.mBuffer = cArr;
        this.mStart = i;
        this.mLength = i2;
    }

    public int getBufferStart() {
        return this.mStart;
    }

    public int getBufferLength() {
        return this.mLength;
    }
}
