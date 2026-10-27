package c.a.a.a;

/* JADX INFO: compiled from: Base16Codec.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f3011a = new a(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f3012b = new a(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final char[] f3013c;

    public a(boolean z) {
        this.f3013c = (z ? "0123456789abcdef" : "0123456789ABCDEF").toCharArray();
    }

    private static int c(char c2, int i2) {
        int iDigit = Character.digit(c2, 16);
        if (iDigit >= 0) {
            return iDigit;
        }
        throw new c.a.a.c.b("Illegal hexadecimal character {} at index {}", Character.valueOf(c2), Integer.valueOf(i2));
    }

    public byte[] a(CharSequence charSequence) {
        if (c.a.a.f.b.i(charSequence)) {
            return null;
        }
        String strB = c.a.a.f.b.b(charSequence);
        int length = strB.length();
        if ((length & 1) != 0) {
            strB = "0" + ((Object) strB);
            length = strB.length();
        }
        byte[] bArr = new byte[length >> 1];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int iC = c(strB.charAt(i2), i2) << 4;
            int i4 = i2 + 1;
            int iC2 = iC | c(strB.charAt(i4), i4);
            i2 = i4 + 1;
            bArr[i3] = (byte) (iC2 & 255);
            i3++;
        }
        return bArr;
    }

    public char[] b(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            char[] cArr2 = this.f3013c;
            cArr[i2] = cArr2[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr[i4] = cArr2[bArr[i3] & 15];
        }
        return cArr;
    }
}
