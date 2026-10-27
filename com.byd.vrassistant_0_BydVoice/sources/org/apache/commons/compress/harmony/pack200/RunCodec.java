package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class RunCodec extends Codec {
    private final Codec aCodec;
    private final Codec bCodec;
    private int k;
    private int last;

    public RunCodec(int i, Codec codec, Codec codec2) throws Pack200Exception {
        if (i <= 0) {
            throw new Pack200Exception("Cannot have a RunCodec for a negative number of numbers");
        }
        if (codec == null || codec2 == null) {
            throw new Pack200Exception("Must supply both codecs for a RunCodec");
        }
        this.k = i;
        this.aCodec = codec;
        this.bCodec = codec2;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream) throws Pack200Exception, IOException {
        return decode(inputStream, this.last);
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream, long j) throws Pack200Exception, IOException {
        int i = this.k - 1;
        this.k = i;
        if (i >= 0) {
            int iDecode = this.aCodec.decode(inputStream, this.last);
            this.last = this.k == 0 ? 0 : iDecode;
            return normalise(iDecode, this.aCodec);
        }
        int iDecode2 = this.bCodec.decode(inputStream, this.last);
        this.last = iDecode2;
        return normalise(iDecode2, this.bCodec);
    }

    private int normalise(int i, Codec codec) {
        if (codec instanceof BHSDCodec) {
            BHSDCodec bHSDCodec = (BHSDCodec) codec;
            if (bHSDCodec.isDelta()) {
                long jCardinality = bHSDCodec.cardinality();
                while (true) {
                    long j = i;
                    if (j <= bHSDCodec.largest()) {
                        break;
                    }
                    i = (int) (j - jCardinality);
                }
                while (true) {
                    long j2 = i;
                    if (j2 >= bHSDCodec.smallest()) {
                        break;
                    }
                    i = (int) (j2 + jCardinality);
                }
            }
        }
        return i;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int i, InputStream inputStream) throws Pack200Exception, IOException {
        int[] iArr = new int[i];
        int[] iArrDecodeInts = this.aCodec.decodeInts(this.k, inputStream);
        normalise(iArrDecodeInts, this.aCodec);
        int[] iArrDecodeInts2 = this.bCodec.decodeInts(i - this.k, inputStream);
        normalise(iArrDecodeInts2, this.bCodec);
        System.arraycopy(iArrDecodeInts, 0, iArr, 0, this.k);
        int i2 = this.k;
        System.arraycopy(iArrDecodeInts2, 0, iArr, i2, i - i2);
        this.lastBandLength = this.aCodec.lastBandLength + this.bCodec.lastBandLength;
        return iArr;
    }

    private void normalise(int[] iArr, Codec codec) {
        if (codec instanceof BHSDCodec) {
            BHSDCodec bHSDCodec = (BHSDCodec) codec;
            if (bHSDCodec.isDelta()) {
                long jCardinality = bHSDCodec.cardinality();
                for (int i = 0; i < iArr.length; i++) {
                    while (iArr[i] > bHSDCodec.largest()) {
                        iArr[i] = (int) (((long) iArr[i]) - jCardinality);
                    }
                    while (iArr[i] < bHSDCodec.smallest()) {
                        iArr[i] = (int) (((long) iArr[i]) + jCardinality);
                    }
                }
                return;
            }
            return;
        }
        if (codec instanceof PopulationCodec) {
            PopulationCodec populationCodec = (PopulationCodec) codec;
            int[] iArr2 = (int[]) populationCodec.getFavoured().clone();
            Arrays.sort(iArr2);
            for (int i2 = 0; i2 < iArr.length; i2++) {
                Codec favouredCodec = Arrays.binarySearch(iArr2, iArr[i2]) > -1 ? populationCodec.getFavouredCodec() : populationCodec.getUnfavouredCodec();
                if (favouredCodec instanceof BHSDCodec) {
                    BHSDCodec bHSDCodec2 = (BHSDCodec) favouredCodec;
                    if (bHSDCodec2.isDelta()) {
                        long jCardinality2 = bHSDCodec2.cardinality();
                        while (iArr[i2] > bHSDCodec2.largest()) {
                            iArr[i2] = (int) (((long) iArr[i2]) - jCardinality2);
                        }
                        while (iArr[i2] < bHSDCodec2.smallest()) {
                            iArr[i2] = (int) (((long) iArr[i2]) + jCardinality2);
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        return "RunCodec[k=" + this.k + ";aCodec=" + this.aCodec + "bCodec=" + this.bCodec + "]";
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i, int i2) throws Pack200Exception {
        throw new Pack200Exception("Must encode entire band at once with a RunCodec");
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i) throws Pack200Exception {
        throw new Pack200Exception("Must encode entire band at once with a RunCodec");
    }

    public int getK() {
        return this.k;
    }

    public Codec getACodec() {
        return this.aCodec;
    }

    public Codec getBCodec() {
        return this.bCodec;
    }
}
