package c.a.a.a;

import cn.hutool.core.exceptions.UtilException;

/* JADX INFO: compiled from: Base16Codec.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f2868a = new a(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f2869b = new a(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final char[] f2870c;

    public a(boolean z) {
        this.f2870c = (z ? "0123456789abcdef" : "0123456789ABCDEF").toCharArray();
    }

    private static int c(char c2, int i) {
        int iDigit = Character.digit(c2, 16);
        if (iDigit >= 0) {
            return iDigit;
        }
        throw new UtilException("Illegal hexadecimal character {} at index {}", Character.valueOf(c2), Integer.valueOf(i));
    }

    public byte[] a(CharSequence charSequence) {
        if (c.a.a.d.b.j(charSequence)) {
            return null;
        }
        String strC = c.a.a.d.b.c(charSequence);
        int length = strC.length();
        if ((length & 1) != 0) {
            strC = "0" + ((Object) strC);
            length = strC.length();
        }
        byte[] bArr = new byte[length >> 1];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int iC = c(strC.charAt(i), i) << 4;
            int i3 = i + 1;
            int iC2 = iC | c(strC.charAt(i3), i3);
            i = i3 + 1;
            bArr[i2] = (byte) (iC2 & 255);
            i2++;
        }
        return bArr;
    }

    public char[] b(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = this.f2870c;
            cArr[i] = cArr2[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return cArr;
    }
}
