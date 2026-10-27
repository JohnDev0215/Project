package android.util.proto;

import android.provider.Telephony;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes3.dex */
public final class ProtoOutputStream extends ProtoStream {
    public static final String TAG = "ProtoOutputStream";
    private EncodedBuffer mBuffer;
    private boolean mCompacted;
    private int mCopyBegin;
    private int mDepth;
    private long mExpectedObjectToken;
    private int mNextObjectId;
    private OutputStream mStream;

    public static long makeFieldId(int i, long j) {
        return j | (((long) i) & 4294967295L);
    }

    public ProtoOutputStream() {
        this(0);
    }

    public ProtoOutputStream(int i) {
        this.mNextObjectId = -1;
        this.mBuffer = new EncodedBuffer(i);
    }

    public ProtoOutputStream(OutputStream outputStream) {
        this();
        this.mStream = outputStream;
    }

    public ProtoOutputStream(FileDescriptor fileDescriptor) {
        this(new FileOutputStream(fileDescriptor));
    }

    public int getRawSize() {
        if (this.mCompacted) {
            return getBytes().length;
        }
        return this.mBuffer.getSize();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(long r7, double r9) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.proto.ProtoOutputStream.write(long, double):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(long r6, float r8) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.proto.ProtoOutputStream.write(long, float):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(long r5, int r7) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.proto.ProtoOutputStream.write(long, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(long r7, long r9) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.proto.ProtoOutputStream.write(long, long):void");
    }

    public void write(long j, boolean z) {
        assertNotCompacted();
        int i = (int) j;
        int i2 = (int) ((17587891077120L & j) >> 32);
        if (i2 == 264) {
            writeBoolImpl(i, z);
        } else {
            if (i2 == 520 || i2 == 1288) {
                writeRepeatedBoolImpl(i, z);
                return;
            }
            throw new IllegalArgumentException("Attempt to call write(long, boolean) with " + getFieldIdString(j));
        }
    }

    public void write(long j, String str) {
        assertNotCompacted();
        int i = (int) j;
        int i2 = (int) ((17587891077120L & j) >> 32);
        if (i2 == 265) {
            writeStringImpl(i, str);
        } else {
            if (i2 == 521 || i2 == 1289) {
                writeRepeatedStringImpl(i, str);
                return;
            }
            throw new IllegalArgumentException("Attempt to call write(long, String) with " + getFieldIdString(j));
        }
    }

    public void write(long j, byte[] bArr) {
        assertNotCompacted();
        int i = (int) j;
        int i2 = (int) ((17587891077120L & j) >> 32);
        if (i2 == 267) {
            writeObjectImpl(i, bArr);
            return;
        }
        if (i2 == 268) {
            writeBytesImpl(i, bArr);
            return;
        }
        if (i2 != 523) {
            if (i2 != 524) {
                if (i2 != 1291) {
                    if (i2 != 1292) {
                        throw new IllegalArgumentException("Attempt to call write(long, byte[]) with " + getFieldIdString(j));
                    }
                }
            }
            writeRepeatedBytesImpl(i, bArr);
            return;
        }
        writeRepeatedObjectImpl(i, bArr);
    }

    public long start(long j) {
        assertNotCompacted();
        int i = (int) j;
        if ((ProtoStream.FIELD_TYPE_MASK & j) == ProtoStream.FIELD_TYPE_MESSAGE) {
            long j2 = ProtoStream.FIELD_COUNT_MASK & j;
            if (j2 == 1099511627776L) {
                return startObjectImpl(i, false);
            }
            if (j2 == ProtoStream.FIELD_COUNT_REPEATED || j2 == ProtoStream.FIELD_COUNT_PACKED) {
                return startObjectImpl(i, true);
            }
        }
        throw new IllegalArgumentException("Attempt to call start(long) with " + getFieldIdString(j));
    }

    public void end(long j) {
        endObjectImpl(j, getRepeatedFromToken(j));
    }

    @Deprecated
    public void writeDouble(long j, double d) {
        assertNotCompacted();
        writeDoubleImpl(checkFieldId(j, 1103806595072L), d);
    }

    private void writeDoubleImpl(int i, double d) {
        if (d != 0.0d) {
            writeTag(i, 1);
            this.mBuffer.writeRawFixed64(Double.doubleToLongBits(d));
        }
    }

    @Deprecated
    public void writeRepeatedDouble(long j, double d) {
        assertNotCompacted();
        writeRepeatedDoubleImpl(checkFieldId(j, 2203318222848L), d);
    }

    private void writeRepeatedDoubleImpl(int i, double d) {
        writeTag(i, 1);
        this.mBuffer.writeRawFixed64(Double.doubleToLongBits(d));
    }

    @Deprecated
    public void writePackedDouble(long j, double[] dArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5501853106176L);
        int length = dArr != null ? dArr.length : 0;
        if (length > 0) {
            writeKnownLengthHeader(iCheckFieldId, length * 8);
            for (int i = 0; i < length; i++) {
                this.mBuffer.writeRawFixed64(Double.doubleToLongBits(dArr[i]));
            }
        }
    }

