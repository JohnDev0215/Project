package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public class PopulationCodec extends Codec {
    private int[] favoured;
    private final Codec favouredCodec;
    private int l;
    private Codec tokenCodec;
    private final Codec unfavouredCodec;

    public PopulationCodec(Codec codec, Codec codec2, Codec codec3) {
        this.favouredCodec = codec;
        this.tokenCodec = codec2;
        this.unfavouredCodec = codec3;
    }

    public PopulationCodec(Codec codec, int i, Codec codec2) {
        if (i >= 256 || i <= 0) {
            throw new IllegalArgumentException("L must be between 1..255");
        }
        this.favouredCodec = codec;
        this.l = i;
        this.unfavouredCodec = codec2;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream) throws Pack200Exception, IOException {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream, long j) throws Pack200Exception, IOException {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int i, InputStream inputStream) throws Pack200Exception, IOException {
        this.lastBandLength = 0;
        this.favoured = new int[i];
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        int i4 = -1;
        while (true) {
            int iDecode = this.favouredCodec.decode(inputStream, i3);
            if (i4 > -1 && (iDecode == i2 || iDecode == i3)) {
                break;
            }
            i4++;
            this.favoured[i4] = iDecode;
            int iAbs = Math.abs(i2);
            int iAbs2 = Math.abs(iDecode);
            if (iAbs > iAbs2) {
                i2 = iDecode;
            } else if (iAbs == iAbs2) {
                i2 = iAbs;
            }
            i3 = iDecode;
        }
        this.lastBandLength += i4;
        if (this.tokenCodec == null) {
            if (i4 < 256) {
                this.tokenCodec = Codec.BYTE1;
            } else {
                int i5 = 1;
                while (true) {
                    i5++;
                    if (i5 >= 5) {
                        break;
                    }
                    BHSDCodec bHSDCodec = new BHSDCodec(i5, 256 - this.l, 0);
                    if (bHSDCodec.encodes(i4)) {
                        this.tokenCodec = bHSDCodec;
                        break;
                    }
                }
                if (this.tokenCodec == null) {
                    throw new Pack200Exception("Cannot calculate token codec from " + i4 + " and " + this.l);
                }
            }
        }
        this.lastBandLength += i;
        int[] iArrDecodeInts = this.tokenCodec.decodeInts(i, inputStream);
        int iDecode2 = 0;
        for (int i6 = 0; i6 < i; i6++) {
            int i7 = iArrDecodeInts[i6];
            if (i7 == 0) {
                this.lastBandLength++;
                iDecode2 = this.unfavouredCodec.decode(inputStream, iDecode2);
                iArrDecodeInts[i6] = iDecode2;
            } else {
                iArrDecodeInts[i6] = this.favoured[i7 - 1];
            }
        }
        return iArrDecodeInts;
    }

    public int[] getFavoured() {
        return this.favoured;
    }

    public Codec getFavouredCodec() {
        return this.favouredCodec;
    }

    public Codec getUnfavouredCodec() {
        return this.unfavouredCodec;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i, int i2) throws Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i) throws Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    public byte[] encode(int[] iArr, int[] iArr2, int[] iArr3) throws Pack200Exception {
        int length = iArr.length + 1;
        int[] iArr4 = new int[length];
        System.arraycopy(iArr, 0, iArr4, 0, iArr.length);
        iArr4[length - 1] = iArr[iArr.length - 1];
        byte[] bArrEncode = this.favouredCodec.encode(iArr4);
        byte[] bArrEncode2 = this.tokenCodec.encode(iArr2);
        byte[] bArrEncode3 = this.unfavouredCodec.encode(iArr3);
        byte[] bArr = new byte[bArrEncode.length + bArrEncode2.length + bArrEncode3.length];
        System.arraycopy(bArrEncode, 0, bArr, 0, bArrEncode.length);
        System.arraycopy(bArrEncode2, 0, bArr, bArrEncode.length, bArrEncode2.length);
        System.arraycopy(bArrEncode3, 0, bArr, bArrEncode.length + bArrEncode2.length, bArrEncode3.length);
        return bArr;
    }

    public Codec getTokenCodec() {
        return this.tokenCodec;
    }
}
