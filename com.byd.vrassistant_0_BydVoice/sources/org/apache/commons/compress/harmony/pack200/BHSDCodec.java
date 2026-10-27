package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class BHSDCodec extends Codec {
    private final int b;
    private long cardinality;
    private final int d;
    private final int h;
    private final int l;
    private final long largest;
    private final long[] powers;
    private final int s;
    private final long smallest;

    public BHSDCodec(int i, int i2) {
        this(i, i2, 0, 0);
    }

    public BHSDCodec(int i, int i2, int i3) {
        this(i, i2, i3, 0);
    }

    public BHSDCodec(int i, int i2, int i3, int i4) {
        if (i < 1 || i > 5) {
            throw new IllegalArgumentException("1<=b<=5");
        }
        if (i2 < 1 || i2 > 256) {
            throw new IllegalArgumentException("1<=h<=256");
        }
        if (i3 < 0 || i3 > 2) {
            throw new IllegalArgumentException("0<=s<=2");
        }
        if (i4 < 0 || i4 > 1) {
            throw new IllegalArgumentException("0<=d<=1");
        }
        if (i == 1 && i2 != 256) {
            throw new IllegalArgumentException("b=1 -> h=256");
        }
        if (i2 == 256 && i == 5) {
            throw new IllegalArgumentException("h=256 -> b!=5");
        }
        this.b = i;
        this.h = i2;
        this.s = i3;
        this.d = i4;
        this.l = 256 - i2;
        if (i2 == 1) {
            this.cardinality = (i * 255) + 1;
        } else {
            this.cardinality = (long) (((long) ((((double) r11) * (1.0d - Math.pow(r0, r2))) / ((double) (1 - i2)))) + Math.pow(i2, i));
        }
        this.smallest = calculateSmallest();
        this.largest = calculateLargest();
        this.powers = new long[i];
        for (int i5 = 0; i5 < i; i5++) {
            this.powers[i5] = (long) Math.pow(i2, i5);
        }
    }

    public long cardinality() {
        return this.cardinality;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream) throws Pack200Exception, IOException {
        if (this.d != 0) {
            throw new Pack200Exception("Delta encoding used without passing in last value; this is a coding error");
        }
        return decode(inputStream, 0L);
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream inputStream, long j) throws Pack200Exception, IOException {
        long j2;
        int i = 0;
        long j3 = 0;
        do {
            j2 = inputStream.read();
            this.lastBandLength++;
            j3 += this.powers[i] * j2;
            i++;
            if (j2 < this.l) {
                break;
            }
        } while (i < this.b);
        if (j2 == -1) {
            throw new EOFException("End of stream reached whilst decoding");
        }
        if (isSigned()) {
            int i2 = this.s;
            long j4 = (1 << i2) - 1;
            j3 = (j3 & j4) == j4 ? ~(j3 >>> i2) : j3 - (j3 >>> i2);
        }
        if (isDelta()) {
            j3 += j;
        }
        return (int) j3;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int i, InputStream inputStream) throws Pack200Exception, IOException {
        int[] iArrDecodeInts = super.decodeInts(i, inputStream);
        if (isDelta()) {
            for (int i2 = 0; i2 < iArrDecodeInts.length; i2++) {
                while (true) {
                    int i3 = iArrDecodeInts[i2];
                    if (i3 <= this.largest) {
                        break;
                    }
                    iArrDecodeInts[i2] = (int) (((long) i3) - this.cardinality);
                }
                while (true) {
                    int i4 = iArrDecodeInts[i2];
                    if (i4 < this.smallest) {
                        iArrDecodeInts[i2] = (int) (((long) i4) + this.cardinality);
                    }
                }
            }
        }
        return iArrDecodeInts;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int i, InputStream inputStream, int i2) throws Pack200Exception, IOException {
        int[] iArrDecodeInts = super.decodeInts(i, inputStream, i2);
        if (isDelta()) {
            for (int i3 = 0; i3 < iArrDecodeInts.length; i3++) {
                while (true) {
                    int i4 = iArrDecodeInts[i3];
                    if (i4 <= this.largest) {
                        break;
                    }
                    iArrDecodeInts[i3] = (int) (((long) i4) - this.cardinality);
                }
                while (true) {
                    int i5 = iArrDecodeInts[i3];
                    if (i5 < this.smallest) {
                        iArrDecodeInts[i3] = (int) (((long) i5) + this.cardinality);
                    }
                }
            }
        }
        return iArrDecodeInts;
    }

    public boolean encodes(long j) {
        return j >= this.smallest && j <= this.largest;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i, int i2) throws Pack200Exception {
        long j;
        long j2 = i;
        if (!encodes(j2)) {
            throw new Pack200Exception("The codec " + toString() + " does not encode the value " + i);
        }
        if (isDelta()) {
            j2 -= (long) i2;
        }
        long j3 = 4294967296L;
        if (isSigned()) {
            if (j2 < -2147483648L) {
                j2 += 4294967296L;
            } else if (j2 > 2147483647L) {
                j2 -= 4294967296L;
            }
            if (j2 < 0) {
                j2 = ((-j2) << this.s) - 1;
            } else {
                int i3 = this.s;
                if (i3 == 1) {
                    j2 <<= i3;
                } else {
                    j3 = (j2 - (j2 % 3)) / 3;
                }
            }
        } else if (j2 < 0) {
            long j4 = this.cardinality;
            j2 = j4 < 4294967296L ? j2 + j4 : j2 + j3;
        }
        if (j2 < 0) {
            throw new Pack200Exception("unable to encode");
        }
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < this.b; i4++) {
            if (j2 < this.l) {
                j = j2;
            } else {
                j = j2 % ((long) this.h);
                while (j < this.l) {
                    j += (long) this.h;
                }
            }
            arrayList.add(Byte.valueOf((byte) j));
            if (j < this.l) {
                break;
            }
            j2 = (j2 - j) / ((long) this.h);
        }
        int size = arrayList.size();
        byte[] bArr = new byte[size];
        for (int i5 = 0; i5 < size; i5++) {
            bArr[i5] = ((Byte) arrayList.get(i5)).byteValue();
        }
        return bArr;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int i) throws Pack200Exception {
        return encode(i, 0);
    }

    public boolean isDelta() {
        return this.d != 0;
    }

    public boolean isSigned() {
        return this.s != 0;
    }

    public long largest() {
        return this.largest;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private long calculateLargest() {
        /*
            r6 = this;
            int r0 = r6.d
            r1 = 1
            if (r0 != r1) goto L13
            org.apache.commons.compress.harmony.pack200.BHSDCodec r0 = new org.apache.commons.compress.harmony.pack200.BHSDCodec
            int r1 = r6.b
            int r2 = r6.h
            r0.<init>(r1, r2)
            long r0 = r0.largest()
            return r0
        L13:
            int r0 = r6.s
            r2 = 1
            if (r0 != 0) goto L1f
            long r0 = r6.cardinality()
        L1d:
            long r0 = r0 - r2
            goto L38
        L1f:
            if (r0 != r1) goto L29
            long r0 = r6.cardinality()
            r4 = 2
            long r0 = r0 / r4
            goto L1d
        L29:
            r1 = 2
            if (r0 != r1) goto L4b
            r0 = 3
            long r4 = r6.cardinality()
            long r4 = r4 * r0
            r0 = 4
            long r4 = r4 / r0
            long r0 = r4 - r2
        L38:
            int r4 = r6.s
            if (r4 != 0) goto L42
            r4 = 4294967294(0xfffffffe, double:2.12199579E-314)
            goto L45
        L42:
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
        L45:
            long r4 = r4 - r2
            long r0 = java.lang.Math.min(r4, r0)
            return r0
        L4b:
            java.lang.Error r0 = new java.lang.Error
            java.lang.String r1 = "Unknown s value"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BHSDCodec.calculateLargest():long");
    }

    public long smallest() {
        return this.smallest;
    }

    private long calculateSmallest() {
        if (this.d == 1 || !isSigned()) {
            return this.cardinality >= 4294967296L ? -2147483648L : 0L;
        }
        return Math.max(-2147483648L, (-cardinality()) / ((long) (1 << this.s)));
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(11);
        stringBuffer.append('(');
        stringBuffer.append(this.b);
        stringBuffer.append(',');
        stringBuffer.append(this.h);
        if (this.s != 0 || this.d != 0) {
            stringBuffer.append(',');
            stringBuffer.append(this.s);
        }
        if (this.d != 0) {
            stringBuffer.append(',');
            stringBuffer.append(this.d);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public int getB() {
        return this.b;
    }

    public int getH() {
        return this.h;
    }

    public int getS() {
        return this.s;
    }

    public int getL() {
        return this.l;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BHSDCodec)) {
            return false;
        }
        BHSDCodec bHSDCodec = (BHSDCodec) obj;
        return bHSDCodec.b == this.b && bHSDCodec.h == this.h && bHSDCodec.s == this.s && bHSDCodec.d == this.d;
    }

    public int hashCode() {
        return (((((this.b * 37) + this.h) * 37) + this.s) * 37) + this.d;
    }
}
