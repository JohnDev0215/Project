package com.amazonaws.mobileconnectors.iot;

import com.amazon.music.destination.parser.ParserUtil;
import java.io.IOException;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes5.dex */
class Asn1Object {
    private static final byte LOWER_5_BITS = 31;
    private final int length;
    private final int tag;
    private final int type;
    private final byte[] value;

    public Asn1Object(int i, int i2, byte[] bArr) {
        this.tag = i;
        this.type = i & 31;
        this.length = i2;
        this.value = bArr;
    }

    public int getType() {
        return this.type;
    }

    public int getLength() {
        return this.length;
    }

    public byte[] getValue() {
        return this.value;
    }

    public boolean isConstructed() {
        return (this.tag & 32) == 32;
    }

    public DerParser getParser() throws IOException {
        if (!isConstructed()) {
            throw new IOException("Invalid DER: can't parse primitive entity");
        }
        return new DerParser(this.value);
    }

    public BigInteger getInteger() throws IOException {
        if (this.type != 2) {
            throw new IOException("Invalid DER: object is not integer");
        }
        return new BigInteger(this.value);
    }

    public String getString() throws IOException {
        String str;
        int i = this.type;
        if (i == 12) {
            str = ParserUtil.UTF_8;
        } else if (i != 30) {
            if (i != 18 && i != 19 && i != 21 && i != 22) {
                switch (i) {
                    case 25:
                    case 26:
                    case 27:
                        break;
                    case 28:
                        throw new IOException("Invalid DER: can't handle UCS-4 string");
                    default:
                        throw new IOException("Invalid DER: object is not a string");
                }
            }
            str = "ISO-8859-1";
        } else {
            str = "UTF-16BE";
        }
        return new String(this.value, str);
    }
}
