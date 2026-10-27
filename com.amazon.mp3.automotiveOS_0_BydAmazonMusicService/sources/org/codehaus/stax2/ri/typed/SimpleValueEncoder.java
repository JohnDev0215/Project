package org.codehaus.stax2.ri.typed;

import org.codehaus.stax2.typed.Base64Variant;

/* JADX INFO: loaded from: classes7.dex */
public class SimpleValueEncoder {
    protected final char[] mBuffer = new char[500];
    protected final ValueEncoderFactory mEncoderFactory = new ValueEncoderFactory();

    public String encodeAsString(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        return encode(this.mEncoderFactory.getEncoder(base64Variant, bArr, i, i2));
    }

    protected String encode(AsciiValueEncoder asciiValueEncoder) {
        char[] cArr = this.mBuffer;
        int iEncodeMore = asciiValueEncoder.encodeMore(cArr, 0, cArr.length);
        if (asciiValueEncoder.isCompleted()) {
            return new String(this.mBuffer, 0, iEncodeMore);
        }
        StringBuffer stringBuffer = new StringBuffer(this.mBuffer.length << 1);
        stringBuffer.append(this.mBuffer, 0, iEncodeMore);
        do {
            char[] cArr2 = this.mBuffer;
            stringBuffer.append(this.mBuffer, 0, asciiValueEncoder.encodeMore(cArr2, 0, cArr2.length));
        } while (!asciiValueEncoder.isCompleted());
        return stringBuffer.toString();
    }
}
