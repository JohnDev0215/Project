package p;

import com.google.common.io.BaseEncoding$DecodingException;

/* JADX INFO: loaded from: classes.dex */
public final class dl extends el {
    public dl(String str, String str2, Character ch) {
        this(new bl(str, str2.toCharArray()), ch);
    }

    @Override // p.el
    public final int b(byte[] bArr, CharSequence charSequence) throws BaseEncoding$DecodingException {
        CharSequence charSequenceF = f(charSequence);
        int length = charSequenceF.length();
        bl blVar = this.a;
        if (!blVar.h[length % blVar.e]) {
            throw new BaseEncoding$DecodingException("Invalid input length " + charSequenceF.length());
        }
        int i = 0;
        int i2 = 0;
        while (i < charSequenceF.length()) {
            int i3 = i + 2;
            int iA = (blVar.a(charSequenceF.charAt(i + 1)) << 12) | (blVar.a(charSequenceF.charAt(i)) << 18);
            int i4 = i2 + 1;
            bArr[i2] = (byte) (iA >>> 16);
            if (i3 < charSequenceF.length()) {
                int i5 = i + 3;
                int iA2 = iA | (blVar.a(charSequenceF.charAt(i3)) << 6);
                int i6 = i2 + 2;
                bArr[i4] = (byte) ((iA2 >>> 8) & 255);
                if (i5 < charSequenceF.length()) {
                    i += 4;
                    i2 += 3;
                    bArr[i6] = (byte) ((iA2 | blVar.a(charSequenceF.charAt(i5))) & 255);
                } else {
                    i2 = i6;
                    i = i5;
                }
            } else {
                i2 = i4;
                i = i3;
            }
        }
        return i2;
    }

    @Override // p.el
    public final void e(StringBuilder sb, byte[] bArr, int i) {
        int i2 = 0;
        ci1.m(0, i, bArr.length);
        for (int i3 = i; i3 >= 3; i3 -= 3) {
            int i4 = i2 + 2;
            int i5 = ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2] & 255) << 16);
            i2 += 3;
            int i6 = i5 | (bArr[i4] & 255);
            bl blVar = this.a;
            sb.append(blVar.b[i6 >>> 18]);
            char[] cArr = blVar.b;
            sb.append(cArr[(i6 >>> 12) & 63]);
            sb.append(cArr[(i6 >>> 6) & 63]);
            sb.append(cArr[i6 & 63]);
        }
        if (i2 < i) {
            d(sb, bArr, i2, i - i2);
        }
    }

    public dl(bl blVar, Character ch) {
        super(blVar, ch);
        ci1.j(blVar.b.length == 64);
    }
}
