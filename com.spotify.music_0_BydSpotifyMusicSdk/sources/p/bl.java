package p;

import com.google.common.io.BaseEncoding$DecodingException;
import java.math.RoundingMode;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class bl {
    public final String a;
    public final char[] b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final byte[] g;
    public final boolean[] h;
    public final boolean i;

    public bl(String str, char[] cArr, byte[] bArr, boolean z) {
        str.getClass();
        this.a = str;
        cArr.getClass();
        this.b = cArr;
        try {
            int iO = ux2.o(cArr.length, RoundingMode.UNNECESSARY);
            this.d = iO;
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(iO);
            int i = 1 << (3 - iNumberOfTrailingZeros);
            this.e = i;
            this.f = iO >> iNumberOfTrailingZeros;
            this.c = cArr.length - 1;
            this.g = bArr;
            boolean[] zArr = new boolean[i];
            for (int i2 = 0; i2 < this.f; i2++) {
                zArr[ux2.h(i2 * 8, this.d, RoundingMode.CEILING)] = true;
            }
            this.h = zArr;
            this.i = z;
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e);
        }
    }

    public final int a(char c) throws BaseEncoding$DecodingException {
        if (c > 127) {
            throw new BaseEncoding$DecodingException("Unrecognized character: 0x" + Integer.toHexString(c));
        }
        byte b = this.g[c];
        if (b != -1) {
            return b;
        }
        if (c <= ' ' || c == 127) {
            throw new BaseEncoding$DecodingException("Unrecognized character: 0x" + Integer.toHexString(c));
        }
        throw new BaseEncoding$DecodingException("Unrecognized character: " + c);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof bl)) {
            return false;
        }
        bl blVar = (bl) obj;
        return this.i == blVar.i && Arrays.equals(this.b, blVar.b);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b) + (this.i ? 1231 : 1237);
    }

    public final String toString() {
        return this.a;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public bl(String str, char[] cArr) {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        for (int i = 0; i < cArr.length; i++) {
            char c = cArr[i];
            if (c < 128) {
                if (bArr[c] == -1) {
                    bArr[c] = (byte) i;
                } else {
                    throw new IllegalArgumentException(v03.z("Duplicate character: %s", Character.valueOf(c)));
                }
            } else {
                throw new IllegalArgumentException(v03.z("Non-ASCII character: %s", Character.valueOf(c)));
            }
        }
        this(str, cArr, bArr, false);
    }
}