    @Deprecated
    public void writeFloat(long j, float f) {
        assertNotCompacted();
        writeFloatImpl(checkFieldId(j, 1108101562368L), f);
    }

    private void writeFloatImpl(int i, float f) {
        if (f != 0.0f) {
            writeTag(i, 5);
            this.mBuffer.writeRawFixed32(Float.floatToIntBits(f));
        }
    }

    @Deprecated
    public void writeRepeatedFloat(long j, float f) {
        assertNotCompacted();
        writeRepeatedFloatImpl(checkFieldId(j, 2207613190144L), f);
    }

    private void writeRepeatedFloatImpl(int i, float f) {
        writeTag(i, 5);
        this.mBuffer.writeRawFixed32(Float.floatToIntBits(f));
    }

    @Deprecated
    public void writePackedFloat(long j, float[] fArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5506148073472L);
        int length = fArr != null ? fArr.length : 0;
        if (length > 0) {
            writeKnownLengthHeader(iCheckFieldId, length * 4);
            for (int i = 0; i < length; i++) {
                this.mBuffer.writeRawFixed32(Float.floatToIntBits(fArr[i]));
            }
        }
    }

    private void writeUnsignedVarintFromSignedInt(int i) {
        if (i >= 0) {
            this.mBuffer.writeRawVarint32(i);
        } else {
            this.mBuffer.writeRawVarint64(i);
        }
    }

    @Deprecated
    public void writeInt32(long j, int i) {
        assertNotCompacted();
        writeInt32Impl(checkFieldId(j, 1120986464256L), i);
    }

    private void writeInt32Impl(int i, int i2) {
        if (i2 != 0) {
            writeTag(i, 0);
            writeUnsignedVarintFromSignedInt(i2);
        }
    }

    @Deprecated
    public void writeRepeatedInt32(long j, int i) {
        assertNotCompacted();
        writeRepeatedInt32Impl(checkFieldId(j, 2220498092032L), i);
    }

    private void writeRepeatedInt32Impl(int i, int i2) {
        writeTag(i, 0);
        writeUnsignedVarintFromSignedInt(i2);
    }

    @Deprecated
    public void writePackedInt32(long j, int[] iArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5519032975360L);
        int length = iArr != null ? iArr.length : 0;
        if (length > 0) {
            int rawVarint32Size = 0;
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                rawVarint32Size += i2 >= 0 ? EncodedBuffer.getRawVarint32Size(i2) : 10;
            }
            writeKnownLengthHeader(iCheckFieldId, rawVarint32Size);
            for (int i3 = 0; i3 < length; i3++) {
                writeUnsignedVarintFromSignedInt(iArr[i3]);
            }
        }
    }

    @Deprecated
    public void writeInt64(long j, long j2) {
        assertNotCompacted();
        writeInt64Impl(checkFieldId(j, 1112396529664L), j2);
    }

    private void writeInt64Impl(int i, long j) {
        if (j != 0) {
            writeTag(i, 0);
            this.mBuffer.writeRawVarint64(j);
        }
    }

    @Deprecated
    public void writeRepeatedInt64(long j, long j2) {
        assertNotCompacted();
        writeRepeatedInt64Impl(checkFieldId(j, 2211908157440L), j2);
    }

    private void writeRepeatedInt64Impl(int i, long j) {
        writeTag(i, 0);
        this.mBuffer.writeRawVarint64(j);
    }

    @Deprecated
    public void writePackedInt64(long j, long[] jArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5510443040768L);
        int length = jArr != null ? jArr.length : 0;
        if (length > 0) {
            int rawVarint64Size = 0;
            for (int i = 0; i < length; i++) {
                rawVarint64Size += EncodedBuffer.getRawVarint64Size(jArr[i]);
            }
            writeKnownLengthHeader(iCheckFieldId, rawVarint64Size);
            for (int i2 = 0; i2 < length; i2++) {
                this.mBuffer.writeRawVarint64(jArr[i2]);
            }
        }
    }

    @Deprecated
    public void writeUInt32(long j, int i) {
        assertNotCompacted();
        writeUInt32Impl(checkFieldId(j, 1155346202624L), i);
    }

    private void writeUInt32Impl(int i, int i2) {
        if (i2 != 0) {
            writeTag(i, 0);
            this.mBuffer.writeRawVarint32(i2);
        }
    }

    @Deprecated
    public void writeRepeatedUInt32(long j, int i) {
        assertNotCompacted();
        writeRepeatedUInt32Impl(checkFieldId(j, 2254857830400L), i);
    }

    private void writeRepeatedUInt32Impl(int i, int i2) {
        writeTag(i, 0);
        this.mBuffer.writeRawVarint32(i2);
    }

    @Deprecated
    public void writePackedUInt32(long j, int[] iArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5553392713728L);
        int length = iArr != null ? iArr.length : 0;
        if (length > 0) {
            int rawVarint32Size = 0;
            for (int i = 0; i < length; i++) {
                rawVarint32Size += EncodedBuffer.getRawVarint32Size(iArr[i]);
            }
            writeKnownLengthHeader(iCheckFieldId, rawVarint32Size);
            for (int i2 = 0; i2 < length; i2++) {
                this.mBuffer.writeRawVarint32(iArr[i2]);
            }
        }
    }

    @Deprecated
    public void writeUInt64(long j, long j2) {
        assertNotCompacted();
        writeUInt64Impl(checkFieldId(j, 1116691496960L), j2);
    }

    private void writeUInt64Impl(int i, long j) {
        if (j != 0) {
            writeTag(i, 0);
            this.mBuffer.writeRawVarint64(j);
        }
    }

    @Deprecated
    public void writeRepeatedUInt64(long j, long j2) {
        assertNotCompacted();
        writeRepeatedUInt64Impl(checkFieldId(j, 2216203124736L), j2);
    }

    private void writeRepeatedUInt64Impl(int i, long j) {
        writeTag(i, 0);
        this.mBuffer.writeRawVarint64(j);
    }

    @Deprecated
    public void writePackedUInt64(long j, long[] jArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5514738008064L);
        int length = jArr != null ? jArr.length : 0;
        if (length > 0) {
            int rawVarint64Size = 0;
            for (int i = 0; i < length; i++) {
                rawVarint64Size += EncodedBuffer.getRawVarint64Size(jArr[i]);
            }
            writeKnownLengthHeader(iCheckFieldId, rawVarint64Size);
            for (int i2 = 0; i2 < length; i2++) {
                this.mBuffer.writeRawVarint64(jArr[i2]);
            }
        }
    }

    @Deprecated
    public void writeSInt32(long j, int i) {
        assertNotCompacted();
        writeSInt32Impl(checkFieldId(j, 1172526071808L), i);
    }

    private void writeSInt32Impl(int i, int i2) {
        if (i2 != 0) {
            writeTag(i, 0);
            this.mBuffer.writeRawZigZag32(i2);
        }
    }

    @Deprecated
    public void writeRepeatedSInt32(long j, int i) {
        assertNotCompacted();
        writeRepeatedSInt32Impl(checkFieldId(j, 2272037699584L), i);
    }

    private void writeRepeatedSInt32Impl(int i, int i2) {
        writeTag(i, 0);
        this.mBuffer.writeRawZigZag32(i2);
    }

    @Deprecated
    public void writePackedSInt32(long j, int[] iArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5570572582912L);
        int length = iArr != null ? iArr.length : 0;
        if (length > 0) {
            int rawZigZag32Size = 0;
            for (int i = 0; i < length; i++) {
                rawZigZag32Size += EncodedBuffer.getRawZigZag32Size(iArr[i]);
            }
            writeKnownLengthHeader(iCheckFieldId, rawZigZag32Size);
            for (int i2 = 0; i2 < length; i2++) {
                this.mBuffer.writeRawZigZag32(iArr[i2]);
            }
        }
    }

    @Deprecated
    public void writeSInt64(long j, long j2) {
        assertNotCompacted();
        writeSInt64Impl(checkFieldId(j, 1176821039104L), j2);
    }

    private void writeSInt64Impl(int i, long j) {
        if (j != 0) {
            writeTag(i, 0);
            this.mBuffer.writeRawZigZag64(j);
        }
    }

    @Deprecated
    public void writeRepeatedSInt64(long j, long j2) {
        assertNotCompacted();
        writeRepeatedSInt64Impl(checkFieldId(j, 2276332666880L), j2);
    }

    private void writeRepeatedSInt64Impl(int i, long j) {
        writeTag(i, 0);
        this.mBuffer.writeRawZigZag64(j);
    }

    @Deprecated
    public void writePackedSInt64(long j, long[] jArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5574867550208L);
        int length = jArr != null ? jArr.length : 0;
        if (length > 0) {
            int rawZigZag64Size = 0;
            for (int i = 0; i < length; i++) {
                rawZigZag64Size += EncodedBuffer.getRawZigZag64Size(jArr[i]);
            }
            writeKnownLengthHeader(iCheckFieldId, rawZigZag64Size);
            for (int i2 = 0; i2 < length; i2++) {
                this.mBuffer.writeRawZigZag64(jArr[i2]);
            }
        }
    }

    @Deprecated
    public void writeFixed32(long j, int i) {
        assertNotCompacted();
        writeFixed32Impl(checkFieldId(j, 1129576398848L), i);
    }

    private void writeFixed32Impl(int i, int i2) {
        if (i2 != 0) {
            writeTag(i, 5);
            this.mBuffer.writeRawFixed32(i2);
        }
    }

    @Deprecated
    public void writeRepeatedFixed32(long j, int i) {
        assertNotCompacted();
        writeRepeatedFixed32Impl(checkFieldId(j, 2229088026624L), i);
    }

    private void writeRepeatedFixed32Impl(int i, int i2) {
        writeTag(i, 5);
        this.mBuffer.writeRawFixed32(i2);
    }

    @Deprecated
    public void writePackedFixed32(long j, int[] iArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5527622909952L);
        int length = iArr != null ? iArr.length : 0;
        if (length > 0) {
            writeKnownLengthHeader(iCheckFieldId, length * 4);
            for (int i = 0; i < length; i++) {
                this.mBuffer.writeRawFixed32(iArr[i]);
            }
        }
    }

    @Deprecated
    public void writeFixed64(long j, long j2) {
        assertNotCompacted();
        writeFixed64Impl(checkFieldId(j, 1125281431552L), j2);
    }

    private void writeFixed64Impl(int i, long j) {
        if (j != 0) {
            writeTag(i, 1);
            this.mBuffer.writeRawFixed64(j);
        }
    }

    @Deprecated
    public void writeRepeatedFixed64(long j, long j2) {
        assertNotCompacted();
        writeRepeatedFixed64Impl(checkFieldId(j, 2224793059328L), j2);
    }

    private void writeRepeatedFixed64Impl(int i, long j) {
        writeTag(i, 1);
        this.mBuffer.writeRawFixed64(j);
    }

    @Deprecated
    public void writePackedFixed64(long j, long[] jArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5523327942656L);
        int length = jArr != null ? jArr.length : 0;
        if (length > 0) {
            writeKnownLengthHeader(iCheckFieldId, length * 8);
            for (int i = 0; i < length; i++) {
                this.mBuffer.writeRawFixed64(jArr[i]);
            }
        }
    }

    @Deprecated
    public void writeSFixed32(long j, int i) {
        assertNotCompacted();
        writeSFixed32Impl(checkFieldId(j, 1163936137216L), i);
    }

    private void writeSFixed32Impl(int i, int i2) {
        if (i2 != 0) {
            writeTag(i, 5);
            this.mBuffer.writeRawFixed32(i2);
        }
    }

    @Deprecated
    public void writeRepeatedSFixed32(long j, int i) {
        assertNotCompacted();
        writeRepeatedSFixed32Impl(checkFieldId(j, 2263447764992L), i);
    }

    private void writeRepeatedSFixed32Impl(int i, int i2) {
        writeTag(i, 5);
        this.mBuffer.writeRawFixed32(i2);
    }

    @Deprecated
    public void writePackedSFixed32(long j, int[] iArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5561982648320L);
        int length = iArr != null ? iArr.length : 0;
        if (length > 0) {
            writeKnownLengthHeader(iCheckFieldId, length * 4);
            for (int i = 0; i < length; i++) {
                this.mBuffer.writeRawFixed32(iArr[i]);
            }
        }
    }

    @Deprecated
    public void writeSFixed64(long j, long j2) {
        assertNotCompacted();
        writeSFixed64Impl(checkFieldId(j, 1168231104512L), j2);
    }

    private void writeSFixed64Impl(int i, long j) {
        if (j != 0) {
            writeTag(i, 1);
            this.mBuffer.writeRawFixed64(j);
        }
    }

    @Deprecated
    public void writeRepeatedSFixed64(long j, long j2) {
        assertNotCompacted();
        writeRepeatedSFixed64Impl(checkFieldId(j, 2267742732288L), j2);
    }

    private void writeRepeatedSFixed64Impl(int i, long j) {
        writeTag(i, 1);
        this.mBuffer.writeRawFixed64(j);
    }

    @Deprecated
    public void writePackedSFixed64(long j, long[] jArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5566277615616L);
        int length = jArr != null ? jArr.length : 0;
        if (length > 0) {
            writeKnownLengthHeader(iCheckFieldId, length * 8);
            for (int i = 0; i < length; i++) {
                this.mBuffer.writeRawFixed64(jArr[i]);
            }
        }
    }

    @Deprecated
    public void writeBool(long j, boolean z) {
        assertNotCompacted();
        writeBoolImpl(checkFieldId(j, 1133871366144L), z);
    }

    private void writeBoolImpl(int i, boolean z) {
        if (z) {
            writeTag(i, 0);
            this.mBuffer.writeRawByte((byte) 1);
        }
    }

    @Deprecated
    public void writeRepeatedBool(long j, boolean z) {
        assertNotCompacted();
        writeRepeatedBoolImpl(checkFieldId(j, 2233382993920L), z);
    }

    private void writeRepeatedBoolImpl(int i, boolean z) {
        writeTag(i, 0);
        this.mBuffer.writeRawByte(z ? (byte) 1 : (byte) 0);
    }

    @Deprecated
    public void writePackedBool(long j, boolean[] zArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5531917877248L);
        int length = zArr != null ? zArr.length : 0;
        if (length > 0) {
            writeKnownLengthHeader(iCheckFieldId, length);
            for (int i = 0; i < length; i++) {
                this.mBuffer.writeRawByte(zArr[i] ? (byte) 1 : (byte) 0);
            }
        }
    }

    @Deprecated
    public void writeString(long j, String str) {
        assertNotCompacted();
        writeStringImpl(checkFieldId(j, 1138166333440L), str);
    }

    private void writeStringImpl(int i, String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        writeUtf8String(i, str);
    }

    @Deprecated
    public void writeRepeatedString(long j, String str) {
        assertNotCompacted();
        writeRepeatedStringImpl(checkFieldId(j, 2237677961216L), str);
    }

    private void writeRepeatedStringImpl(int i, String str) {
        if (str == null || str.length() == 0) {
            writeKnownLengthHeader(i, 0);
        } else {
            writeUtf8String(i, str);
        }
    }

    private void writeUtf8String(int i, String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            writeKnownLengthHeader(i, bytes.length);
            this.mBuffer.writeRawBuffer(bytes);
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("not possible");
        }
    }

    @Deprecated
    public void writeBytes(long j, byte[] bArr) {
        assertNotCompacted();
        writeBytesImpl(checkFieldId(j, 1151051235328L), bArr);
    }

    private void writeBytesImpl(int i, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        writeKnownLengthHeader(i, bArr.length);
        this.mBuffer.writeRawBuffer(bArr);
    }

    @Deprecated
    public void writeRepeatedBytes(long j, byte[] bArr) {
        assertNotCompacted();
        writeRepeatedBytesImpl(checkFieldId(j, 2250562863104L), bArr);
    }

    private void writeRepeatedBytesImpl(int i, byte[] bArr) {
        writeKnownLengthHeader(i, bArr == null ? 0 : bArr.length);
        this.mBuffer.writeRawBuffer(bArr);
    }

    @Deprecated
    public void writeEnum(long j, int i) {
        assertNotCompacted();
        writeEnumImpl(checkFieldId(j, 1159641169920L), i);
    }

    private void writeEnumImpl(int i, int i2) {
        if (i2 != 0) {
            writeTag(i, 0);
            writeUnsignedVarintFromSignedInt(i2);
        }
    }

    @Deprecated
    public void writeRepeatedEnum(long j, int i) {
        assertNotCompacted();
        writeRepeatedEnumImpl(checkFieldId(j, 2259152797696L), i);
    }

    private void writeRepeatedEnumImpl(int i, int i2) {
        writeTag(i, 0);
        writeUnsignedVarintFromSignedInt(i2);
    }

    @Deprecated
    public void writePackedEnum(long j, int[] iArr) {
        assertNotCompacted();
        int iCheckFieldId = checkFieldId(j, 5557687681024L);
        int length = iArr != null ? iArr.length : 0;
        if (length > 0) {
            int rawVarint32Size = 0;
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                rawVarint32Size += i2 >= 0 ? EncodedBuffer.getRawVarint32Size(i2) : 10;
            }
            writeKnownLengthHeader(iCheckFieldId, rawVarint32Size);
            for (int i3 = 0; i3 < length; i3++) {
                writeUnsignedVarintFromSignedInt(iArr[i3]);
            }
        }
    }

    @Deprecated
    public long startObject(long j) {
        assertNotCompacted();
        return startObjectImpl(checkFieldId(j, 1146756268032L), false);
    }

    @Deprecated
    public void endObject(long j) {
        assertNotCompacted();
        endObjectImpl(j, false);
    }

    @Deprecated
    public long startRepeatedObject(long j) {
        assertNotCompacted();
        return startObjectImpl(checkFieldId(j, 2246267895808L), true);
    }

    @Deprecated
    public void endRepeatedObject(long j) {
        assertNotCompacted();
        endObjectImpl(j, true);
    }

    private long startObjectImpl(int i, boolean z) {
        writeTag(i, 2);
        int writePos = this.mBuffer.getWritePos();
        this.mDepth++;
        this.mNextObjectId--;
        this.mBuffer.writeRawFixed32((int) (this.mExpectedObjectToken >> 32));
        this.mBuffer.writeRawFixed32((int) this.mExpectedObjectToken);
        long jMakeToken = makeToken(getTagSize(i), z, this.mDepth, this.mNextObjectId, writePos);
        this.mExpectedObjectToken = jMakeToken;
        return jMakeToken;
    }

    private void endObjectImpl(long j, boolean z) {
        int depthFromToken = getDepthFromToken(j);
        boolean repeatedFromToken = getRepeatedFromToken(j);
        int offsetFromToken = getOffsetFromToken(j);
        int writePos = (this.mBuffer.getWritePos() - offsetFromToken) - 8;
        if (z != repeatedFromToken) {
            if (z) {
                throw new IllegalArgumentException("endRepeatedObject called where endObject should have been");
            }
            throw new IllegalArgumentException("endObject called where endRepeatedObject should have been");
        }
        if ((this.mDepth & 511) != depthFromToken || this.mExpectedObjectToken != j) {
            throw new IllegalArgumentException("Mismatched startObject/endObject calls. Current depth " + this.mDepth + " token=" + token2String(j) + " expectedToken=" + token2String(this.mExpectedObjectToken));
        }
        int i = offsetFromToken + 4;
        this.mExpectedObjectToken = (((long) this.mBuffer.getRawFixed32At(offsetFromToken)) << 32) | (4294967295L & ((long) this.mBuffer.getRawFixed32At(i)));
        this.mDepth--;
        if (writePos > 0) {
            this.mBuffer.editRawFixed32(offsetFromToken, -writePos);
            this.mBuffer.editRawFixed32(i, -1);
        } else if (z) {
            this.mBuffer.editRawFixed32(offsetFromToken, 0);
            this.mBuffer.editRawFixed32(i, 0);
        } else {
            this.mBuffer.rewindWriteTo(offsetFromToken - getTagSizeFromToken(j));
        }
    }

    @Deprecated
    public void writeObject(long j, byte[] bArr) {
        assertNotCompacted();
        writeObjectImpl(checkFieldId(j, 1146756268032L), bArr);
    }

    void writeObjectImpl(int i, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        writeKnownLengthHeader(i, bArr.length);
        this.mBuffer.writeRawBuffer(bArr);
    }

    @Deprecated
    public void writeRepeatedObject(long j, byte[] bArr) {
        assertNotCompacted();
        writeRepeatedObjectImpl(checkFieldId(j, 2246267895808L), bArr);
    }

    void writeRepeatedObjectImpl(int i, byte[] bArr) {
        writeKnownLengthHeader(i, bArr == null ? 0 : bArr.length);
        this.mBuffer.writeRawBuffer(bArr);
    }

    public static int checkFieldId(long j, long j2) {
        long j3 = j & ProtoStream.FIELD_COUNT_MASK;
        long j4 = j & ProtoStream.FIELD_TYPE_MASK;
        long j5 = j2 & ProtoStream.FIELD_COUNT_MASK;
        long j6 = j2 & ProtoStream.FIELD_TYPE_MASK;
        int i = (int) j;
        if (i == 0) {
            throw new IllegalArgumentException("Invalid proto field " + i + " fieldId=" + Long.toHexString(j));
        }
        if (j4 == j6 && (j3 == j5 || (j3 == ProtoStream.FIELD_COUNT_PACKED && j5 == ProtoStream.FIELD_COUNT_REPEATED))) {
            return i;
        }
        String fieldCountString = getFieldCountString(j3);
        String fieldTypeString = getFieldTypeString(j4);
        if (fieldTypeString != null && fieldCountString != null) {
            StringBuilder sb = new StringBuilder();
            if (j6 == ProtoStream.FIELD_TYPE_MESSAGE) {
                sb.append(Telephony.BaseMmsColumns.START);
            } else {
                sb.append("write");
            }
            sb.append(getFieldCountString(j5));
            sb.append(getFieldTypeString(j6));
            sb.append(" called for field ");
            sb.append(i);
            sb.append(" which should be used with ");
            if (j4 == ProtoStream.FIELD_TYPE_MESSAGE) {
                sb.append(Telephony.BaseMmsColumns.START);
            } else {
                sb.append("write");
            }
            sb.append(fieldCountString);
            sb.append(fieldTypeString);
            if (j3 == ProtoStream.FIELD_COUNT_PACKED) {
                sb.append(" or writeRepeated");
                sb.append(fieldTypeString);
            }
            sb.append('.');
            throw new IllegalArgumentException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        if (j6 == ProtoStream.FIELD_TYPE_MESSAGE) {
            sb2.append(Telephony.BaseMmsColumns.START);
        } else {
            sb2.append("write");
        }
        sb2.append(getFieldCountString(j5));
        sb2.append(getFieldTypeString(j6));
        sb2.append(" called with an invalid fieldId: 0x");
        sb2.append(Long.toHexString(j));
        sb2.append(". The proto field ID might be ");
        sb2.append(i);
        sb2.append('.');
        throw new IllegalArgumentException(sb2.toString());
    }

    private static int getTagSize(int i) {
        return EncodedBuffer.getRawVarint32Size(i << 3);
    }

    public void writeTag(int i, int i2) {
        this.mBuffer.writeRawVarint32((i << 3) | i2);
    }

    private void writeKnownLengthHeader(int i, int i2) {
        writeTag(i, 2);
        this.mBuffer.writeRawFixed32(i2);
        this.mBuffer.writeRawFixed32(i2);
    }

    private void assertNotCompacted() {
        if (this.mCompacted) {
            throw new IllegalArgumentException("write called after compact");
        }
    }

    public byte[] getBytes() {
        compactIfNecessary();
        EncodedBuffer encodedBuffer = this.mBuffer;
        return encodedBuffer.getBytes(encodedBuffer.getReadableSize());
    }

    private void compactIfNecessary() {
        if (this.mCompacted) {
            return;
        }
        if (this.mDepth != 0) {
            throw new IllegalArgumentException("Trying to compact with " + this.mDepth + " missing calls to endObject");
        }
        this.mBuffer.startEditing();
        int readableSize = this.mBuffer.getReadableSize();
        editEncodedSize(readableSize);
        this.mBuffer.rewindRead();
        compactSizes(readableSize);
        int i = this.mCopyBegin;
        if (i < readableSize) {
            this.mBuffer.writeFromThisBuffer(i, readableSize - i);
        }
        this.mBuffer.startEditing();
        this.mCompacted = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0083, code lost:
    
        throw new java.lang.RuntimeException("groups not supported at index " + r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int editEncodedSize(int r6) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.proto.ProtoOutputStream.editEncodedSize(int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x007b, code lost:
    
        throw new java.lang.RuntimeException("groups not supported at index " + r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void compactSizes(int r5) {
        /*
            r4 = this;
            android.util.proto.EncodedBuffer r0 = r4.mBuffer
            int r0 = r0.getReadPos()
            int r0 = r0 + r5
        L7:
            android.util.proto.EncodedBuffer r5 = r4.mBuffer
            int r5 = r5.getReadPos()
            if (r5 >= r0) goto Lc6
            int r1 = r4.readRawTag()
            r2 = r1 & 7
            if (r2 == 0) goto Lbb
            r3 = 1
            if (r2 == r3) goto Lb2
            r3 = 2
            if (r2 == r3) goto L7c
            r3 = 3
            if (r2 == r3) goto L63
            r3 = 4
            if (r2 == r3) goto L63
            r5 = 5
            if (r2 != r5) goto L2c
            android.util.proto.EncodedBuffer r5 = r4.mBuffer
            r5.skipRead(r3)
            goto L7
        L2c:
            android.util.proto.ProtoParseException r5 = new android.util.proto.ProtoParseException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "compactSizes Bad tag tag=0x"
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r1 = java.lang.Integer.toHexString(r1)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " wireType="
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r1 = " -- "
            java.lang.StringBuilder r0 = r0.append(r1)
            android.util.proto.EncodedBuffer r4 = r4.mBuffer
            java.lang.String r4 = r4.getDebugString()
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            throw r5
        L63:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "groups not supported at index "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r5 = r0.append(r5)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L7c:
            android.util.proto.EncodedBuffer r5 = r4.mBuffer
            int r1 = r4.mCopyBegin
            int r2 = r5.getReadPos()
            int r3 = r4.mCopyBegin
            int r2 = r2 - r3
            r5.writeFromThisBuffer(r1, r2)
            android.util.proto.EncodedBuffer r5 = r4.mBuffer
            int r5 = r5.readRawFixed32()
            android.util.proto.EncodedBuffer r1 = r4.mBuffer
            int r1 = r1.readRawFixed32()
            android.util.proto.EncodedBuffer r2 = r4.mBuffer
            r2.writeRawVarint32(r1)
            android.util.proto.EncodedBuffer r2 = r4.mBuffer
            int r2 = r2.getReadPos()
            r4.mCopyBegin = r2
            if (r5 < 0) goto Lac
            android.util.proto.EncodedBuffer r5 = r4.mBuffer
            r5.skipRead(r1)
            goto L7
        Lac:
            int r5 = -r5
            r4.compactSizes(r5)
            goto L7
        Lb2:
            android.util.proto.EncodedBuffer r5 = r4.mBuffer
            r1 = 8
            r5.skipRead(r1)
            goto L7
        Lbb:
            android.util.proto.EncodedBuffer r5 = r4.mBuffer
            byte r5 = r5.readRawByte()
            r5 = r5 & 128(0x80, float:1.8E-43)
            if (r5 == 0) goto L7
            goto Lbb
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.proto.ProtoOutputStream.compactSizes(int):void");
    }

    public void flush() {
        if (this.mStream == null || this.mDepth != 0 || this.mCompacted) {
            return;
        }
        compactIfNecessary();
        EncodedBuffer encodedBuffer = this.mBuffer;
        try {
            this.mStream.write(encodedBuffer.getBytes(encodedBuffer.getReadableSize()));
            this.mStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error flushing proto to stream", e);
        }
    }

    private int readRawTag() {
        if (this.mBuffer.getReadPos() == this.mBuffer.getReadableSize()) {
            return 0;
        }
        return (int) this.mBuffer.readRawUnsigned();
    }

    public void dump(String str) {
        Log.d(str, this.mBuffer.getDebugString());
        this.mBuffer.dumpBuffers(str);
    }
}
