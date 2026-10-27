package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.CodecEncoding;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.pack200.PopulationCodec;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BandSet {
    protected SegmentHeader header;
    protected Segment segment;

    public abstract void read(InputStream inputStream) throws Pack200Exception, IOException;

    public abstract void unpack() throws Pack200Exception, IOException;

    public void unpack(InputStream inputStream) throws Pack200Exception, IOException {
        read(inputStream);
        unpack();
    }

    public BandSet(Segment segment) {
        this.segment = segment;
        this.header = segment.getSegmentHeader();
    }

    public int[] decodeBandInt(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        int[] iArrDecodeInts;
        Codec codec;
        if (bHSDCodec.getB() == 1 || i == 0) {
            return bHSDCodec.decodeInts(i, inputStream);
        }
        int[] iArrDecodeInts2 = bHSDCodec.decodeInts(1, inputStream);
        if (iArrDecodeInts2.length == 0) {
            return iArrDecodeInts2;
        }
        int i2 = iArrDecodeInts2[0];
        if (bHSDCodec.isSigned() && i2 >= -256 && i2 <= -1) {
            Codec codec2 = CodecEncoding.getCodec((-1) - i2, this.header.getBandHeadersInputStream(), bHSDCodec);
            iArrDecodeInts = codec2.decodeInts(i, inputStream);
            codec = codec2;
        } else if (!bHSDCodec.isSigned() && i2 >= bHSDCodec.getL() && i2 <= bHSDCodec.getL() + 255) {
            Codec codec3 = CodecEncoding.getCodec(i2 - bHSDCodec.getL(), this.header.getBandHeadersInputStream(), bHSDCodec);
            iArrDecodeInts = codec3.decodeInts(i, inputStream);
            codec = codec3;
        } else {
            iArrDecodeInts = bHSDCodec.decodeInts(i - 1, inputStream, i2);
            codec = bHSDCodec;
        }
        if (codec instanceof PopulationCodec) {
            PopulationCodec populationCodec = (PopulationCodec) codec;
            int[] iArr = (int[]) populationCodec.getFavoured().clone();
            Arrays.sort(iArr);
            for (int i3 = 0; i3 < iArrDecodeInts.length; i3++) {
                Codec favouredCodec = Arrays.binarySearch(iArr, iArrDecodeInts[i3]) > -1 ? populationCodec.getFavouredCodec() : populationCodec.getUnfavouredCodec();
                if (favouredCodec instanceof BHSDCodec) {
                    BHSDCodec bHSDCodec2 = (BHSDCodec) favouredCodec;
                    if (bHSDCodec2.isDelta()) {
                        long jCardinality = bHSDCodec2.cardinality();
                        while (iArrDecodeInts[i3] > bHSDCodec2.largest()) {
                            iArrDecodeInts[i3] = (int) (((long) iArrDecodeInts[i3]) - jCardinality);
                        }
                        while (iArrDecodeInts[i3] < bHSDCodec2.smallest()) {
                            iArrDecodeInts[i3] = (int) (((long) iArrDecodeInts[i3]) + jCardinality);
                        }
                    }
                }
            }
        }
        return iArrDecodeInts;
    }

    public int[][] decodeBandInt(String str, InputStream inputStream, BHSDCodec bHSDCodec, int[] iArr) throws Pack200Exception, IOException {
        int length = iArr.length;
        int[][] iArr2 = new int[length][];
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            iArr2[i4] = new int[iArr[i4]];
            int i5 = 0;
            while (true) {
                int[] iArr3 = iArr2[i4];
                if (i5 < iArr3.length) {
                    iArr3[i5] = iArrDecodeBandInt[i3];
                    i3++;
                    i5++;
                }
            }
        }
        return iArr2;
    }

    public long[] parseFlags(String str, InputStream inputStream, int i, BHSDCodec bHSDCodec, boolean z) throws Pack200Exception, IOException {
        return parseFlags(str, inputStream, new int[]{i}, z ? bHSDCodec : null, bHSDCodec)[0];
    }

    public long[][] parseFlags(String str, InputStream inputStream, int[] iArr, BHSDCodec bHSDCodec, boolean z) throws Pack200Exception, IOException {
        return parseFlags(str, inputStream, iArr, z ? bHSDCodec : null, bHSDCodec);
    }

    public long[] parseFlags(String str, InputStream inputStream, int i, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2) throws Pack200Exception, IOException {
        return parseFlags(str, inputStream, new int[]{i}, bHSDCodec, bHSDCodec2)[0];
    }

    public long[][] parseFlags(String str, InputStream inputStream, int[] iArr, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2) throws Pack200Exception, IOException {
        int[] iArrDecodeBandInt;
        int[] iArrDecodeBandInt2;
        int length = iArr.length;
        if (length == 0) {
            return new long[][]{new long[0]};
        }
        long[][] jArr = new long[length][];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            jArr[i2] = new long[i3];
            i += i3;
        }
        if (bHSDCodec != null) {
            iArrDecodeBandInt2 = decodeBandInt(str, inputStream, bHSDCodec, i);
            iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec2, i);
        } else {
            iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec2, i);
            iArrDecodeBandInt2 = null;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = 0;
            while (true) {
                long[] jArr2 = jArr[i5];
                if (i6 < jArr2.length) {
                    if (iArrDecodeBandInt2 != null) {
                        jArr2[i6] = (((long) iArrDecodeBandInt2[i4]) << 32) | (((long) iArrDecodeBandInt[i4]) & 4294967295L);
                    } else {
                        jArr2[i6] = iArrDecodeBandInt[i4];
                    }
                    i4++;
                    i6++;
                }
            }
        }
        return jArr;
    }

    public String[] parseReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i, String[] strArr) throws Pack200Exception, IOException {
        return parseReferences(str, inputStream, bHSDCodec, new int[]{i}, strArr)[0];
    }

    public String[][] parseReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int[] iArr, String[] strArr) throws Pack200Exception, IOException {
        int length = iArr.length;
        if (length == 0) {
            return new String[][]{new String[0]};
        }
        String[][] strArr2 = new String[length][];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            strArr2[i2] = new String[i3];
            i += i3;
        }
        String[] strArr3 = new String[i];
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = iArrDecodeBandInt[i4];
            if (i5 < 0 || i5 >= strArr.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + i5 + ", array size = " + strArr.length);
            }
            strArr3[i4] = strArr[i5];
        }
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = iArr[i7];
            String[] strArr4 = new String[i8];
            strArr2[i7] = strArr4;
            System.arraycopy(strArr3, i6, strArr4, 0, i8);
            i6 += i8;
        }
        return strArr2;
    }

    public CPInteger[] parseCPIntReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        int[] cpInt = this.segment.getCpBands().getCpInt();
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPInteger[] cPIntegerArr = new CPInteger[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArrDecodeBandInt[i2];
            if (i3 < 0 || i3 >= cpInt.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + i3 + ", array size = " + cpInt.length);
            }
            cPIntegerArr[i2] = this.segment.getCpBands().cpIntegerValue(i3);
        }
        return cPIntegerArr;
    }

    public CPDouble[] parseCPDoubleReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPDouble[] cPDoubleArr = new CPDouble[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPDoubleArr[i2] = this.segment.getCpBands().cpDoubleValue(iArrDecodeBandInt[i2]);
        }
        return cPDoubleArr;
    }

    public CPFloat[] parseCPFloatReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPFloat[] cPFloatArr = new CPFloat[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPFloatArr[i2] = this.segment.getCpBands().cpFloatValue(iArrDecodeBandInt[i2]);
        }
        return cPFloatArr;
    }

    public CPLong[] parseCPLongReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        long[] cpLong = this.segment.getCpBands().getCpLong();
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPLong[] cPLongArr = new CPLong[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArrDecodeBandInt[i2];
            if (i3 < 0 || i3 >= cpLong.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + i3 + ", array size = " + cpLong.length);
            }
            cPLongArr[i2] = this.segment.getCpBands().cpLongValue(i3);
        }
        return cPLongArr;
    }

    public CPUTF8[] parseCPUTF8References(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPUTF8[] cputf8Arr = new CPUTF8[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cputf8Arr[i2] = this.segment.getCpBands().cpUTF8Value(iArrDecodeBandInt[i2]);
        }
        return cputf8Arr;
    }

    public CPUTF8[][] parseCPUTF8References(String str, InputStream inputStream, BHSDCodec bHSDCodec, int[] iArr) throws Pack200Exception, IOException {
        CPUTF8[][] cputf8Arr = new CPUTF8[iArr.length][];
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            cputf8Arr[i2] = new CPUTF8[i3];
            i += i3;
        }
        CPUTF8[] cputf8Arr2 = new CPUTF8[i];
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        for (int i4 = 0; i4 < i; i4++) {
            cputf8Arr2[i4] = this.segment.getCpBands().cpUTF8Value(iArrDecodeBandInt[i4]);
        }
        int i5 = 0;
        for (int i6 = 0; i6 < iArr.length; i6++) {
            int i7 = iArr[i6];
            CPUTF8[] cputf8Arr3 = new CPUTF8[i7];
            cputf8Arr[i6] = cputf8Arr3;
            System.arraycopy(cputf8Arr2, i5, cputf8Arr3, 0, i7);
            i5 += i7;
        }
        return cputf8Arr;
    }

    public CPString[] parseCPStringReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPString[] cPStringArr = new CPString[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPStringArr[i2] = this.segment.getCpBands().cpStringValue(iArrDecodeBandInt[i2]);
        }
        return cPStringArr;
    }

    public CPInterfaceMethodRef[] parseCPInterfaceMethodRefReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        CpBands cpBands = this.segment.getCpBands();
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPInterfaceMethodRef[] cPInterfaceMethodRefArr = new CPInterfaceMethodRef[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPInterfaceMethodRefArr[i2] = cpBands.cpIMethodValue(iArrDecodeBandInt[i2]);
        }
        return cPInterfaceMethodRefArr;
    }

    public CPMethodRef[] parseCPMethodRefReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        CpBands cpBands = this.segment.getCpBands();
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPMethodRef[] cPMethodRefArr = new CPMethodRef[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPMethodRefArr[i2] = cpBands.cpMethodValue(iArrDecodeBandInt[i2]);
        }
        return cPMethodRefArr;
    }

    public CPFieldRef[] parseCPFieldRefReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        CpBands cpBands = this.segment.getCpBands();
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPFieldRef[] cPFieldRefArr = new CPFieldRef[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPFieldRefArr[i2] = cpBands.cpFieldValue(iArrDecodeBandInt[i2]);
        }
        return cPFieldRefArr;
    }

    public CPNameAndType[] parseCPDescriptorReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        CpBands cpBands = this.segment.getCpBands();
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPNameAndType[] cPNameAndTypeArr = new CPNameAndType[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPNameAndTypeArr[i2] = cpBands.cpNameAndTypeValue(iArrDecodeBandInt[i2]);
        }
        return cPNameAndTypeArr;
    }

    public CPUTF8[] parseCPSignatureReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPUTF8[] cputf8Arr = new CPUTF8[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cputf8Arr[i2] = this.segment.getCpBands().cpSignatureValue(iArrDecodeBandInt[i2]);
        }
        return cputf8Arr;
    }

    protected CPUTF8[][] parseCPSignatureReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int[] iArr) throws Pack200Exception, IOException {
        CPUTF8[][] cputf8Arr = new CPUTF8[iArr.length][];
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            cputf8Arr[i2] = new CPUTF8[i3];
            i += i3;
        }
        CPUTF8[] cputf8Arr2 = new CPUTF8[i];
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        for (int i4 = 0; i4 < i; i4++) {
            cputf8Arr2[i4] = this.segment.getCpBands().cpSignatureValue(iArrDecodeBandInt[i4]);
        }
        int i5 = 0;
        for (int i6 = 0; i6 < iArr.length; i6++) {
            int i7 = iArr[i6];
            CPUTF8[] cputf8Arr3 = new CPUTF8[i7];
            cputf8Arr[i6] = cputf8Arr3;
            System.arraycopy(cputf8Arr2, i5, cputf8Arr3, 0, i7);
            i5 += i7;
        }
        return cputf8Arr;
    }

    public CPClass[] parseCPClassReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws Pack200Exception, IOException {
        int[] iArrDecodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPClass[] cPClassArr = new CPClass[iArrDecodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPClassArr[i2] = this.segment.getCpBands().cpClassValue(iArrDecodeBandInt[i2]);
        }
        return cPClassArr;
    }

    protected String[] getReferences(int[] iArr, String[] strArr) {
        int length = iArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            strArr2[i] = strArr[iArr[i]];
        }
        return strArr2;
    }

    protected String[][] getReferences(int[][] iArr, String[] strArr) {
        int length = iArr.length;
        String[][] strArr2 = new String[length][];
        for (int i = 0; i < length; i++) {
            strArr2[i] = new String[iArr[i].length];
            int i2 = 0;
            while (true) {
                String[] strArr3 = strArr2[i];
                if (i2 < strArr3.length) {
                    strArr3[i2] = strArr[iArr[i][i2]];
                    i2++;
                }
            }
        }
        return strArr2;
    }
}
