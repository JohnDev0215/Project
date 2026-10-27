package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BandSet {
    private static final int[] effortThresholds = {0, 0, 1000, 500, 100, 100, 100, 100, 100, 0};
    private long[] canonicalLargest;
    private long[] canonicalSmallest;
    final int effort;
    protected final SegmentHeader segmentHeader;

    public abstract void pack(OutputStream outputStream) throws Pack200Exception, IOException;

    public BandSet(int i, SegmentHeader segmentHeader) {
        this.effort = i;
        this.segmentHeader = segmentHeader;
    }

    public byte[] encodeScalar(int[] iArr, BHSDCodec bHSDCodec) throws Pack200Exception {
        return bHSDCodec.encode(iArr);
    }

    public byte[] encodeScalar(int i, BHSDCodec bHSDCodec) throws Pack200Exception {
        return bHSDCodec.encode(i);
    }

    public byte[] encodeBandInt(String str, int[] iArr, BHSDCodec bHSDCodec) throws Pack200Exception {
        byte[] bArrEncode;
        int i = this.effort;
        if (i <= 1 || iArr.length < effortThresholds[i]) {
            bArrEncode = null;
        } else {
            BandAnalysisResults bandAnalysisResultsAnalyseBand = analyseBand(str, iArr, bHSDCodec);
            Codec codec = bandAnalysisResultsAnalyseBand.betterCodec;
            bArrEncode = bandAnalysisResultsAnalyseBand.encodedBand;
            if (codec != null) {
                if (codec instanceof BHSDCodec) {
                    int[] specifier = CodecEncoding.getSpecifier(codec, bHSDCodec);
                    int i2 = specifier[0];
                    if (specifier.length > 1) {
                        for (int i3 = 1; i3 < specifier.length; i3++) {
                            this.segmentHeader.appendBandCodingSpecifier(specifier[i3]);
                        }
                    }
                    byte[] bArrEncode2 = bHSDCodec.encode(new int[]{bHSDCodec.isSigned() ? (-1) - i2 : i2 + bHSDCodec.getL()});
                    byte[] bArr = new byte[bArrEncode2.length + bArrEncode.length];
                    System.arraycopy(bArrEncode2, 0, bArr, 0, bArrEncode2.length);
                    System.arraycopy(bArrEncode, 0, bArr, bArrEncode2.length, bArrEncode.length);
                    return bArr;
                }
                if (!(codec instanceof PopulationCodec)) {
                    boolean z = codec instanceof RunCodec;
                } else {
                    for (int i4 : bandAnalysisResultsAnalyseBand.extraMetadata) {
                        this.segmentHeader.appendBandCodingSpecifier(i4);
                    }
                    return bArrEncode;
                }
            }
        }
        if (iArr.length <= 0) {
            return new byte[0];
        }
        if (bArrEncode == null) {
            bArrEncode = bHSDCodec.encode(iArr);
        }
        int i5 = iArr[0];
        if (bHSDCodec.getB() != 1) {
            if (bHSDCodec.isSigned() && i5 >= -256 && i5 <= -1) {
                byte[] bArrEncode3 = bHSDCodec.encode(new int[]{(-1) - CodecEncoding.getSpecifierForDefaultCodec(bHSDCodec)});
                byte[] bArr2 = new byte[bArrEncode3.length + bArrEncode.length];
                System.arraycopy(bArrEncode3, 0, bArr2, 0, bArrEncode3.length);
                System.arraycopy(bArrEncode, 0, bArr2, bArrEncode3.length, bArrEncode.length);
                return bArr2;
            }
            if (!bHSDCodec.isSigned() && i5 >= bHSDCodec.getL() && i5 <= bHSDCodec.getL() + 255) {
                byte[] bArrEncode4 = bHSDCodec.encode(new int[]{CodecEncoding.getSpecifierForDefaultCodec(bHSDCodec) + bHSDCodec.getL()});
                byte[] bArr3 = new byte[bArrEncode4.length + bArrEncode.length];
                System.arraycopy(bArrEncode4, 0, bArr3, 0, bArrEncode4.length);
                System.arraycopy(bArrEncode, 0, bArr3, bArrEncode4.length, bArrEncode.length);
                return bArr3;
            }
        }
        return bArrEncode;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.apache.commons.compress.harmony.pack200.BandSet.BandAnalysisResults analyseBand(java.lang.String r13, int[] r14, org.apache.commons.compress.harmony.pack200.BHSDCodec r15) throws org.apache.commons.compress.harmony.pack200.Pack200Exception {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BandSet.analyseBand(java.lang.String, int[], org.apache.commons.compress.harmony.pack200.BHSDCodec):org.apache.commons.compress.harmony.pack200.BandSet$BandAnalysisResults");
    }

    private boolean timeToStop(BandAnalysisResults bandAnalysisResults) {
        return this.effort > 6 ? bandAnalysisResults.numCodecsTried >= this.effort * 2 : bandAnalysisResults.numCodecsTried >= this.effort;
    }

    private void tryCodecs(String str, int[] iArr, BHSDCodec bHSDCodec, BandData bandData, BandAnalysisResults bandAnalysisResults, byte[] bArr, BHSDCodec[] bHSDCodecArr) throws Pack200Exception {
        for (BHSDCodec bHSDCodec2 : bHSDCodecArr) {
            if (bHSDCodec2.equals(bHSDCodec)) {
                return;
            }
            if (bHSDCodec2.isDelta()) {
                if (bHSDCodec2.largest() >= bandData.largestDelta && bHSDCodec2.smallest() <= bandData.smallestDelta && bHSDCodec2.largest() >= bandData.largest && bHSDCodec2.smallest() <= bandData.smallest) {
                    byte[] bArrEncode = bHSDCodec2.encode(iArr);
                    BandAnalysisResults.access$408(bandAnalysisResults);
                    int length = (bArr.length - bArrEncode.length) - bHSDCodec.encode(CodecEncoding.getSpecifier(bHSDCodec2, null)).length;
                    if (length > bandAnalysisResults.saved) {
                        bandAnalysisResults.betterCodec = bHSDCodec2;
                        bandAnalysisResults.encodedBand = bArrEncode;
                        bandAnalysisResults.saved = length;
                    }
                }
            } else if (bHSDCodec2.largest() >= bandData.largest && bHSDCodec2.smallest() <= bandData.smallest) {
                byte[] bArrEncode2 = bHSDCodec2.encode(iArr);
                BandAnalysisResults.access$408(bandAnalysisResults);
                int length2 = (bArr.length - bArrEncode2.length) - bHSDCodec.encode(CodecEncoding.getSpecifier(bHSDCodec2, null)).length;
                if (length2 > bandAnalysisResults.saved) {
                    bandAnalysisResults.betterCodec = bHSDCodec2;
                    bandAnalysisResults.encodedBand = bArrEncode2;
                    bandAnalysisResults.saved = length2;
                }
            }
            if (timeToStop(bandAnalysisResults)) {
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void encodeWithPopulationCodec(java.lang.String r18, int[] r19, org.apache.commons.compress.harmony.pack200.BHSDCodec r20, org.apache.commons.compress.harmony.pack200.BandSet.BandData r21, org.apache.commons.compress.harmony.pack200.BandSet.BandAnalysisResults r22) throws org.apache.commons.compress.harmony.pack200.Pack200Exception {
        /*
            Method dump skipped, instruction units count: 580
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BandSet.encodeWithPopulationCodec(java.lang.String, int[], org.apache.commons.compress.harmony.pack200.BHSDCodec, org.apache.commons.compress.harmony.pack200.BandSet$BandData, org.apache.commons.compress.harmony.pack200.BandSet$BandAnalysisResults):void");
    }

    protected byte[] encodeFlags(String str, long[] jArr, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2, boolean z) throws Pack200Exception {
        if (!z) {
            int[] iArr = new int[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                iArr[i] = (int) jArr[i];
            }
            return encodeBandInt(str, iArr, bHSDCodec);
        }
        int[] iArr2 = new int[jArr.length];
        int[] iArr3 = new int[jArr.length];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            long j = jArr[i2];
            iArr2[i2] = (int) (j >> 32);
            iArr3[i2] = (int) j;
        }
        byte[] bArrEncodeBandInt = encodeBandInt(str, iArr2, bHSDCodec2);
        byte[] bArrEncodeBandInt2 = encodeBandInt(str, iArr3, bHSDCodec);
        byte[] bArr = new byte[bArrEncodeBandInt.length + bArrEncodeBandInt2.length];
        System.arraycopy(bArrEncodeBandInt, 0, bArr, 0, bArrEncodeBandInt.length);
        System.arraycopy(bArrEncodeBandInt2, 0, bArr, bArrEncodeBandInt.length + 1, bArrEncodeBandInt2.length);
        return bArr;
    }

    protected int[] integerListToArray(List list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Integer) list.get(i)).intValue();
        }
        return iArr;
    }

    protected long[] longListToArray(List list) {
        int size = list.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = ((Long) list.get(i)).longValue();
        }
        return jArr;
    }

    protected int[] cpEntryListToArray(List list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            int index = ((ConstantPoolEntry) list.get(i)).getIndex();
            iArr[i] = index;
            if (index < 0) {
                throw new RuntimeException("Index should be > 0");
            }
        }
        return iArr;
    }

    protected int[] cpEntryOrNullListToArray(List list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            ConstantPoolEntry constantPoolEntry = (ConstantPoolEntry) list.get(i);
            iArr[i] = constantPoolEntry == null ? 0 : constantPoolEntry.getIndex() + 1;
            if (constantPoolEntry != null && constantPoolEntry.getIndex() < 0) {
                throw new RuntimeException("Index should be > 0");
            }
        }
        return iArr;
    }

    protected byte[] encodeFlags(String str, long[][] jArr, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2, boolean z) throws Pack200Exception {
        return encodeFlags(str, flatten(jArr), bHSDCodec, bHSDCodec2, z);
    }

    private long[] flatten(long[][] jArr) {
        int length = 0;
        for (long[] jArr2 : jArr) {
            length += jArr2.length;
        }
        long[] jArr3 = new long[length];
        int i = 0;
        for (long[] jArr4 : jArr) {
            int i2 = 0;
            while (true) {
                if (i2 < jArr4.length) {
                    jArr3[i] = jArr4[i2];
                    i++;
                    i2++;
                }
            }
        }
        return jArr3;
    }

    public class BandData {
        private final int[] band;
        private Map distinctValues;
        private int largest;
        private int largestDelta;
        private int smallest;
        private int smallestDelta;
        private int deltaIsAscending = 0;
        private int smallDeltaCount = 0;
        private double averageAbsoluteDelta = 0.0d;
        private double averageAbsoluteValue = 0.0d;

        public BandData(int[] iArr) {
            Integer numValueOf;
            this.smallest = Integer.MAX_VALUE;
            this.largest = Integer.MIN_VALUE;
            this.band = iArr;
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                if (i2 < this.smallest) {
                    this.smallest = i2;
                }
                if (i2 > this.largest) {
                    this.largest = i2;
                }
                if (i != 0) {
                    int i3 = i2 - iArr[i - 1];
                    if (i3 < this.smallestDelta) {
                        this.smallestDelta = i3;
                    }
                    if (i3 > this.largestDelta) {
                        this.largestDelta = i3;
                    }
                    if (i3 >= 0) {
                        this.deltaIsAscending++;
                    }
                    this.averageAbsoluteDelta += ((double) Math.abs(i3)) / ((double) (iArr.length - 1));
                    if (Math.abs(i3) < 256) {
                        this.smallDeltaCount++;
                    }
                } else {
                    int i4 = iArr[0];
                    this.smallestDelta = i4;
                    this.largestDelta = i4;
                }
                this.averageAbsoluteValue += ((double) Math.abs(iArr[i])) / ((double) iArr.length);
                if (BandSet.this.effort > 3) {
                    if (this.distinctValues == null) {
                        this.distinctValues = new HashMap();
                    }
                    Integer numValueOf2 = Integer.valueOf(iArr[i]);
                    Integer num = (Integer) this.distinctValues.get(numValueOf2);
                    if (num == null) {
                        numValueOf = 1;
                    } else {
                        numValueOf = Integer.valueOf(num.intValue() + 1);
                    }
                    this.distinctValues.put(numValueOf2, numValueOf);
                }
            }
        }

        public boolean mainlySmallDeltas() {
            return ((float) this.smallDeltaCount) / ((float) this.band.length) > 0.7f;
        }

        public boolean wellCorrelated() {
            return this.averageAbsoluteDelta * 3.1d < this.averageAbsoluteValue;
        }

        public boolean mainlyPositiveDeltas() {
            return ((float) this.deltaIsAscending) / ((float) this.band.length) > 0.95f;
        }

        public boolean anyNegatives() {
            return this.smallest < 0;
        }

        public int numDistinctValues() {
            Map map = this.distinctValues;
            if (map == null) {
                return this.band.length;
            }
            return map.size();
        }
    }

    public class BandAnalysisResults {
        private Codec betterCodec;
        private byte[] encodedBand;
        private int[] extraMetadata;
        private int numCodecsTried = 0;
        private int saved = 0;

        public BandAnalysisResults() {
        }

        static /* synthetic */ int access$408(BandAnalysisResults bandAnalysisResults) {
            int i = bandAnalysisResults.numCodecsTried;
            bandAnalysisResults.numCodecsTried = i + 1;
            return i;
        }
    }
}
