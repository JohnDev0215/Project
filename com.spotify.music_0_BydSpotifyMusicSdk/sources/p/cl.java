package p;

import com.google.common.io.BaseEncoding$DecodingException;

/* JADX INFO: loaded from: classes.dex */
public final class cl extends el {
    public final char[] e;

    public cl(bl blVar) {
        super(blVar, null);
        this.e = new char[512];
        char[] cArr = blVar.b;
        ci1.j(cArr.length == 16);
        for (int i = 0; i < 256; i++) {
            char[] cArr2 = this.e;
            cArr2[i] = cArr[i >>> 4];
            cArr2[i | 256] = cArr[i & 15];
        }
    }

    @Override // p.el
    public final int b(byte[] bArr, CharSequence charSequence) throws BaseEncoding$DecodingException {
        if (charSequence.length() % 2 == 1) {
            throw new BaseEncoding$DecodingException("Invalid input length " + charSequence.length());
        }
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char cCharAt = charSequence.charAt(i);
            bl blVar = this.a;
            bArr[i2] = (byte) ((blVar.a(cCharAt) << 4) | blVar.a(charSequence.charAt(i + 1)));
            i += 2;
            i2++;
        }
        return i2;
    }

    @Override // p.el
    public final void e(StringBuilder sb, byte[] bArr, int i) {
        ci1.m(0, i, bArr.length);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = bArr[i2] & 255;
            char[] cArr = this.e;
            sb.append(cArr[i3]);
            sb.append(cArr[i3 | 256]);
        }
    }

    public final el g(bl blVar, Character ch) {
        return new cl(blVar);
    }
}
