package com.android.net.module.util;

import android.media.MediaMetrics;
import android.text.TextUtils;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class DnsPacket {
    public static final int ANSECTION = 1;
    public static final int ARSECTION = 3;
    public static final int NSSECTION = 2;
    private static final int NUM_SECTIONS = 4;
    public static final int QDSECTION = 0;
    private static final String TAG = DnsPacket.class.getSimpleName();
    protected final DnsHeader mHeader;
    protected final List<DnsRecord>[] mRecords;

    public static class ParseException extends RuntimeException {
        public String reason;

        public ParseException(String str) {
            super(str);
            this.reason = str;
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
            this.reason = str;
        }
    }

    public class DnsHeader {
        private static final String TAG = "DnsHeader";
        public final int flags;
        public final int id;
        private final int[] mRecordCount;
        public final int rcode;

        DnsHeader(ByteBuffer byteBuffer) throws BufferUnderflowException {
            this.id = Short.toUnsignedInt(byteBuffer.getShort());
            int unsignedInt = Short.toUnsignedInt(byteBuffer.getShort());
            this.flags = unsignedInt;
            this.rcode = unsignedInt & 15;
            this.mRecordCount = new int[4];
            for (int i = 0; i < 4; i++) {
                this.mRecordCount[i] = Short.toUnsignedInt(byteBuffer.getShort());
            }
        }

        public int getRecordCount(int i) {
            return this.mRecordCount[i];
        }
    }

    public class DnsRecord {
        private static final int MAXLABELCOUNT = 128;
        private static final int MAXLABELSIZE = 63;
        private static final int MAXNAMESIZE = 255;
        private static final int NAME_COMPRESSION = 192;
        private static final int NAME_NORMAL = 0;
        private static final String TAG = "DnsRecord";
        public final String dName;
        private final DecimalFormat mByteFormat = new DecimalFormat();
        private final FieldPosition mPos = new FieldPosition(0);
        private final byte[] mRdata;
        public final int nsClass;
        public final int nsType;
        public final long ttl;

        DnsRecord(int i, ByteBuffer byteBuffer) throws BufferUnderflowException, ParseException {
            String name = parseName(byteBuffer, 0);
            this.dName = name;
            if (name.length() > 255) {
                throw new ParseException("Parse name fail, name size is too long: " + this.dName.length());
            }
            this.nsType = Short.toUnsignedInt(byteBuffer.getShort());
            this.nsClass = Short.toUnsignedInt(byteBuffer.getShort());
            if (i != 0) {
                this.ttl = Integer.toUnsignedLong(byteBuffer.getInt());
                byte[] bArr = new byte[Short.toUnsignedInt(byteBuffer.getShort())];
                this.mRdata = bArr;
                byteBuffer.get(bArr);
                return;
            }
            this.ttl = 0L;
            this.mRdata = null;
        }

        public byte[] getRR() {
            byte[] bArr = this.mRdata;
            if (bArr == null) {
                return null;
            }
            return (byte[]) bArr.clone();
        }

        private String labelToString(byte[] bArr) {
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArr) {
                int unsignedInt = Byte.toUnsignedInt(b);
                if (unsignedInt <= 32 || unsignedInt >= 127) {
                    stringBuffer.append('\\');
                    this.mByteFormat.format(unsignedInt, stringBuffer, this.mPos);
                } else if (unsignedInt == 34 || unsignedInt == 46 || unsignedInt == 59 || unsignedInt == 92 || unsignedInt == 40 || unsignedInt == 41 || unsignedInt == 64 || unsignedInt == 36) {
                    stringBuffer.append('\\');
                    stringBuffer.append((char) unsignedInt);
                } else {
                    stringBuffer.append((char) unsignedInt);
                }
            }
            return stringBuffer.toString();
        }

        private String parseName(ByteBuffer byteBuffer, int i) throws BufferUnderflowException, ParseException {
            if (i > 128) {
                throw new ParseException("Failed to parse name, too many labels");
            }
            int unsignedInt = Byte.toUnsignedInt(byteBuffer.get());
            int i2 = unsignedInt & 192;
            if (unsignedInt == 0) {
                return "";
            }
            if (i2 != 0 && i2 != 192) {
                throw new ParseException("Parse name fail, bad label type");
            }
            if (i2 == 192) {
                int unsignedInt2 = ((unsignedInt & (-193)) << 8) + Byte.toUnsignedInt(byteBuffer.get());
                int iPosition = byteBuffer.position();
                if (unsignedInt2 >= iPosition - 2) {
                    throw new ParseException("Parse compression name fail, invalid compression");
                }
                byteBuffer.position(unsignedInt2);
                String name = parseName(byteBuffer, i + 1);
                byteBuffer.position(iPosition);
                return name;
            }
            byte[] bArr = new byte[unsignedInt];
            byteBuffer.get(bArr);
            String strLabelToString = labelToString(bArr);
            if (strLabelToString.length() > 63) {
                throw new ParseException("Parse name fail, invalid label length");
            }
            String name2 = parseName(byteBuffer, i + 1);
            if (TextUtils.isEmpty(name2)) {
                return strLabelToString;
            }
            return strLabelToString + MediaMetrics.SEPARATOR + name2;
        }
    }

    protected DnsPacket(byte[] bArr) throws ParseException {
        if (bArr == null) {
            throw new ParseException("Parse header failed, null input data");
        }
        try {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            this.mHeader = new DnsHeader(byteBufferWrap);
            this.mRecords = new ArrayList[4];
            for (int i = 0; i < 4; i++) {
                int recordCount = this.mHeader.getRecordCount(i);
                if (recordCount > 0) {
                    this.mRecords[i] = new ArrayList(recordCount);
                }
                for (int i2 = 0; i2 < recordCount; i2++) {
                    try {
                        this.mRecords[i].add(new DnsRecord(i, byteBufferWrap));
                    } catch (BufferUnderflowException e) {
                        throw new ParseException("Parse record fail", e);
                    }
                }
            }
        } catch (BufferUnderflowException e2) {
            throw new ParseException("Parse Header fail, bad input data", e2);
        }
    }
}
