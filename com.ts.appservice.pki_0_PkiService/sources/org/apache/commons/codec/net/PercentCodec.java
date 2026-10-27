package org.apache.commons.codec.net;

import java.nio.ByteBuffer;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class PercentCodec implements BinaryEncoder, BinaryDecoder {
    private static final byte ESCAPE_CHAR = 37;
    private final BitSet alwaysEncodeChars;
    private int alwaysEncodeCharsMax;
    private int alwaysEncodeCharsMin;
    private final boolean plusForSpace;

    public PercentCodec() {
        this.alwaysEncodeChars = new BitSet();
        this.alwaysEncodeCharsMin = Integer.MAX_VALUE;
        this.alwaysEncodeCharsMax = PKIFailureInfo.systemUnavail;
        this.plusForSpace = false;
        insertAlwaysEncodeChar(ESCAPE_CHAR);
    }

    private boolean canEncode(byte b2) {
        return !isAsciiChar(b2) || (inAlwaysEncodeCharsRange(b2) && this.alwaysEncodeChars.get(b2));
    }

    private boolean containsSpace(byte[] bArr) {
        for (byte b2 : bArr) {
            if (b2 == 32) {
                return true;
            }
        }
        return false;
    }

    private byte[] doEncode(byte[] bArr, int i, boolean z) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        for (byte b2 : bArr) {
            if (z && canEncode(b2)) {
                if (b2 < 0) {
                    b2 = (byte) (b2 + 256);
                }
                char cHexDigit = Utils.hexDigit(b2 >> 4);
                char cHexDigit2 = Utils.hexDigit(b2);
                byteBufferAllocate.put(ESCAPE_CHAR);
                byteBufferAllocate.put((byte) cHexDigit);
                byteBufferAllocate.put((byte) cHexDigit2);
            } else if (this.plusForSpace && b2 == 32) {
                byteBufferAllocate.put((byte) 43);
            } else {
                byteBufferAllocate.put(b2);
            }
        }
        return byteBufferAllocate.array();
    }

    private int expectedDecodingBytes(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            i += bArr[i] == 37 ? 3 : 1;
            i2++;
        }
        return i2;
    }

    private int expectedEncodingBytes(byte[] bArr) {
        int i = 0;
        for (byte b2 : bArr) {
            i += canEncode(b2) ? 3 : 1;
        }
        return i;
    }

    private boolean inAlwaysEncodeCharsRange(byte b2) {
        return b2 >= this.alwaysEncodeCharsMin && b2 <= this.alwaysEncodeCharsMax;
    }

    private void insertAlwaysEncodeChar(byte b2) {
        this.alwaysEncodeChars.set(b2);
        if (b2 < this.alwaysEncodeCharsMin) {
            this.alwaysEncodeCharsMin = b2;
        }
        if (b2 > this.alwaysEncodeCharsMax) {
            this.alwaysEncodeCharsMax = b2;
        }
    }

    private void insertAlwaysEncodeChars(byte[] bArr) {
        if (bArr != null) {
            for (byte b2 : bArr) {
                insertAlwaysEncodeChar(b2);
            }
        }
        insertAlwaysEncodeChar(ESCAPE_CHAR);
    }

    private boolean isAsciiChar(byte b2) {
        return b2 >= 0;
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) throws DecoderException {
        if (bArr == null) {
            return null;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(expectedDecodingBytes(bArr));
        int i = 0;
        while (i < bArr.length) {
            byte b2 = bArr[i];
            if (b2 == 37) {
                int i2 = i + 1;
                try {
                    int iDigit16 = Utils.digit16(bArr[i2]);
                    i = i2 + 1;
                    byteBufferAllocate.put((byte) ((iDigit16 << 4) + Utils.digit16(bArr[i])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid percent decoding: ", e);
                }
            } else if (this.plusForSpace && b2 == 43) {
                byteBufferAllocate.put((byte) 32);
            } else {
                byteBufferAllocate.put(b2);
            }
            i++;
        }
        return byteBufferAllocate.array();
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int iExpectedEncodingBytes = expectedEncodingBytes(bArr);
        boolean z = iExpectedEncodingBytes != bArr.length;
        return (z || (this.plusForSpace && containsSpace(bArr))) ? doEncode(bArr, iExpectedEncodingBytes, z) : bArr;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent encoded");
    }

    public PercentCodec(byte[] bArr, boolean z) {
        this.alwaysEncodeChars = new BitSet();
        this.alwaysEncodeCharsMin = Integer.MAX_VALUE;
        this.alwaysEncodeCharsMax = PKIFailureInfo.systemUnavail;
        this.plusForSpace = z;
        insertAlwaysEncodeChars(bArr);
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent decoded");
    }
}
