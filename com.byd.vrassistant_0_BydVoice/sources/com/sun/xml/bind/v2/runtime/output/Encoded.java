package com.sun.xml.bind.v2.runtime.output;

import java.io.IOException;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
public final class Encoded {
    public byte[] buf;
    public int len;
    private static final byte[][] entities = new byte[128][];
    private static final byte[][] attributeEntities = new byte[128][];

    public Encoded() {
    }

    public Encoded(String str) {
        set(str);
    }

    public void ensureSize(int i) {
        byte[] bArr = this.buf;
        if (bArr == null || bArr.length < i) {
            this.buf = new byte[i];
        }
    }

    public final void set(String str) {
        int i;
        int i2;
        int length = str.length();
        ensureSize((length * 3) + 1);
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            char cCharAt = str.charAt(i3);
            if (cCharAt > 127) {
                if (cCharAt <= 2047) {
                    this.buf[i4] = (byte) ((cCharAt >> 6) + 192);
                    i2 = i4 + 1;
                } else if (55296 <= cCharAt && cCharAt <= 57343) {
                    i3++;
                    int iCharAt = (((cCharAt & 1023) << 10) | (str.charAt(i3) & 1023)) + 65536;
                    byte[] bArr = this.buf;
                    int i5 = i4 + 1;
                    bArr[i4] = (byte) ((iCharAt >> 18) | 240);
                    int i6 = i5 + 1;
                    bArr[i5] = (byte) (((iCharAt >> 12) & 63) | 128);
                    int i7 = i6 + 1;
                    bArr[i6] = (byte) (((iCharAt >> 6) & 63) | 128);
                    i4 = i7 + 1;
                    bArr[i7] = (byte) ((iCharAt & 63) + 128);
                    i3++;
                } else {
                    byte[] bArr2 = this.buf;
                    int i8 = i4 + 1;
                    bArr2[i4] = (byte) ((cCharAt >> '\f') + 224);
                    i2 = i8 + 1;
                    bArr2[i8] = (byte) (((cCharAt >> 6) & 63) + 128);
                }
                i = i2 + 1;
                this.buf[i2] = (byte) ((cCharAt & '?') + 128);
            } else {
                i = i4 + 1;
                this.buf[i4] = (byte) cCharAt;
            }
            i4 = i;
            i3++;
        }
        this.len = i4;
    }

    public final void setEscape(String str, boolean z) {
        int iWriteEntity;
        int i;
        int length = str.length();
        ensureSize((length * 6) + 1);
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (cCharAt > 127) {
                if (cCharAt <= 2047) {
                    this.buf[i3] = (byte) ((cCharAt >> 6) + 192);
                    i = i3 + 1;
                } else if (55296 <= cCharAt && cCharAt <= 57343) {
                    i2++;
                    int iCharAt = (((cCharAt & 1023) << 10) | (str.charAt(i2) & 1023)) + 65536;
                    byte[] bArr = this.buf;
                    int i4 = i3 + 1;
                    bArr[i3] = (byte) ((iCharAt >> 18) | 240);
                    int i5 = i4 + 1;
                    bArr[i4] = (byte) (((iCharAt >> 12) & 63) | 128);
                    int i6 = i5 + 1;
                    bArr[i5] = (byte) (((iCharAt >> 6) & 63) | 128);
                    i3 = i6 + 1;
                    bArr[i6] = (byte) ((iCharAt & 63) + 128);
                    i2++;
                } else {
                    byte[] bArr2 = this.buf;
                    int i7 = i3 + 1;
                    bArr2[i3] = (byte) ((cCharAt >> '\f') + 224);
                    i = i7 + 1;
                    bArr2[i7] = (byte) (((cCharAt >> 6) & 63) + 128);
                }
                iWriteEntity = i + 1;
                this.buf[i] = (byte) ((cCharAt & '?') + 128);
            } else {
                byte[] bArr3 = attributeEntities[cCharAt];
                if (bArr3 != null) {
                    if (z || entities[cCharAt] != null) {
                        iWriteEntity = writeEntity(bArr3, i3);
                    } else {
                        iWriteEntity = i3 + 1;
                        this.buf[i3] = (byte) cCharAt;
                    }
                } else {
                    iWriteEntity = i3 + 1;
                    this.buf[i3] = (byte) cCharAt;
                }
            }
            i3 = iWriteEntity;
            i2++;
        }
        this.len = i3;
    }

    private int writeEntity(byte[] bArr, int i) {
        System.arraycopy(bArr, 0, this.buf, i, bArr.length);
        return i + bArr.length;
    }

    public final void write(UTF8XmlOutput uTF8XmlOutput) throws IOException {
        uTF8XmlOutput.write(this.buf, 0, this.len);
    }

    public void append(char c) {
        byte[] bArr = this.buf;
        int i = this.len;
        this.len = i + 1;
        bArr[i] = (byte) c;
    }

    public void compact() {
        int i = this.len;
        byte[] bArr = new byte[i];
        System.arraycopy(this.buf, 0, bArr, 0, i);
        this.buf = bArr;
    }

    static {
        add(Typography.amp, "&amp;", false);
        add(Typography.less, "&lt;", false);
        add(Typography.greater, "&gt;", false);
        add(Typography.quote, "&quot;", true);
        add('\t', "&#x9;", true);
        add('\r', "&#xD;", false);
        add('\n', "&#xA;", true);
    }

    private static void add(char c, String str, boolean z) {
        byte[] bytes = UTF8XmlOutput.toBytes(str);
        attributeEntities[c] = bytes;
        if (z) {
            return;
        }
        entities[c] = bytes;
    }
}
