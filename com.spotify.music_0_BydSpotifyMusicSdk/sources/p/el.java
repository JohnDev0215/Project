package p;

import com.google.common.io.BaseEncoding$DecodingException;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class el {
    public static final cl d;
    public final bl a;
    public final Character b;
    public volatile el c;

    static {
        new dl("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
        new dl("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
        new el("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
        new el("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
        d = new cl(new bl("base16()", "0123456789ABCDEF".toCharArray()));
    }

    public el(bl blVar, Character ch) {
        this.a = blVar;
        if (ch != null) {
            char cCharValue = ch.charValue();
            byte[] bArr = blVar.g;
            if (cCharValue < bArr.length && bArr[cCharValue] != -1) {
                throw new IllegalArgumentException(v03.z("Padding character %s was already in alphabet", ch));
            }
        }
        this.b = ch;
    }

    public final byte[] a(String str) {
        try {
            CharSequence charSequenceF = f(str);
            int length = (int) (((((long) this.a.d) * ((long) charSequenceF.length())) + 7) / 8);
            byte[] bArr = new byte[length];
            int iB = b(bArr, charSequenceF);
            if (iB == length) {
                return bArr;
            }
            byte[] bArr2 = new byte[iB];
            System.arraycopy(bArr, 0, bArr2, 0, iB);
            return bArr2;
        } catch (BaseEncoding$DecodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int b(byte[] bArr, CharSequence charSequence) throws BaseEncoding$DecodingException {
        int i;
        int i2;
        CharSequence charSequenceF = f(charSequence);
        int length = charSequenceF.length();
        bl blVar = this.a;
        if (!blVar.h[length % blVar.e]) {
            throw new BaseEncoding$DecodingException("Invalid input length " + charSequenceF.length());
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < charSequenceF.length()) {
            long jA = 0;
            int i5 = 0;
            int i6 = 0;
            while (true) {
                i = blVar.d;
                i2 = blVar.e;
                if (i5 >= i2) {
                    break;
                }
                jA <<= i;
                if (i3 + i5 < charSequenceF.length()) {
                    jA |= (long) blVar.a(charSequenceF.charAt(i6 + i3));
                    i6++;
                }
                i5++;
            }
            int i7 = blVar.f;
            int i8 = (i7 * 8) - (i6 * i);
            int i9 = (i7 - 1) * 8;
            while (i9 >= i8) {
                bArr[i4] = (byte) ((jA >>> i9) & 255);
                i9 -= 8;
                i4++;
            }
            i3 += i2;
        }
        return i4;
    }

    public final String c(byte[] bArr) {
        int length = bArr.length;
        ci1.m(0, length, bArr.length);
        bl blVar = this.a;
        StringBuilder sb = new StringBuilder(ux2.h(length, blVar.f, RoundingMode.CEILING) * blVar.e);
        try {
            e(sb, bArr, length);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final void d(StringBuilder sb, byte[] bArr, int i, int i2) {
        ci1.m(i, i + i2, bArr.length);
        bl blVar = this.a;
        int i3 = 0;
        ci1.j(i2 <= blVar.f);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | ((long) (bArr[i + i4] & 255))) << 8;
        }
        int i5 = blVar.d;
        int i6 = ((i2 + 1) * 8) - i5;
        while (i3 < i2 * 8) {
            sb.append(blVar.b[((int) (j >>> (i6 - i3))) & blVar.c]);
            i3 += i5;
        }
        Character ch = this.b;
        if (ch != null) {
            while (i3 < blVar.f * 8) {
                sb.append(ch.charValue());
                i3 += i5;
            }
        }
    }

    public void e(StringBuilder sb, byte[] bArr, int i) {
        int i2 = 0;
        ci1.m(0, i, bArr.length);
        while (i2 < i) {
            bl blVar = this.a;
            d(sb, bArr, i2, Math.min(blVar.f, i - i2));
            i2 += blVar.f;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof el)) {
            return false;
        }
        el elVar = (el) obj;
        return this.a.equals(elVar.a) && Objects.equals(this.b, elVar.b);
    }

    public final CharSequence f(CharSequence charSequence) {
        Character ch = this.b;
        if (ch == null) {
            return charSequence;
        }
        char cCharValue = ch.charValue();
        int length = charSequence.length() - 1;
        while (length >= 0 && charSequence.charAt(length) == cCharValue) {
            length--;
        }
        return charSequence.subSequence(0, length + 1);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ Objects.hashCode(this.b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        bl blVar = this.a;
        sb.append(blVar);
        if (8 % blVar.d != 0) {
            Character ch = this.b;
            if (ch == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(ch);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    public el(String str, String str2, Character ch) {
        this(new bl(str, str2.toCharArray()), ch);
    }
}
