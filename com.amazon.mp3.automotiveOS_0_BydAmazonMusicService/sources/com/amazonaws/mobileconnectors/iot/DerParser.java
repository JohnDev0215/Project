package com.amazonaws.mobileconnectors.iot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes5.dex */
class DerParser {
    public static final int ANY = 0;
    public static final int APPLICATION = 64;
    public static final int BIT_STRING = 3;
    public static final int BMP_STRING = 30;
    public static final int BOOLEAN = 1;
    public static final int BYTE_MAX = 255;
    public static final int CONSTRUCTED = 32;
    public static final int CONTEXT = 128;
    public static final int ENUMERATED = 10;
    public static final int GENERALIZED_TIME = 24;
    public static final int GENERAL_STRING = 27;
    public static final int GRAPHIC_STRING = 25;
    public static final int IA5_STRING = 22;
    public static final int INTEGER = 2;
    public static final int ISO646_STRING = 26;
    public static final byte LOWER_7_BITS = 127;
    public static final int MAX_NUMBER_OF_BYTES = 4;
    public static final int NULL = 5;
    public static final int NUMERIC_STRING = 18;
    public static final int OBJECT_IDENTIFIER = 6;
    public static final int OCTET_STRING = 4;
    public static final int PRINTABLE_STRING = 19;
    public static final int PRIVATE = 192;
    public static final int REAL = 9;
    public static final int RELATIVE_OID = 13;
    public static final int SEQUENCE = 16;
    public static final int SET = 17;
    public static final int T61_STRING = 20;
    public static final int UNIVERSAL = 0;
    public static final int UNIVERSAL_STRING = 28;
    public static final int UTC_TIME = 23;
    public static final int UTF8_STRING = 12;
    public static final int VIDEOTEX_STRING = 21;
    private final InputStream in;

    public DerParser(InputStream inputStream) {
        this.in = inputStream;
    }

    public DerParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr));
    }

    public Asn1Object read() throws IOException {
        int i = this.in.read();
        if (i == -1) {
            throw new IOException("Invalid DER: stream too short, missing tag");
        }
        int length = getLength();
        byte[] bArr = new byte[length];
        if (this.in.read(bArr) < length) {
            throw new IOException("Invalid DER: stream too short, missing value");
        }
        return new Asn1Object(i, length, bArr);
    }

    private int getLength() throws IOException {
        int i = this.in.read();
        if (i == -1) {
            throw new IOException("Invalid DER: length missing");
        }
        if ((i & (-128)) == 0) {
            return i;
        }
        int i2 = i & WorkQueueKt.MASK;
        if (i >= 255 || i2 > 4) {
            throw new IOException("Invalid DER: length field too big (" + i + ")");
        }
        byte[] bArr = new byte[i2];
        if (this.in.read(bArr) < i2) {
            throw new IOException("Invalid DER: length too short");
        }
        return new BigInteger(1, bArr).intValue();
    }
}
