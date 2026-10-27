package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Codec {
    public static final BHSDCodec BCI5 = new BHSDCodec(5, 4);
    public static final BHSDCodec BRANCH5 = new BHSDCodec(5, 4, 2);
    public static final BHSDCodec BYTE1 = new BHSDCodec(1, 256);
    public static final BHSDCodec CHAR3 = new BHSDCodec(3, 128);
    public static final BHSDCodec DELTA5 = new BHSDCodec(5, 64, 1, 1);
    public static final BHSDCodec MDELTA5 = new BHSDCodec(5, 64, 2, 1);
    public static final BHSDCodec SIGNED5 = new BHSDCodec(5, 64, 1);
    public static final BHSDCodec UDELTA5 = new BHSDCodec(5, 64, 0, 1);
    public static final BHSDCodec UNSIGNED5 = new BHSDCodec(5, 64);
    public int lastBandLength;

    public abstract int decode(InputStream inputStream) throws Pack200Exception, IOException;

    public abstract int decode(InputStream inputStream, long j) throws Pack200Exception, IOException;

    public abstract byte[] encode(int i) throws Pack200Exception;

    public abstract byte[] encode(int i, int i2) throws Pack200Exception;

    public int[] decodeInts(int i, InputStream inputStream) throws Pack200Exception, IOException {
        this.lastBandLength = 0;
        int[] iArr = new int[i];
        int iDecode = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iDecode = decode(inputStream, iDecode);
            iArr[i2] = iDecode;
        }
        return iArr;
    }

    public int[] decodeInts(int i, InputStream inputStream, int i2) throws Pack200Exception, IOException {
        int i3 = i + 1;
        int[] iArr = new int[i3];
        iArr[0] = i2;
        for (int i4 = 1; i4 < i3; i4++) {
            i2 = decode(inputStream, i2);
            iArr[i4] = i2;
        }
        return iArr;
    }

    public byte[] encode(int[] iArr) throws Pack200Exception {
        int length = iArr.length;
        byte[][] bArr = new byte[length][];
        int i = 0;
        int length2 = 0;
        while (i < iArr.length) {
            byte[] bArrEncode = encode(iArr[i], i > 0 ? iArr[i - 1] : 0);
            bArr[i] = bArrEncode;
            length2 += bArrEncode.length;
            i++;
        }
        byte[] bArr2 = new byte[length2];
        int length3 = 0;
        for (int i2 = 0; i2 < length; i2++) {
            byte[] bArr3 = bArr[i2];
            System.arraycopy(bArr3, 0, bArr2, length3, bArr3.length);
            length3 += bArr[i2].length;
        }
        return bArr2;
    }
}
