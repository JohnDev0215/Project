package c.a.a.g;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: HexUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static byte[] a(CharSequence charSequence) {
        return c.a.a.a.a.f3011a.a(charSequence);
    }

    public static byte[] b(String str) {
        return a(str);
    }

    public static char[] c(byte[] bArr, boolean z) {
        return (z ? c.a.a.a.a.f3011a : c.a.a.a.a.f3012b).b(bArr);
    }

    public static String d(String str) {
        return e(str, c.f3076b);
    }

    public static String e(String str, Charset charset) {
        return g(c.a.a.f.b.a(str, charset), true);
    }

    public static String f(byte[] bArr) {
        return g(bArr, true);
    }

    public static String g(byte[] bArr, boolean z) {
        return new String(c(bArr, z));
    }
}
